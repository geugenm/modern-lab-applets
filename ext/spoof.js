function spoofFunc()
{
	// TODO: This is appropiate for newer Chrome
	if ('userAgent' in Navigator.prototype)
	{
		var oldUA = navigator.userAgent;
		var parts = oldUA.split(' ');
		for(var i=0;i<parts.length;i++)
		{
			// Replace with chromium, some sites have disabled chrome completely
			if(parts[i].startsWith("Chrome/") || parts[i].startsWith("Chromium/"))
				parts[i] = "Chromium/44";
		}
		var newUA = parts.join(' ');
		var mimes = [].slice.call(navigator.mimeTypes);
		var realMimesLen = mimes.length;
		var plugin = [];
		plugin.description = "CheerpJ Applet Runner executes Java applets.";
		plugin.name = "CheerpJ Applet Runner";
		mimes.push({description:"",type:"application/x-java-applet",enabledPlugin:plugin});
		mimes.push({description:"",type:"application/x-java-applet;version=1.8.0",enabledPlugin:plugin});
		mimes.push({description:"",type:"application/x-java-applet;jpi-version=1.8.0",enabledPlugin:plugin});
		mimes.push({description:"",type:"application/x-java-applet;deploy=10.7.2",enabledPlugin:plugin});
		// Allow direct queries from mime-types
		for(var i=0;i<mimes.length;i++)
		{
			var m=mimes[i];
			mimes[m.type] = m;
		}
		// Add mime types to the plugin object
		for(var i=realMimesLen;i<mimes.length;i++)
		{
			var m=mimes[i];
			plugin.push(m);
			plugin[m.type] = m;
		}
		Object.defineProperties(Navigator.prototype, {userAgent:{ value: newUA, configurable: false, enumerable: true, writable: false},
								javaEnabled: { value: function() { return true; }, configurable: false, enumerable: true, writable: false},
								mimeTypes: { value: mimes, configurable: false, enumerable: true, writable: false}});
		Object.defineProperty(window, "chrome", {value: null});
	}
	else
		debugger
	// Directly inject the spoof code in all subframes
	var elems = document.getElementsByTagName("frame");
	if(elems.length == 0)
		return;
	var spoofScript = spoofFunc.toString()+";spoofFunc();";
	for(var i=0;i<elems.length;i++)
	{
		var f = elems[i];
		var s = f.contentDocument.createElement("script");
		s.textContent = spoofScript;
		f.contentDocument.head.appendChild(s);
	}
}
var settingsUrl = document.documentElement.getAttribute("data-settings-url");
var loaderUrl = document.documentElement.firstChild.src;
// Wipe out the document
document.removeChild(document.documentElement);
window.onmessage = function(e)
{
	window.onmessage = null;
	document.removeChild(document.documentElement);
	var msg = e.data;
	if(msg.systemclipboard == "true")
		cjClipboardMode="system";
	spoofFunc();
	// Trigger a soft-reload of the page (part 3)
	// Download the original, unmodified code of the current page, we need
	// to run let the whole page reload from scratch now that we have "spoofed"
	// that the legacy Java plugin is available.
	var xhr = new XMLHttpRequest();
	xhr.open("GET", location.href);
	xhr.onload = function(e)
	{
		document.addEventListener("DOMContentLoaded", function(e)
		{
			// Trigger a soft-reload of the page (part 5)
			// If the page uses legacy framesets we need to wrap it inside an iframe, this is required
			// to allow Java applets to spawn virtual Windows. Without the wrapping iframe it is not possible
			// to have a proper overlay <div>, since framesets cannot have sibling elements.
			if(document.body.tagName == "FRAMESET")
			{
				document.open();
				document.write("<html><head></head><body style='margin: 0;height:100%;'><iframe style='border: 0; margin: 0; padding: 0; height: 100%; width: 100%;' id='cjenv'></iframe></body></html>");
				document.close();
				var subLoader = document.createElement("script");
				subLoader.src = loaderUrl;
				subLoader.onload = function(e)
				{
					cheerpjAttachBodyObserver(true);
					var cjEnv = document.getElementById("cjenv");
					cjEnv.contentDocument.open();
					cjEnv.contentDocument.write(xhr.responseText);
					cjEnv.contentDocument.close();
				}
				document.head.appendChild(subLoader);
			}
			else
			{
				// If any Java applet tag is detected, replace it with HTML5 and begin execution of the code
				cheerpjAttachBodyObserver(true);
			}
		});
		// Trigger a soft-reload of the page (part 4)
		// Actually write the whole page content in the document
		var xhr = e.target;
		document.write(xhr.responseText);
		document.close();
	};
	xhr.send();
};
// Trigger a soft-reload of the page (part 2)
// Inject an iframe from the extension package, it will be able to extract setting from the localStorage
// of the extension domain. The setting will be sent using postMessage and handled in the 'onmessage' above
var i = document.createElement("iframe");
i.src = settingsUrl;
document.appendChild(i);

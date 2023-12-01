// Be robust to this script being injected multiple times
var cjInjectDone;
if(!cjInjectDone)
{
cjInjectDone = 1;
// Trigger a soft-reload of the page (part 1)
// Inject the CheerpJ loader.js script and the code to "spoof" the Java plugin,
// this is required to support pages which do not show legacy applets unless
// the old Java plugin is detected.
document.open();
var h = document.createElement("head");
h.setAttribute("data-settings-url", chrome.runtime.getURL("settings.html"));
document.appendChild(h);
var loaderScript = document.createElement("script");
loaderScript.src = chrome.runtime.getURL("cheerpj/loader.js");
h.appendChild(loaderScript);
var spoofScript = document.createElement("script");
spoofScript.src = chrome.runtime.getURL("spoof.js");
h.appendChild(spoofScript);
}

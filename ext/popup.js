var currentOrigin = null;
function sendReport()
{
	// Check what's the current report phase
	if(this.value == "Report bug")
	{
		chrome.tabs.query({active:true, currentWindow: true}, function(tabs)
		{
			document.getElementById("reporturl").textContent = tabs[0].url;
			document.getElementById("report").style.display = "block";
			document.getElementById("reportbutton").value = "Send report";
		});
	}
	else
	{
		var xhr = new XMLHttpRequest()
		xhr.open("POST", "https://docs.google.com/forms/u/0/d/e/1FAIpQLSekuolLuPHfptxRBnUlEoErgiZHg053NK-aasXo-00QHpXSbw/formResponse");
		// Google forms does not provide CORS, but we actually don't care
		xhr.onload=xhr.onerror=function()
		{
			document.getElementById("reportbutton").value = "Sent!";
			document.getElementById("reportbutton").disabled = true;
			document.getElementById("report").style.display = "none";
			document.getElementById("reportend").style.display = "block";
		};
		var url = document.getElementById("reporturl").textContent;
		var message = document.getElementById("reportmsg").value;
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.send("entry.1219009250=" + encodeURIComponent(url) + "&entry.1103734758=" + encodeURIComponent(message));
	}
}
function toggleSettings()
{
	var settingsDiv = document.getElementById("settings");
	var curDisplay = settingsDiv.style.display;
	if(curDisplay == "none")
		settingsDiv.style.display = "block";
	else
		settingsDiv.style.display = "none";
}
function changeSystemClipboard(e)
{
	var checkbox = e.target;
	var value = checkbox.checked ? "true" : "false";
	localStorage["systemclipboard"] = value;
}
function changeAvoidReloads(e)
{
	var checkbox = e.target;
	var value = checkbox.checked ? "true" : "false";
	if(currentOrigin)
		localStorage[currentOrigin] = value;
}
function getTabOrigin(tab)
{
	var url = tab.url;
	var schemeEnd = url.indexOf('://');
	var siteEnd = url.indexOf('/', schemeEnd+3);
	var originRequest = url.substring(0, siteEnd+1) + "*";
	return originRequest;
}
// Get the current permission status for this origin
function askTabStatus(tabs)
{
	if(tabs[0].url.startsWith("file://"))
	{
		document.getElementById("fileurl").style.display = "unset";
		document.getElementById("reportbutton").disabled = true;
		return;
	}
	currentOrigin = getTabOrigin(tabs[0]);
	var avoidReloads = false;
	if(currentOrigin)
		avoidReloads = initAvoidReloads(localStorage[currentOrigin]);
	chrome.runtime.sendMessage({type: "enableOnTab", tabId: tabs[0].id, avoidReloads: avoidReloads});
}
function initSystemClipboard(v)
{
	var checkbox = document.getElementById("systemclipboard");
	if(v == "true")
		checkbox.checked = true;
	else
		checkbox.checked = false;
}
function initAvoidReloads(v)
{
	var checkbox = document.getElementById("avoidreloads");
	if(v == "true")
	{
		checkbox.checked = true;
		return true;
	}
	else
	{
		checkbox.checked = false;
		return false;
	}
}
chrome.tabs.query({active:true, currentWindow: true}, askTabStatus);
initSystemClipboard(localStorage["systemclipboard"]);
document.getElementById("reportbutton").addEventListener("click", sendReport);
document.getElementById("settingsbutton").addEventListener("click", toggleSettings);
document.getElementById("systemclipboard").addEventListener("change", changeSystemClipboard);
document.getElementById("avoidreloads").addEventListener("change", changeAvoidReloads);

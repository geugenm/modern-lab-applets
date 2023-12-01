// Be robust to this script being injected multiple times
var cjInjectDone;
if(!cjInjectDone)
{
cjInjectDone = 1;
// Simplified injection code path if the user has requested to avoid reloads for this origin
// This behavior is useful to support POST based pages whose content might change after a reload
var loaderScript = document.createElement("script");
loaderScript.src = chrome.runtime.getURL("cheerpj/loader.js");
loaderScript.onload = function()
{
	var attachScrip = document.createElement("script");
	attachScrip.src = chrome.runtime.getURL("attach_simple.js");
	document.head.appendChild(attachScrip);
};
document.head.appendChild(loaderScript);
}

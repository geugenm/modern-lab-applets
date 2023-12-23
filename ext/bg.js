function messageHandler(m)
{
	if(m.type == "enableOnTab")
	{
		var scriptToInject = m.avoidReloads ? "inject_simple.js" : "inject.js";
		chrome.scripting.executeScript({target:{tabId: m.tabId}, files: [scriptToInject]})
	}
}
function fetchHandler(e)
{
	e.respondWith(fetch(e.request).then(function(response)
	{
		return response;
	}, function(errResponse)
	{
		// This is either a non-existing file, or a valid directory
		// By convention, directories contains an index.list file
		var originalRequest = e.request;
		var originalUrl = originalRequest.url;
		originalRequest.url = originalUrl + "/index.list";
		return fetch(originalUrl + "/index.list").then(function(r)
		{
			// Valid directory, forge a custom 231 response that will be
			// interpreter by cheerpOS to add a trailing slash
			return new Response(null, {status: 231});
		},function(errResponse)
		{
			// Non-existing file or directory, return a 204 to keep the console clean
			return new Response(null, {status: 204});
		});
	}));
}
chrome.runtime.onMessage.addListener(messageHandler);
self.addEventListener("fetch", fetchHandler);

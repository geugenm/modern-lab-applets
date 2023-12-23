// Skip the leading '?'
function getJNLPUrl()
{
	var q = window.location.search.substr(1);
	var params = q.split('&');
	for(var i=0;i<params.length;i++)
	{
		var p = params[i].split('=');
		if(p[0] == 'url')
			return decodeURIComponent(p[1]);
	}
	return null;
}
function jnlpResolveJar(mp, path)
{
	// Format: /R_N.jar
	var jarParts = path.substr(1,path.indexOf(".")).split('_');
	var resourceIndex = parseInt(jarParts[0]);
	var jarIndex = parseInt(jarParts[1]);
	var jarPath = mp.resourcesElement[resourceIndex].getElementsByTagName("jar")[jarIndex].getAttribute("href");
	if(!jarPath.startsWith("http://") && !jarPath.startsWith("https://"))
	{
		if(path.endsWith(".jar.js"))
		{
			if(mp.jarJSOverride)
				jarPath = mp.jarJSOverride + "/" + jarPath + ".js";
			else
				jarPath = mp.codebase + "/" + jarPath + ".js";
		}
		else
			jarPath = mp.codebase + "/" + jarPath;
	}
	return jarPath;
}
function parseJNLPData(jnlpData, jnlpUrl, retData)
{
	// TODO: Display a confirmation maybe
	var applicationDesc = jnlpData.getElementsByTagName("application-desc");
	var appletDesc = jnlpData.getElementsByTagName("applet-desc");
	var isApplet = false;
	var mainClass = null;
	if(applicationDesc.length)
		mainClass = applicationDesc[0].getAttribute("main-class");
	else if(appletDesc.length)
	{
		mainClass = appletDesc[0].getAttribute("main-class");
		isApplet = true;
	}
	assert(mainClass);
	var resourcesList = jnlpData.getElementsByTagName("resources");
	var classPath = null;
	for(var r=0;r<resourcesList.length;r++)
	{
		var resources = resourcesList[r];
		var jars = resources.getElementsByTagName("jar");
		for(var i=0;i<jars.length;i++)
		{
			// TODO: Is the main attribute useful?
			var thisJar = "/jnlp/" + r + "_" + i + ".jar";
			if(classPath == null)
				classPath = thisJar;
			else
				classPath += ":" + thisJar;
		}
	}
	// Create a special WebFolder to dynamically resolve the JARs
	var jnlpFolder = new CheerpJWebFolder("/jnlp/",loaderPath,false,cheerpjJarJsOverridePath,null,DirectDownloader);
	jnlpFolder.mapPath = jnlpResolveJar;
	jnlpFolder.resourcesElement = resourcesList;
	jnlpFolder.codebase = jnlpData.firstChild.getAttribute("codebase");
	var lastPath = jnlpUrl.lastIndexOf('/');
	var jnlpPrefix = jnlpUrl.substring(0,lastPath+1);
	if(!jnlpFolder.codebase)
	{
		// Use JNLP path by default
		jnlpFolder.codebase = jnlpPrefix;
	}
	cheerpjFSMounts.unshift(jnlpFolder);
	if(isApplet)
	{
		assert(retData);
		retData.classPath = classPath;
		retData.mainClass = mainClass;
	}
	else
	{
		cheerpjSetAppPrefix(jnlpPrefix);
		// Show some information about the Application
		var info = jnlpData.getElementsByTagName("information")[0];
		var title = null;
		var vendor = null;
		var icon = null;
		for(var i=0;i<info.children.length;i++)
		{
			var c=info.children[i];
			switch(c.tagName)
			{
				case "title":
					title = c.textContent;
					break;
				case "vendor":
					vendor = c.textContent;
					break;
				case "icon":
					icon = c.getAttribute("href");
					break;
			}
		}
		var infoTag = document.createElement("div");
		var titleTag = document.createElement("h1");
		titleTag.textContent = title;
		infoTag.appendChild(titleTag);
		if(icon)
		{
			var iconTag = document.createElement("img");
			iconTag.src = icon;
			iconTag.setAttribute("style", "display: inline-block;");
			infoTag.appendChild(iconTag);
		}
		if(vendor)
		{
			var vendorTag = document.createElement("h3");
			vendorTag.textContent = vendor;
			infoTag.appendChild(vendorTag);
		}
		document.body.appendChild(infoTag);
		// Add all arguments from the JNLP file
		var args = [mainClass, classPath];
		for(var i=0;i<applicationDesc[0].children.length;i++)
		{
			var c=applicationDesc[0].children[i];
			if(c.tagName == "argument")
				args.push(c.textContent);
		}
		if(cheerpjAppendedArguments)
		{
			for(var i=0;i<cheerpjAppendedArguments.length;i++)
				args.push(cheerpjAppendedArguments[i]);
		}
		// Initialize JNLP basic services
		cheerpjRunStaticMethod(threads[0], "java/lang/System",cjSystemSetProperty,"java.protocol.handler.pkgs","com.leaningtech.handlers");
		var newService = cheerpjCreateInstance("N3com11leaningtech22JNLPServiceManagerStub", cjJNLPServiceManagerConstructor, jnlpFolder.codebase);
		cheerpjRunStaticMethod(threads[0], "javax/jnlp/ServiceManager", cjJNLPSetServiceManager, newService);
		cheerpjRunMain.apply(null, args);
	}
}
function handleLoadedJNLP(e)
{
	parseJNLPData(e.target.responseXML, jnlpUrl, null);
}
var jnlpUrl = getJNLPUrl();
if(jnlpUrl)
{
	console.log("Load JNLP file",jnlpUrl);
	var xhr = new XMLHttpRequest();
	xhr.open("GET",jnlpUrl);
	xhr.onload=handleLoadedJNLP;
	xhr.responseType="document";
	xhr.overrideMimeType('text/xml');
	xhr.send();
}
function CheerpJNetProvider()
{
	this.controlChannel = null;
	this.state = "PREAUTH";
}
CheerpJNetProvider.prototype.doAuth=function(p)
{
	// Try to create the control channel
	// TODO: wss
	this.controlChannel = new WebSocket("ws://127.0.0.1:8091/auth?something=1");
	this.controlChannel.onopen=function()
	{
		debugger;
	};
	this.controlChannel.onerror=function()
	{
		debugger;
	};
	this.controlChannel.onmessage=function()
	{
		debugger;
	};
	this.controlChannel.onclose=function()
	{
		debugger;
	};
}
CheerpJNetProvider.prototype.hasNetwork=function(p)
{
	debugger
}
var cjNetProvider = new CheerpJNetProvider();
function _ZN3com7avocent3app8security18X509CertificateJNI23ValidateX509CertificateEABIAIN4java4lang6StringACEI()
{
debugger
}
function _ZN3com7avocent3app8security18X509CertificateJNI19SaveX509CertificateEABIN4java4lang6StringEI()
{
debugger
}
function _ZN3com7avocent3app8security18X509CertificateJNI22ExtractX509CertificateEN3com7avocent3app8security11X509CertObjEI()
{
debugger
}
function _ZN3com7avocent3app8security18X509CertificateJNI21ExportX509CertificateEN4java4lang6StringEI()
{
debugger
}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary22GetVirtualMediaVersionEVEN4java4lang6String()
{
debugger
}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary10GetMessageEN3com7avocent2vm3jni15VMSessionStatusEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary7ConnectEN3com7avocent2vm3jni16VMConnectionInfoN4java4lang6StringN4java4lang6StringN4java4lang6StringEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary20RequestConnectCancelEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary22RequestReconnectCancelEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary10DisconnectEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary16GetSessionStatusEN3com7avocent2vm3jni15VMSessionStatusEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary18GetCertificateInfoEN3com7avocent2vm3jni17VMCertificateInfoEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary22SetCertificateResponseEIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary26SetPlainConnectionResponseEIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary18SendPreemptRequestEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary19SendPreemptResponseEIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary12GetDriveListEN3com7avocent2vm3jni11VMDriveListEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary15GetLocalDriveAtEN3com7avocent2vm3jni12VMLocalDriveIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary21RemoveLocalDriveImageEIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary16GetRemoteDriveAtEN3com7avocent2vm3jni13VMRemoteDriveIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary22RequestRemoteDriveListEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary21RequestLocalDriveListEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary15RequestUsbResetEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary16CreateLocalDriveEN4java4lang6StringEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary19GetLocalDriveObjectEN4java4lang6StringN3com7avocent2vm3jni12VMLocalDriveEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary8MapDriveEIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary13MapLocalDriveEIIZEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary19MapLocalDriveObjectEIN3com7avocent2vm3jni12VMLocalDriveEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary10UnmapDriveEIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary11CreateImageEN4java4lang6StringN4java4lang6StringN4java4lang6StringZEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary17CreateImageCancelEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary24CreateImageGetPercentageEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary22SetVirtualDeviceCountsEIIIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary23SetLocalDriveForMappingEIIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary21SetLocalDriveReadOnlyEIIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary22MapSelectedLocalDrivesEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary17UploadServerImageEIN4java4lang6StringEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary23UploadServerImageCancelEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary22RequestServerImageListEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary18GetServerImageListEN3com7avocent2vm3jni17VMServerImageListEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary16GetServerImageAtEN3com7avocent2vm3jni13VMServerImageIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary23RequestServerImageSpaceEVEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary24RequestServerImageDeleteEIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary16AutomountResultsEIEI(){debugger}
function _ZN3com7avocent2vm3jni17RPVMNativeLibrary24RequestLocalDriveRefreshEIZEI(){debugger}
function _ZN3com7avocent3kvm14nativekeyboard9NativeKVM15determineDriverEVEI()
{
debugger
}

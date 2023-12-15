## Modernized computer applets for semiconductors course.

<div align="center">
  <a href="https://github.com/rfapplets/modern-lab-comp-applets">
    <img src=".github/img/logo.png" alt="Logo" width="80" height="80">
  </a>
  <h3 align="center">Applets REMASTERED</h3>

  <p align="center">
    This repository has been established with the primary objective of enhancing the functionality of applets in 2023, ensuring seamless execution for all users, thereby facilitating a smooth and trouble-free experience. 
    <br />
    <a href="https://github.com/rfapplets/modern-lab-comp-applets/tree/master/docs"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://www.acsu.buffalo.edu/~wie/applet/diffusion/diffusion.html">View Demo</a>
    ·
    <a href="https://github.com/rfapplets/modern-lab-comp-applets/issues">Report Bug</a>
    ·
    <a href="https://github.com/rfapplets/modern-lab-comp-applets/issues">Request Feature</a>
  </p>
</div>

<center>
<img src="https://github.com/leaningtech/cheerpj-appletrunner/blob/master/media/cheerpj_applet_demo1.gif?raw=true" alt="Gif demonstration of cheerpj">
</center>

### Prerequisites

Before using this script, install the CheerpJ plugin by visiting
the [CheerpJ Applet Runner](https://chromewebstore.google.com/detail/cheerpj-applet-runner/bbmolahhldcbngedljfadjlognfaaein)
link in your browser(Must support Chrome Extensions).

### Usage

#### Average user

See the documentation in docs tutorial.

#### Advanced user

You will need python3. Run the following command in your terminal:

```bash
python3 scripts/start_server_ui.py -dir=src/
```

Start the server and press open url button, now you can browse through applets!

### Functionality

- Opens the Wikipedia link in your default browser.
- Finds an available port starting from 8000 and starts an HTTP server on it.
- Logs the server's progress using Python's built-in logging module (logging level set to DEBUG).
- Provides a server URL in the format `http://<ip_address>:<port>` for browsing the server.

### UI Features

- **Start Button:** Initiates the server.
- **Stop Button:** Terminates the running server.
- **Open URL Button:** Opens the server URL in the browser.

### Note

- This script is intended for Unix & Windows systems and may not work correctly on other operating systems.

### How does it work?

The Java runtime invokes an interceptor and converter to WebAssembly. This process allows Java to be compiled to
WebAssembly and run in a web browser. WebAssembly is a binary format that enables the execution of code by modern web
browsers at near-native speeds. When a web page loads a WebAssembly module, the browser downloads the module's binary
file and compiles it to machine code using a virtual machine called the WebAssembly Runtime, which is integrated into
the browser's JavaScript engine

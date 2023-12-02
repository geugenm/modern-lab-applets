## Python Server with UI

<div align="center">
  <a href="https://github.com/project-gecko-legacy">
    <img src=".github/img/logo.png" alt="Logo" width="80" height="80">
  </a>
  <h3 align="center">Applets REMASTERED</h3>

  <p align="center">
    This repository was created in order to improve applets experience in 2023. So everyone will be able to run them without problems
    <br />
    <a href="https://github.com/geugenm/modern-lab-comp-applets/tree/master/docs"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://www.acsu.buffalo.edu/~wie/applet/diffusion/diffusion.html">View Demo</a>
    ·
    <a href="https://github.com/geugenm/modern-lab-comp-applets/issues">Report Bug</a>
    ·
    <a href="https://github.com/geugenm/modern-lab-comp-applets/issues">Request Feature</a>
  </p>
</div>


### Prerequisites
Before using this script, install the CheerpJ plugin by visiting the [CheerpJ Applet Runner](https://chromewebstore.google.com/detail/cheerpj-applet-runner/bbmolahhldcbngedljfadjlognfaaein) link in your browser.

### Usage
To run this script, use the following command in your terminal:

```bash
python3 scripts/start_server_ui.py
```


### Functionality
- Opens the Wikipedia link in your default browser.
- Finds an available port starting from 8000 and starts an HTTP server on it.
- Logs the server's progress using Python's built-in logging module (logging level set to DEBUG).
- Provides a server URL in the format `http://<ip_address>:<port>` for browsing the server.

### UI Features
- **Start Button:** Initiates the server.
- **Stop Button:** Terminates the running server.
- **Open URL Button:** Opens the server URL in the browser.
- **Check Button:** Checks if the server is running.

### Note
- This script is intended for Linux systems and may not work correctly on other operating systems.

### How to Use
1. Clone this repository.
2. Install the CheerpJ plugin.
3. Run the script using the command mentioned above.

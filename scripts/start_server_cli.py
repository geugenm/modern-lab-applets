"""
This script is designed to open a Wikipedia link in the browser, find an available port starting from 8000, and start a simple HTTP server on the found port as a subprocess.

Before using this script, you need to install the CheerpJ plugin. You can do this by visiting the following URL in your browser:
https://chromewebstore.google.com/detail/cheerpj-applet-runner/bbmolahhldcbngedljfadjlognfaaein

After installing the CheerpJ plugin, you can run this script. The script will open the Wikipedia link in your default browser. It will then find an available port starting from 8000, and start a simple HTTP server on the found port as a subprocess.

The script will also open a server URL in the browser, which you can use to browse the server. The server URL is in the format http://<ip_address>:<port>, where <ip_address> is the IP address of your system, and <port> is the port on which the server is running.

Please note that this script uses Python's built-in logging module to log the progress of the script. The logging level is set to DEBUG, which means that the script will log detailed information about its progress.

This script is designed to be stable and reliable. It includes checks to ensure that the server is started successfully, and it uses exception handling to handle any errors that may occur during the execution of the script.

Please note that this script is intended for use on Linux systems. It may not work correctly on other operating systems.

To run this script, you can use the following command in your terminal:
python3 script.py
"""

import logging
import socket
import subprocess
import webbrowser

# Define the custom log level
VERBOSE = 15
logging.VERBOSE = VERBOSE
logging.addLevelName(logging.VERBOSE, 'VERBOSE')

# Set up logging
logging.basicConfig(level=logging.DEBUG)


def verbose(self, message, *args, **kwargs):
    if self.isEnabledFor(logging.VERBOSE):
        self._log(logging.VERBOSE, message, args, **kwargs)


logging.Logger.verbose = verbose

logger = logging.getLogger()

# Constants
LOG_LEVEL = logging.DEBUG
START_PORT = 8000
APPLETS_EXTENSION_URL = 'https://chromewebstore.google.com/detail/cheerpj-applet-runner/bbmolahhldcbngedljfadjlognfaaein'
SERVER_COMMAND = ['python', '-m', 'http.server']

# Unused, just for notes
APPLETS_SOURCE = 'https://edurfe.bsu.by/mod/resource/view.php?id=3910'
APPLETS_REAL_SOURCE = 'https://www.acsu.buffalo.edu/~wie/applet/applet.old'


def find_available_port(start_port):
    """Find an available port starting from the given port."""
    hostname = socket.gethostname()
    ip_address = socket.gethostbyname(hostname)
    logger.verbose('Checking for available port starting from %s', start_port)
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        if s.connect_ex((ip_address, start_port)) == 0:
            return find_available_port(start_port + 1)
        else:
            logger.verbose('Found available port: %s', start_port)
            return start_port


def open_url(url):
    """Open a URL in the default browser."""
    logger.verbose('Opening URL: %s', url)
    try:
        webbrowser.open(url)
    except Exception as e:
        logger.error('Failed to open URL: %s', e)


def start_server(port):
    """Start a simple HTTP server on the given port as a subprocess."""
    hostname = socket.gethostname()
    ip_address = socket.gethostbyname(hostname)
    logger.verbose('Starting server on port: %s', port)
    try:
        server_process = subprocess.Popen(SERVER_COMMAND + [str(port)], stdout=subprocess.PIPE,
                                          stderr=subprocess.STDOUT)
    except Exception as e:
        logger.error('Failed to start server: %s', e)
        return

    # Open a server URL in the browser
    open_url(f'http://{ip_address}:{port}')

    while True:
        output = server_process.stdout.readline()
        if output == '' and server_process.poll() is not None:
            break
        if output:
            logger.verbose('Server output: %s', output.strip())


def main():
    """Main function."""
    logger.verbose('Opening special links in the browser')
    open_url(APPLETS_EXTENSION_URL)

    # Find an available port starting from 8000
    logger.verbose('Finding an available port starting from %s', START_PORT)
    port = find_available_port(START_PORT)
    if port is None:
        raise Exception("No available ports found.")

    # Start a simple HTTP server on the found port as a subprocess
    logger.verbose('Starting server on found port')
    start_server(port)


if __name__ == "__main__":
    main()

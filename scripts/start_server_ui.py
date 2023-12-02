import http.server
import socketserver
import webbrowser
import threading
import tkinter as tk
import argparse
import os
import http.client
import logging
from datetime import datetime
from tkinter import messagebox

class ServerUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Python Server")

        self.directory = tk.StringVar(value="")  # Default directory
        self.port = tk.StringVar(value="8080")  # Default port
        self.status_label = tk.Label(self.root, text="STATUS: OFF", fg="red")
        self.status_label.pack()

        self.start_button = tk.Button(self.root, text="Start", command=self.start_server)
        self.start_button.pack()

        self.stop_button = tk.Button(self.root, text="Stop", command=self.stop_server, state=tk.DISABLED)
        self.stop_button.pack()

        self.open_button = tk.Button(self.root, text="Open URL", command=self.open_url, state=tk.DISABLED)
        self.open_button.pack()

        tk.Label(self.root, text="Directory to host:").pack()
        self.directory_entry = tk.Entry(self.root, textvariable=self.directory)
        self.directory_entry.pack()

        tk.Label(self.root, text="Port:").pack()
        self.port_entry = tk.Entry(self.root, textvariable=self.port)
        self.port_entry.pack()

        self.setup_logging()  # Setup logging

    def setup_logging(self):
        self.logger = logging.getLogger("PythonServer")
        self.logger.setLevel(logging.INFO)

        formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')

        # Log to file
        log_directory = "logs"
        if not os.path.exists(log_directory):
            os.makedirs(log_directory)
        timestamp = datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
        self.log_file = os.path.join(log_directory, f"log_{timestamp}.txt")
        file_handler = logging.FileHandler(self.log_file)
        file_handler.setFormatter(formatter)
        self.logger.addHandler(file_handler)

        self.logger.info(f"Server started at {timestamp}")

    def start_server(self):
        self.start_button.config(state=tk.DISABLED)
        self.directory_entry.config(state=tk.DISABLED)
        self.port_entry.config(state=tk.DISABLED)

        directory = self.directory.get()
        if not os.path.exists(directory):
            self.logger.info(f"Directory '{directory}' does not exist.")
            messagebox.showinfo("Error", f"Directory '{directory}' does not exist.")
            self.start_button.config(state=tk.NORMAL)
            self.directory_entry.config(state=tk.NORMAL)
            self.port_entry.config(state=tk.NORMAL)
            return

        port = int(self.port.get())

        self.server = socketserver.TCPServer(("localhost", port), http.server.SimpleHTTPRequestHandler)
        self.server_thread = threading.Thread(target=self.server.serve_forever)
        self.server_thread.daemon = True
        self.server_thread.start()

        self.stop_button.config(state=tk.NORMAL)
        self.open_button.config(state=tk.NORMAL)
        self.status_label.config(text="STATUS: ON", fg="green")
        self.logger.info(f"Server started at http://localhost:{port} serving directory {directory}")

    def stop_server(self):
        if hasattr(self, 'server'):
            self.server.shutdown()
            self.server.server_close()
            self.stop_button.config(state=tk.DISABLED)
            self.open_button.config(state=tk.DISABLED)
            self.status_label.config(text="STATUS: OFF", fg="red")
            self.directory_entry.config(state=tk.NORMAL)
            self.port_entry.config(state=tk.NORMAL)
            self.start_button.config(state=tk.NORMAL)
            self.logger.info("Server stopped")

    def open_url(self):
        port = int(self.port.get())
        webbrowser.open_new_tab(f"http://localhost:{port}")


def run_server(directory, port, log_file):
    root = tk.Tk()
    server_ui = ServerUI(root)
    server_ui.directory.set(directory)
    server_ui.port.set(port)
    server_ui.log_file = log_file
    root.mainloop()


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Python Server with UI")
    parser.add_argument("-dir", "--directory", type=str, default="", help="Directory to host")
    parser.add_argument("-p", "--port", type=int, default=8080, help="Port to run the server on")
    parser.add_argument("-log", "--logfile", type=str, default="log.txt", help="Log file name")
    args = parser.parse_args()

    run_server(args.directory, args.port, args.logfile)

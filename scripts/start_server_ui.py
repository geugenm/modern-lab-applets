import argparse
import http.client
import http.server
import logging
import os
import socketserver
import threading
import tkinter as tk
import webbrowser
from datetime import datetime
from tkinter import messagebox
from tkinter import ttk


class ServerUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Java WebAssembly Translation Server")

        self.style = ttk.Style()
        self.style.theme_use('clam')  # Using a theme that resembles a modern style

        self.directory = tk.StringVar(value="")  # Default directory
        self.port = tk.StringVar(value="8080")  # Default port

        self.status_label = ttk.Label(self.root, text="STATUS: OFF", foreground="red", font=("Arial", 12, "bold"))
        self.status_label.pack(pady=10)

        self.start_button = ttk.Button(self.root, text="Start", command=self.start_server)
        self.start_button.pack(pady=5)

        self.stop_button = ttk.Button(self.root, text="Stop", command=self.stop_server, state=tk.DISABLED)
        self.stop_button.pack(pady=5)

        self.open_button = ttk.Button(self.root, text="Open URL", command=self.open_url, state=tk.DISABLED)
        self.open_button.pack(pady=5)

        ttk.Label(self.root, text="Directory to open:").pack()
        self.directory_entry = ttk.Entry(self.root, textvariable=self.directory)
        self.directory_entry.pack(pady=5)

        ttk.Label(self.root, text="Port:").pack()
        self.port_entry = ttk.Entry(self.root, textvariable=self.port)
        self.port_entry.pack(pady=5)

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
        self.status_label.config(text="STATUS: ON", foreground="green")
        self.logger.info(f"Server started at http://localhost:{port} serving directory {directory}")

    def stop_server(self):
        if hasattr(self, 'server'):
            self.server.shutdown()
            self.server.server_close()
            self.stop_button.config(state=tk.DISABLED)
            self.open_button.config(state=tk.DISABLED)
            self.status_label.config(text="STATUS: OFF", foreground="red")
            self.directory_entry.config(state=tk.NORMAL)
            self.port_entry.config(state=tk.NORMAL)
            self.start_button.config(state=tk.NORMAL)
            self.logger.info("Server stopped")

    def open_url(self):
        directory = self.directory.get()
        port = int(self.port.get())
        if not os.path.exists(directory):
            messagebox.showinfo("Error", f"Directory '{directory}' does not exist.")
            return
        webbrowser.open_new_tab(f"http://localhost:{port}/{directory}")


def run_server(directory, port, log_file):
    root = tk.Tk()
    server_ui = ServerUI(root)
    server_ui.directory.set(directory)
    server_ui.port.set(port)
    server_ui.log_file = log_file
    root.mainloop()


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Java WebAssembly Translation Server with UI")
    parser.add_argument("-dir", "--directory", type=str, default="", help="Directory to open")
    parser.add_argument("-p", "--port", type=int, default=8080, help="Port to run the server on")
    parser.add_argument("-log", "--logfile", type=str, default="log.txt", help="Log file name")
    args = parser.parse_args()

    run_server(args.directory, args.port, args.logfile)

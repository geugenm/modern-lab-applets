import argparse
import http.server
import socket
import socketserver
import threading
import tkinter as tk
import webbrowser
from tkinter import ttk

# Constants
DEFAULT_PORT: int = 25355
DEFAULT_DIRECTORY: str = "."
LOG_DIRECTORY: str = "logs/"
HTTP_PREFIX: str = "http://"
SERVER_ADDRESS: str = socket.gethostbyname(socket.gethostname())


# Model
class ServerModel:
    TIMEOUT: int = 2

    def __init__(self, directory: str = DEFAULT_DIRECTORY, port: int = DEFAULT_PORT):
        self.directory = directory
        self.port = port
        self.server = None
        self.server_thread = None

    def start_server(self):
        self.server = socketserver.TCPServer((SERVER_ADDRESS, self.port), http.server.SimpleHTTPRequestHandler)
        self.server_thread = threading.Thread(target=self.server.serve_forever)
        self.server_thread.daemon = True
        self.server_thread.start()

    def stop_server(self):
        if self.server:
            self.server.shutdown()
            self.server_thread.join(timeout=self.TIMEOUT)
            self.server.server_close()


# View
class ServerView:
    WINDOW_TITLE: str = "Webassembly translation server"
    START_BUTTON_TEXT: str = "Start"
    STOP_BUTTON_TEXT: str = "Stop"
    OPEN_URL_BUTTON_TEXT: str = "Open URL"
    BUTTON_WIDTH: int = 12
    BUTTON_FONT_SIZE: int = 15
    WINDOW_HEIGHT: int = 100

    def __init__(self, root: tk.Tk):
        self.root = root
        self.root.title(self.WINDOW_TITLE)

        window_width = len(self.WINDOW_TITLE) * self.BUTTON_WIDTH
        self.root.geometry(f"{window_width}x{self.WINDOW_HEIGHT}")

        self.start_button = ttk.Button(self.root, text=self.START_BUTTON_TEXT, width=self.BUTTON_WIDTH)
        self.stop_button = ttk.Button(self.root, text=self.STOP_BUTTON_TEXT, width=self.BUTTON_WIDTH)
        self.open_button = ttk.Button(self.root, text=self.OPEN_URL_BUTTON_TEXT, width=self.BUTTON_WIDTH)

        self.start_button.pack()
        self.stop_button.pack()
        self.open_button.pack()

        self.status_label = ttk.Label(self.root)
        self.status_label.pack()


# Controller
class ServerController:
    STATUS_ON: str = "STATUS: ON"
    STATUS_OFF: str = "STATUS: OFF"
    GREEN: str = "green"
    RED: str = "red"

    def __init__(self, root, model: ServerModel):
        self.model = model
        self.view = ServerView(root)
        self.view.start_button.config(command=self.start_server)
        self.view.stop_button.config(command=self.stop_server)
        self.view.open_button.config(command=self.open_url)

    def start_server(self):
        self.model.start_server()
        self.view.status_label.config(text=self.STATUS_ON, foreground=self.GREEN)
        self.view.start_button.config(state="disabled")

    def stop_server(self):
        self.model.stop_server()
        self.view.status_label.config(text=self.STATUS_OFF, foreground=self.RED)
        self.view.start_button.config(state="normal")

    def open_url(self):
        webbrowser.open_new_tab(f"{HTTP_PREFIX}{SERVER_ADDRESS}:{self.model.port}/{self.model.directory}")


def main():
    parser = argparse.ArgumentParser(description='Start a server.')
    parser.add_argument('-p', '--port', type=int, default=DEFAULT_PORT, help='The port to use.')
    parser.add_argument('-s', '--source', default=DEFAULT_DIRECTORY, help='The source directory.')
    args = parser.parse_args()

    root = tk.Tk()
    model = ServerModel(directory=args.source, port=args.port)
    controller = ServerController(root, model)
    root.mainloop()


if __name__ == "__main__":
    main()

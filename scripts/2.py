import webbrowser
import socket
import subprocess
import logging
import sys
import tkinter as tk
from tkinter import messagebox

# Set up logging
logging.basicConfig(level=logging.INFO)

# Constants
START_PORT = 8000
APPLETS_EXTENSION_URL = 'https://chromewebstore.google.com/detail/cheerpj-applet-runner/bbmolahhldcbngedljfadjlognfaaein'
SERVER_COMMAND = ['python', '-m', 'http.server']

# Function to find an available port
def find_available_port(start_port):
  hostname = socket.gethostname()
  ip_address = socket.gethostbyname(hostname)
  with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
      if s.connect_ex((ip_address, start_port)) == 0:
          return find_available_port(start_port + 1)
      else:
          return start_port

# Function to start the server
def start_server(port):
  hostname = socket.gethostname()
  ip_address = socket.gethostbyname(hostname)
  try:
      server_process = subprocess.Popen(SERVER_COMMAND + [str(port)], stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
  except Exception as e:
      messagebox.showerror("Error", f"Failed to start server: {e}")
      return None
  return server_process

# Function to open the URL
def open_url(url):
  try:
      webbrowser.open(url)
  except Exception as e:
      messagebox.showerror("Error", f"Failed to open URL: {e}")

# Function to handle the start server button
def handle_start_server():
  global server_process
  if server_process is not None:
      messagebox.showinfo("Server", "Server is already running.")
      return
  global port
  port = find_available_port(START_PORT)
  server_process = start_server(port)
  if server_process is not None:
      hostname = socket.gethostname()
      ip_address = socket.gethostbyname(hostname)
      messagebox.showinfo("Server", f"Server started on http://{ip_address}:{port}")
      logging.info(f"Server started on http://{ip_address}:{port}")

# Function to handle the stop server button
def handle_stop_server():
  global server_process
  if server_process is None:
      messagebox.showinfo("Server", "Server is not running.")
      return
  server_process.kill()
  server_process = None
  messagebox.showinfo("Server", "Server stopped.")
  logging.info("Server stopped.")

# Function to handle the open extension button
def handle_open_extension():
  open_url(APPLETS_EXTENSION_URL)
  logging.info(f"Opened URL: {APPLETS_EXTENSION_URL}")

# Function to handle the open server URL button
def handle_open_server_url():
  global server_process
  if server_process is None:
      messagebox.showinfo("Server", "Server is not running.")
      return
  hostname = socket.gethostname()
  ip_address = socket.gethostbyname(hostname)
  open_url(f'http://{ip_address}:{port}')
  logging.info(f"Opened server URL: http://{ip_address}:{port}")

# Main function
def main():
  global server_process
  server_process = None
  root = tk.Tk()
  root.title("Script Control")
  btn_open_extension = tk.Button(root, text="Open Extension URL", command=handle_open_extension)
  btn_open_extension.pack()
  btn_start_server = tk.Button(root, text="Start Server", command=handle_start_server)
  btn_start_server.pack()
  btn_stop_server = tk.Button(root, text="Stop Server", command=handle_stop_server)
  btn_stop_server.pack()
  btn_open_server_url = tk.Button(root, text="Open Server URL", command=handle_open_server_url)
  btn_open_server_url.pack()
  root.mainloop()

# Help function
def help():
  print("Usage: python3 script.py [--cli]")
  print("--cli: Run the script in command line mode.")
  print("--help: Show this help message.")

if __name__ == "__main__":
  if len(sys.argv) > 1:
      if sys.argv[1] == "--cli":
          port = find_available_port(START_PORT)
          server_process = start_server(port)
          hostname = socket.gethostname()
          ip_address = socket.gethostbyname(hostname)
          print(f"Server started on http://{ip_address}:{port}")
          logging.info(f"Server started on http://{ip_address}:{port}")
      elif sys.argv[1] == "--help":
          help()
  else:
      main()

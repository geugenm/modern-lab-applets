import logging
import os
import platform
import shutil
import subprocess


def setup_logger():
    logging.basicConfig(filename='logs/create_release.log', level=logging.INFO,
                        format='%(asctime)s - %(levelname)s - %(message)s')


def clean_build():
    # Delete .build directory if it exists
    if os.path.exists('.build'):
        shutil.rmtree('.build', ignore_errors=True)


def copy_files():
    # Define source and destination directories
    source_dirs = ['docs', 'src']
    exclude_dirs = ['decompiled', 'logs']
    destination_dir = '.build'

    # Check if required directories exist
    if not all(os.path.exists(dir) for dir in ['docs', 'src', 'scripts']):
        logging.error("Required directories (docs/, src/, scripts/) not found.")
        return

    # Create destination directory if it doesn't exist
    os.makedirs(destination_dir, exist_ok=True)

    # Copy files from source to destination, excluding certain directories
    for src_dir in source_dirs:
        for root, dirs, files in os.walk(src_dir):
            if any(exclude_dir in root for exclude_dir in exclude_dirs):
                continue
            for file in files:
                src_file = os.path.join(root, file)
                dest_file = os.path.join(destination_dir, os.path.relpath(src_file, start=src_dir))
                os.makedirs(os.path.dirname(dest_file), exist_ok=True)
                shutil.copy(src_file, dest_file)
                logging.info(f"Copied {src_file} to {dest_file}")


def start_compile():
    # Change working directory to scripts/
    os.chdir('scripts')

    # Start subprocess to run compile_to_exe.py with flags
    logging.info("Starting compile_to_exe.py...")
    compile_process = subprocess.run(['python', 'compile_to_exe.py', 'start_server_ui.py', '--config=release'],
                                     capture_output=True, text=True)

    if compile_process.returncode != 0:
        logging.error("compile_to_exe.py failed. See below for details:")
        logging.error(compile_process.stdout)
        logging.error(compile_process.stderr)
        return

    # Move the compiled file to .build directory
    current_os = platform.system().lower()
    compiled_file = f".build/release/start_server_ui.exe"  # Replace with actual extension
    shutil.move(compiled_file, f"../.build/start_server_ui.exe")
    logging.info(f"Moved {compiled_file} to .build folder")

    # Remove unnecessary directories and files
    shutil.rmtree('build', ignore_errors=True)
    shutil.rmtree('dist', ignore_errors=True)
    shutil.rmtree('.build', ignore_errors=True)
    os.remove('start_server_ui.spec')  # Remove start_server_ui.spec file
    logging.info("Cleaned up build artifacts")


# Main execution
if __name__ == "__main__":
    setup_logger()
    clean_build()
    copy_files()
    start_compile()

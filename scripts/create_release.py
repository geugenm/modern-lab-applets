import logging
import os
import shutil
import subprocess

LOG_FILE = 'logs/create_release.log'
LOG_LEVEL = logging.INFO
LOG_FORMAT = '%(asctime)s - %(levelname)s - %(message)s'
BUILD_DIR = '.build'
SOURCE_DIRS = ['docs', 'src']
EXCLUDE_DIRS = ['decompiled', 'logs']
COMPILE_SCRIPT = 'compile_to_exe.py'
SERVER_UI_SCRIPT = 'start_server_ui.py'
WINDOWS_COMPILED_SCRIPT = 'start_server_ui.exe'
CONFIG = 'debug'
SPEC_FILE = 'start_server_ui.spec'


def setup_logger():
    logging.basicConfig(filename=LOG_FILE, level=LOG_LEVEL, format=LOG_FORMAT)


def clean_build():
    if os.path.exists(BUILD_DIR):
        shutil.rmtree(BUILD_DIR, ignore_errors=True)


def copy_files():
    if not all(os.path.exists(dir) for dir in SOURCE_DIRS + ['scripts']):
        logging.error("Required directories (docs/, src/, scripts/) not found.")
        return

    os.makedirs(BUILD_DIR, exist_ok=True)

    for src_dir in SOURCE_DIRS:
        for root, _, files in os.walk(src_dir):
            if any(exclude_dir in root for exclude_dir in EXCLUDE_DIRS):
                continue
            for file in files:
                src_file = os.path.join(root, file)
                dest_file = os.path.join(BUILD_DIR, os.path.relpath(src_file, start=src_dir))
                os.makedirs(os.path.dirname(dest_file), exist_ok=True)
                shutil.copy(src_file, dest_file)
                logging.info(f"Copied {src_file} to {dest_file}")


def start_compile():
    original_dir = os.getcwd()
    os.chdir('scripts')

    logging.info("Starting compile_to_exe.py...")
    compile_process = subprocess.run(['python', COMPILE_SCRIPT, SERVER_UI_SCRIPT, f'--config={CONFIG}'],
                                     capture_output=True, text=True)

    if compile_process.returncode != 0:
        logging.error("compile_to_exe.py failed. See below for details:")
        logging.error(compile_process.stdout)
        logging.error(compile_process.stderr)
        return

    compiled_file = f"{BUILD_DIR}/{CONFIG}/{WINDOWS_COMPILED_SCRIPT}"
    shutil.move(compiled_file, f"../{BUILD_DIR}/{WINDOWS_COMPILED_SCRIPT}")
    logging.info(f"Moved {compiled_file} to {BUILD_DIR} folder")

    shutil.rmtree('build', ignore_errors=True)
    shutil.rmtree('dist', ignore_errors=True)
    shutil.rmtree('.build', ignore_errors=True)
    os.remove(SPEC_FILE)
    logging.info("Cleaned up build artifacts")

    os.chdir(original_dir)


if __name__ == "__main__":
    setup_logger()
    clean_build()
    copy_files()
    start_compile()

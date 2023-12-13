import argparse
import os
import shutil
import subprocess
import sys
from typing import List

BUILD_DIR = ".build"
DIST_DIR = "dist"
PYINSTALLER = "pyinstaller"
ONEFILE_FLAG = "--onefile"
NOCONSOLE_FLAG = "--noconsole"
EXE_EXT = ".exe"

def compile_exe(script_file: str, config: str) -> None:
    output_directory = os.path.join(BUILD_DIR, config)
    os.makedirs(output_directory, exist_ok=True)

    # PyInstaller flags
    pyinstaller_flags = [ONEFILE_FLAG]

    if config == "debug":
        pyinstaller_flags.append("--debug")
    else:
        pyinstaller_flags.append(NOCONSOLE_FLAG)

    try:
        subprocess.run([PYINSTALLER, *pyinstaller_flags, script_file])
    except FileNotFoundError:
        print(f"{PYINSTALLER} not found. Please install {PYINSTALLER} to proceed.")
        sys.exit(1)

    # Move the compiled executable to the output directory
    script_name = os.path.splitext(os.path.basename(script_file))[0]
    exe_name = f"{script_name}{EXE_EXT}"
    shutil.move(os.path.join(DIST_DIR, exe_name), os.path.join(output_directory, exe_name))

def main() -> None:
    parser = argparse.ArgumentParser(description="Compile and move executable based on configuration")
    parser.add_argument("script_file", help="Python script file to compile")
    parser.add_argument("--config", choices=["release", "debug"], default="debug",
                        help="Configuration type (release or debug, default is debug)")
    args = parser.parse_args()

    compile_exe(args.script_file, args.config)

if __name__ == "__main__":
    main()
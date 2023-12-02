import argparse
import os
import shutil
import subprocess
import sys


def compile_exe(script_file, config):
    output_directory = os.path.join(".build", config)
    os.makedirs(output_directory, exist_ok=True)

    # PyInstaller flags
    pyinstaller_flags = ["--onefile", "--noconsole"]

    if config == "debug":
        pyinstaller_flags.remove("--noconsole")
        # Add debug flags specific to your project if needed
        # Example: pyinstaller_flags.extend(["--debug"])

    try:
        subprocess.run(["pyinstaller", *pyinstaller_flags, script_file])
    except FileNotFoundError:
        print("PyInstaller not found. Please install PyInstaller to proceed.")
        sys.exit(1)

    # Move the compiled executable to the output directory
    script_name = os.path.splitext(os.path.basename(script_file))[0]
    exe_name = f"{script_name}.exe"
    shutil.move(os.path.join("dist", exe_name), os.path.join(output_directory, exe_name))


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Compile and move executable based on configuration")
    parser.add_argument("script_file", help="Python script file to compile")
    parser.add_argument("--config", choices=["release", "debug"], default="debug",
                        help="Configuration type (release or debug, default is debug)")
    args = parser.parse_args()

    compile_exe(args.script_file, args.config)

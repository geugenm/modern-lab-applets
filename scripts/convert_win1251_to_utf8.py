# Import the necessary modules
import argparse
import chardet
import codecs
import logging
import os

# Define the source and target encoding
source_encoding = "windows-1251"
target_encoding = "utf-8"

# Define the log file name and format
log_file = "conversion.log"
log_format = "%(asctime)s - %(levelname)s - %(message)s"

# Configure the logging module
logging.basicConfig(filename=log_file, level=logging.INFO, format=log_format)


# Define a function to convert a file from windows-1251 to utf-8
def convert_file(filepath):
    # Open the file in binary mode and detect its encoding
    with open(filepath, "rb") as f:
        rawdata = f.read()
        result = chardet.detect(rawdata)
        encoding = result["encoding"]
    # If the encoding is windows-1251, convert it to utf-8
    if encoding == source_encoding:
        # Open the file in windows-1251 mode and read its contents
        with codecs.open(filepath, "r", encoding=source_encoding) as f:
            data = f.read()
        # Open the file in utf-8 mode and write the converted contents
        with codecs.open(filepath, "w", encoding=target_encoding) as f:
            f.write(data)
        # Log a message indicating the conversion
        logging.info("Converted {} from {} to {}".format(filepath, source_encoding, target_encoding))
    # Otherwise, skip the file
    else:
        # Log a message indicating the skipping
        logging.info("Skipped {} with encoding {}".format(filepath, encoding))


# Define a function to scan a directory recursively and convert all the files
def scan_directory(directory):
    # Loop through all the files and subdirectories in the directory
    for entry in os.scandir(directory):
        # If the entry is a file, convert it
        if entry.is_file():
            convert_file(entry.path)
        # If the entry is a directory, scan it recursively
        elif entry.is_dir():
            scan_directory(entry.path)


# Create the parser
parser = argparse.ArgumentParser(description="Convert files from windows-1251 to utf-8")
parser.add_argument("directory", type=str, help="The directory to scan and convert")


def main():
    # Parse the arguments
    args = parser.parse_args()

    # Get the directory where the script is launched
    directory = args.directory

    # Check if the directory exists
    if os.path.exists(directory):
        # Scan the directory and convert all the files
        scan_directory(directory)
    else:
        print("The directory does not exist.")


if __name__ == "__main__":
    main()

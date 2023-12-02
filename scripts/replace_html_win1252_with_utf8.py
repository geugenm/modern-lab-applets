import os
import fileinput
import argparse
import logging


def replace_charset_in_file(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as file:
            content = file.read()

        with open(file_path, 'w', encoding='utf-8') as file:
            new_content = content.replace('charset=windows-1251', 'charset=utf-8')
            file.write(new_content)

    except UnicodeDecodeError:
        logging.warning(f"Unable to decode characters in file: {file_path}. Skipping...")
    except OSError as e:
        logging.error(f"Error processing file: {file_path}. {e}")


def replace_charset_in_directory(directory):
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith('.html') or file.endswith('.htm'):
                file_path = os.path.join(root, file)
                logging.info(f"Processing file: {file_path}")
                replace_charset_in_file(file_path)


def main():
    parser = argparse.ArgumentParser(description='Search and replace charset in HTML files.')
    parser.add_argument('directory', help='Directory path where the replacement will be performed.')
    args = parser.parse_args()

    logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(message)s')

    directory = args.directory
    if not os.path.isdir(directory):
        logging.error(f"Invalid directory: {directory}")
        return

    logging.info(f"Starting charset replacement in directory: {directory}")
    replace_charset_in_directory(directory)
    logging.info("Charset replacement completed.")


if __name__ == "__main__":
    main()

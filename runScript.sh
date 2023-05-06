#!/bin/bash

# Get the absolute path of the script
SCRIPT_PATH="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

# Print the script's location
echo "The script is located in: $SCRIPT_PATH"

FILE_LIST=$(ls "$SCRIPT_PATH")

# Print the list of files and directories
echo "Files and directories in the current location:"
echo "$FILE_LIST"

mvn clean test
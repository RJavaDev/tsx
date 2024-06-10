#!/bin/bash

# Change to the directory where your Project JAR file is located
cd /root/tsx/target/

# Check if the project is already running on port (8080)
nc -z localhost 8080

if [ $? -ne 0 ]; then
    # Port is not open, start the Java project using tmux
    echo "Port 8080 is not open. Starting Project..."
    tmux new-session -d -s tsx java -jar tsx-0.0.1-SNAPSHOT.jar
else
    echo "Project is already running on port 8080."
fi
#!/bin/bash

JAR_NAME="tsx-1.1.5.jar"
JAR_PATH="/root/tsx/$JAR_NAME"
SESSION_NAME="tsx"
LOG_FILE="/root/tsx/tsx.log"
PORT=9090

# Jar fayl mavjudligini tekshirish
if [ ! -f "$JAR_PATH" ]; then
  echo "ERROR: $JAR_PATH topilmadi!"
  exit 1
fi

# Tmux sessiyasi mavjudligini tekshirish
tmux has-session -t $SESSION_NAME 2>/dev/null
if [ $? -eq 0 ]; then
  echo "Ilova allaqachon ishlamoqda (tmux sessiyasi mavjud: $SESSION_NAME)."
  exit 0
fi

# Port ochiqligini tekshirish
nc -z localhost $PORT
if [ $? -eq 0 ]; then
    echo "Port $PORT allaqachon ochiq. Ehtimol boshqa narsa ishlayapti."
    exit 1
fi

echo "Ilova ishga tushirilmoqda..."
tmux new-session -d -s $SESSION_NAME "java -jar $JAR_PATH >> $LOG_FILE 2>&1"
echo "✅ Ilova ishga tushdi. Tmux sessiyasi: $SESSION_NAME"
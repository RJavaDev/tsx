#!/bin/bash

SESSION_NAME="tsx"

# Tmux sessiyasi mavjudmi?
tmux has-session -t $SESSION_NAME 2>/dev/null
if [ $? -eq 0 ]; then
  tmux kill-session -t $SESSION_NAME
  echo "🛑 Ilova to‘xtatildi (tmux sessiyasi $SESSION_NAME o‘chirildi)."
else
  echo "❗ Tmux sessiyasi $SESSION_NAME topilmadi. Ilova ishlamayapti."
fi
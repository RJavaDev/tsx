#!/usr/bin/env bash
# wait-for-it.sh

set -e

hostport="$1"
shift
cmd="$@"

host=$(echo $hostport | cut -d: -f1)
port=$(echo $hostport | cut -d: -f2)

until bash -c "echo > /dev/tcp/$host/$port"; do
  >&2 echo "Postgres is unavailable - sleeping"
  sleep 1
done

>&2 echo "Postgres is up - executing command"
exec $cmd

#!/usr/bin/env bash
echo "load database scripts..."
for f in /docker-entrypoint-initdb.d/scripts/*.sql; do
  echo "running file $f"; [ -f "$f" ] && psql --username postgres --dbname crm < "$f"
done

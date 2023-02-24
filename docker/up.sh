#!/bin/sh
docker-compose -p univ up -d --remove-orphans $*

#!/bin/sh

echo 'SPRING_PROFILES_ACTIVE = '$SPRING_PROFILES_ACTIVE
echo 'MAX_HEAP_MEMORY = '$MAX_HEAP_MEMORY
echo 'MIN_HEAP_MEMORY = '$MIN_HEAP_MEMORY
echo 'APP_NAME = '$APP_NAME
echo 'APP_VERSION = '$APP_VERSION
echo 'APPLICATION_PORT = '$APPLICATION_PORT
echo 'APPLICATION_PATH = '$APPLICATION_PATH
echo 'LOG_LOCATION = '$LOG_LOCATION
echo 'RESOURCE_LOCATION = '$RESOURCE_LOCATION
echo 'CONFIG_LOCATION = '$CONFIG_LOCATION

exec "$@"

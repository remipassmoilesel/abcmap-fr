#!/usr/bin/env bash

# Launch script for Abc-Map
# You can modify environment vars to modify site behavior

. ./setenv.sh

export ABCMAP_FR_ACTIVE_PROFILE=prod

./mvnw spring-boot:run

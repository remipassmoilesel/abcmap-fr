#!/usr/bin/env bash

# Launch script for Abc-Map
# You can modify environment vars to modify site behavior

. ./export-env-vars.sh

./mvnw spring-boot:run

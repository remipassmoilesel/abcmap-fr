#!/usr/bin/env bash

# Launch script for Abc-Map
# You can modify environment vars to modify site behavior

export ABCMAP_FR_DB_USERNAME=abcmapfr
export ABCMAP_FR_DB_PASSWORD=abcmapfr

export ABCMAP_FR_GANALYTICS_API_KEY=UA-57177987-1

export ABCMAP_FR_MAIL_SERVER=mail.server.fr
export ABCMAP_FR_MAIL_USERNAME=abcmapfr
export ABCMAP_FR_MAIL_PASSWORD=abcmapfr

./mvnw spring-boot:run
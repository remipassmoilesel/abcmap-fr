#!/usr/bin/env bash

# Launch script for Abc-Map
# You can modify environment vars to modify site behavior

export ABCMAP_FR_DB_NAME=abcmapfr
# Specify JDBC url without database name
export ABCMAP_FR_DB_URL=jdbc:mysql://localhost:3306/
export ABCMAP_FR_DB_USERNAME=abcmapfr
export ABCMAP_FR_DB_PASSWORD=abcmapfr


export ABCMAP_FR_DB_BACKUP_DIRECTORY=./db-backup/
export ABCMAP_FR_DB_BACKUP_NUMBER=40

export ABCMAP_FR_GANALYTICS_API_KEY=UA-57177987-1

export ABCMAP_FR_MAIL_SERVER=mail.server.fr
export ABCMAP_FR_MAIL_USERNAME=abcmapfr
export ABCMAP_FR_MAIL_PASSWORD=abcmapfr

export ABCMAP_FR_ADMIN_LOGIN=admin
export ABCMAP_FR_ADMIN_PASSWORD=admin

export ABCMAP_FR_ACTIVE_PROFILE=dev




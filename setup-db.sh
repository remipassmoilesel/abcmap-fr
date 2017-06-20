#!/usr/bin/env bash

sudo mysql -u root -e "CREATE DATABASE abcmapfr"
sudo mysql -u root -e "CREATE DATABASE abcmapfr-test"
sudo mysql -u root -e "CREATE USER 'abcmapfr'@'localhost' IDENTIFIED BY 'abcmapfr';"

sudo mysql -u root -e "GRANT ALL ON abcmapfr.* TO 'abcmapfr'@'localhost';"
sudo mysql -u root -e "GRANT ALL ON abcmapfr-test.* TO 'abcmapfr'@'localhost';"

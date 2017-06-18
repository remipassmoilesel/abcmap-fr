# Abc-map.fr

The new version of Abc-map's website. Work in progress.
Based on Spring Boot.

## Launch a demo

	$ git clone https://github.com/remipassmoilesel/abcmap-fr
	$ cd abcmap-fr
	$ ./launcher-example.sh

After visit http://localhost:8085 .

## Specifications

	- Responsive website for desktop and mobile devices
	- Use of Spring, a high productivity entreprise framework
	- Mailing list registration, message board, votes, ...
	- Github activity summary of a profile or a user in real time
	- Donation form with Paypal
	- Optionnal translation with Google Translate

## Sass processing

Need Node JS.

	$ npm install
	$ npm install -g gulp
	$ gulp

## Package and deploy on Tomcat

Create a war:

	$ . ./setenv.sh
	$ mvn package

Modify and copy setenv to:

	$ mv setenv.sh CATALINA_HOME/bin/

Restart tomcat.

	$ CATALINA_HOME/bin/catalina.sh stop
	$ CATALINA_HOME/bin/catalina.sh start

Copy generated war in webapps folder:

	$ cp target/abcmapfr###.war CATALINA_HOME/webapps

## TODO

    - Mail notifications

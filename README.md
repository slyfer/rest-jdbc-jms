Example rest to jdbc - jms

--------------------------
Modules
--------------------------

-----

- jms-client

description:

Jms client that consumes messagges and store them in db

configuration: 

edit: jms-client/src/main/filters/demo.filter.properties

-----

- rest-client

description:

Rest client of rest-server module

configuration: 

edit: rest-client/src/main/filters/demo.filter.properties

-----

- rest-model

description:

Commom module

configuration:

none

-----

- rest-server

description:

Commom module

configuration:

rest-server/src/main/filters/demo.filter.properties

--------------------------
Build
--------------------------

$> cd rest-jdbc-jms/
$> ant build

--------------------------
Run
--------------------------

$> ant run-jms-client
$> ant run-rest-client
$> ant run-rest-server

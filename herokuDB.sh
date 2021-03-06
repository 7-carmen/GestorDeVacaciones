#! /bin/bash


##### Edit files

sed -i 's@localhost:3306/Sample@'"$jdbcURL"'@g' src/main/resources/META-INF/persistence.xml
sed -i 's/acme-manager/'$jdbcUser'/g' src/main/resources/META-INF/persistence.xml
sed -i 's/ACME-M@n@ger-6874/'$jdbcPassword'/g' src/main/resources/META-INF/persistence.xml

sed -i 's@localhost:3306/Sample@'$jdbcURL'@g' src/main/resources/spring/config/data.xml
sed -i 's/acme-user/'$jdbcUser'/g' src/main/resources/spring/config/data.xml
sed -i 's/ACME-Us3r-P@ssw0rd/'$jdbcPassword'/g' src/main/resources/spring/config/data.xml

##### Populate the database

mvn clean install -Dmaven.test.skip=true
#mvn exec:java -Dexec.mainClass="utilities.PopulateDatabase"

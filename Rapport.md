# Labo SMTP Prank
##  Introduction
The aim of this program is to have an easy implementation of SMTP protocol

⚠️This could be used to send real spam email, so be careful of what you are doing with this.

The program will take mail addresses from [victims.utf8](config/victims.utf8) and send random email from [messages.utf8](config/messages.utf8)
You can freely change this.

## Instalation

### 1. Install the program
You can simply use mvn clean install after cloning this repository.

### 2. Tests manual
If you want to tests your eventual modification, you can clone this  [this](https://github.com/DominiqueComte/MockMock) 
to have a Mock server to do your tests. Assuming your using this you can do a test run:
1. Start server:
> java -jar /path/to/target/MockMock-1.4.1-SNAPSHOT.one-jar.jar -p 9999
2. Wait for the server to start (this could take a while)    
3. Run the program
> java -cp /path/to/target/SMTP-1.0-SNAPSHOT.jar SMTP.SmtpSender

Check at URL http://localhost:8282 for the results

### 2. Tests with docker

As an alternative to running the server manually, we created a few scripts to make it easier to use:
 - docker/build-image.sh
 - docker/docker-run.sh
 - docker/docker-stop.sh
For a full start with docker you have to:
> docker/build-image.sh
docker/docker-run.sh
java -cp /path/to/target/SMTP-1.0-SNAPSHOT.jar SMTP.SmtpSender

Check at URL http://localhost:8282 for the results

Do not forget to do a stop afterwards
> docker/docker-run.sh

## Classes description

### ConfigurationManager

This class is here to separate the gestion of the config file from the process. It reads the file from config/ the files called : config.properties  messages.utf8  victims.utf8
Those 3 files correspond respectively to: the global config (port, hostname, and so on), the message to send, and the list of victim that are suppose to receieve said messages.

### Package prank

The package prank generates "prank" messages to send, it randomly select victims, sender and messages from the config files.

### Package mail

This simple pacakge is what does the association with a person "Bob" and its email "bob@example.net". It also contains the notion of group of person, to be able to send a prank not to a specific person, but to a group of persons.

This makes more victims !

### SmtpClient

This is probably the most important class, as it is what really sent the email. It will connect to a SMTP server, in our case our mock server, and send the prank to the victims. This is the exquiavelent to our "main" programm.




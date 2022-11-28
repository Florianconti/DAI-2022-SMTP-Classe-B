# Labo SMTP Prank
##  Introduction
The aim of this program is to have an easy implementation of SMTP protocol

⚠️This could be used to send real spam email, so be careful of what you are doing with this.

The program will take mail addresses from [victims.utf8](config/victims.utf8) and send random email from [messages.utf8](config/messages.utf8)
You can freely change this.

## Instalation

### 1. Install the program
You can simply use mvn clean install after cloning this repository.

### 2. Tests
If you want to tests your eventual modification, you can clone this  [this](https://github.com/DominiqueComte/MockMock) 
to have a Mock server to do your tests. Assuming your using this you can do a test run:
1. Start server:
> java -jar /path/to/target/MockMock-1.4.1-SNAPSHOT.one-jar.jar -p 9999
2. Wait for the server to start (this could take a while)    
3. Run the program
> java -cp /path/to/target/SMTP-1.0-SNAPSHOT.jar SMTP.SmtpSender


# 5. How To Run

## Prerequisites:
* [Java](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://www.mkyong.com/maven/how-to-install-maven-in-windows/)
* [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
* [Node.js](https://nodejs.org/en/download/)
* [ActiveMQ](https://activemq.apache.org/getting-started.html)
* [MongoDB](https://docs.mongodb.com/manual/installation/)

## Steps:
1. Make a new directory to work inside
2. Create a .bat file with the contents:
```
@echo off
git clone https://github.com/alvinjo/AccountFront.git
git clone https://github.com/alvinjo/RealAccountApi.git
git clone https://github.com/alvinjo/RealAccountNumGenApi.git
git clone https://github.com/alvinjo/RealAccountPrizeApi.git
git clone https://github.com/alvinjo/RealAccountConsumer.git
cd AccountFront
npm install
cd ..
```
3. Run the .bat file you created
4. Start the ActiveMQ service

Do this by locating the directory where you have extracted the activemq files. Open command prompt and navigate to the 'bin' folder within the activemq files. Run the command 'activemq start'. The activemq process should start. You can view the queue on localhost:8161/admin/queues.jsp. The default username and password is 'admin'. 

5. Start MongoDB

Do this by first locating the directory where mongodb is installed. The path will look similar to this *'..\MongoDB\Server\4.0\bin\'*. Open command prompt, navigate to this directory and run the mongo.exe file by typing 'mongo'. Once you hit enter, the mongo service will be running.

6. Create another .bat file with the contents:
```
cd RealAccountApi
start mvn spring-boot:run

cd ../RealAccountConsumer
start mvn spring-boot:run

cd ../RealAccountPrizeApi
start mvn spring-boot:run

cd ../RealAccountNumGenApi
start mvn spring-boot:run

cd ../AccountFront
npm start
```
7. Run the .bat file

8. The application should be running. Navigate to localhost:3000



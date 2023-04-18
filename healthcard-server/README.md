# HealthCard Server
## Prerequisites
+ [Git SCM] (https://git-scm.com/downloads) 
+ Oracle JDK 19
+ Gradle latest
+ MySQL 5.x +


## Cloning Repository
Once you [generate SSH keys and add to your Github account](https://help.github.com/articles/generating-ssh-keys/)  you can directly clone the repo using following command.
```bash
 git clone https://github.com/flabdev/healthcard.git
```
P.S: [You should not be using the sudo command with Git] (https://help.github.com/articles/error-permission-denied-publickey)


## Setting up local properties file
 
+ In the cloned repository, navigate to src/main/resources and copy the applicaton.properties file
+ Paste the file in any location on you computer other than the project folder and name it application-local.properties
+ add the mysql details for the project

Change the datasourcename and datasourcePassword in the file to below values
```bash
spring.datasource.username=absd
spring.datasource.password=pass
```

## MySQL seed data setup 
Copy the sql in V1__root_schema.sql in db.migration folder in src/main/resources and execute it in your local mysql instance
Copy the sql in test_seed_data.sql in src/main/resources folder and execute it in your local mysql instance


## Poject execution
Navigate to the root folder and execute 
```bash
gradle clean build
```

Execute the below command
```bash
java -jar build/libs/healthcard-0.0.1-SNAPSHOT.jar --spring.profiles.active=local --spring.config.location=/home/fission/Downloads/healthcard_profiles/
```

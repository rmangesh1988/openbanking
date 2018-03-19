# Project: Openbanking REST API

Openbanking rest api. Contains 3 modules,
* openbanking-client
* openbanking-domain
* openbanking-service

## Information

GET, PATCH, POST service for customer resource.

### Installing steps
```
1.Checkout the project
2.cd .. to openbanking
3.mvn clean install
4.cd.. to openbanking-service
5.mvn spring-boot:run (Or run OpenBankingApplication.java)
 
Access the rest-api at,
http://localhost:9000/customers
 
Test data with customer ID : 12345678902 created at startup
 
Rest api authentication,
username : user
password : password
```

### Hosted On-AWS
```
The rest-api is hosted on AWS Elastic beanstalk at,   
http://openbanking.us-east-2.elasticbeanstalk.com/
```

## Author
Mangesh Rananavare
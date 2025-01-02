# IDMS
Below items can be found as part of this implementation. 

#### Code clone
clone from github url git@github.com:sskktech/drivesoft.git

#### Code build and run
mvn clean install
mvn spring-boot:run


### User Login API idms/api/Login
Which validates user and set authentication status in spring security context, so that user would be allowed to access further authenticated APIs.

### Generate Token API idms/api/authenticate/GetUserAuthorizationToken
To access the secured external api and retrieves token with the credentials configured in application

### Create Account API idms/api/Create/Account
Which validates current user authenticated or not and retrieves valid token or not then it would provide results.

### Fetch Account API /idms/api/Account/GetAccountList
Fetch the data from IDMS Accounts Information API  

### Spring security User validation
Validates user details from the underlying database using spring security.

### Spring security for url's
Allowed public url to get access token. Restricted protected urls. 






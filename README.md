> **What is security?**

Security is for protecting your data and business logic inside your web application.

Security is very important similar to scalability, performance and availability. No client will specifically ask that
need security.

> **What is security?**

Security is for protecting your data and business logic inside your web application.

> **Enable Spring Security**

1) Add Dependency

```
<dependencies>
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
</dependencies>
```

2) Change username and password:

For demo, change user and password

In properties file:

- `spring.security.user.name = user`
- `spring.security.user.password = 123456`

After login for first time , we can refresh and get the same response , because backend is not asking credential again
and again.

That means it is only asking credentials from user first time. It set sessionID to Cookie.

> **Postman**

If you want to use postman, first must set username and password.

Go to Authorization tab and chose Basic auth from dropdown.

Spring security set JSeesonID to header after that not required to set username and password for each request, If delete
JSessionId from cookie (in postman) will see that get 401(unauthorized) error.


> **Spring Security Authentication Process**

How Spring security framework works internally, once it receives any request from front end, third part api consumer,
etc.

![Spring Security Flow](src/main/resources/assests/images/1-spring-security-flow.png)

Authentication Filter will be intercepting the request, it will try to convert authentication details that receiving
from the user like username and password into an authentication object.

This authentication object is a base where all the validation of user,credentials will be validated for all steps.

Once that authentication object is build, the request will be passed to the authentication manager.

Authentication manager is the place where it will identify what is a correspondent authentication provider that my
request has to go .

we have many process where we can validate user credentials, like database, ldap, OAuth, etc,

Once authentication provider receives the request, this is a place where all your business logic will be implemented.
(the logic related to your security like how do I validate username and password)

Authentication provider will use two interfaces:

- User Details Service
- Password Encoder

The user details services is the interface which holds user schema, like how user details should look like.

The password encoder is the interface which will tell how my password has to be encrypted, encoded or decrypted.

Once authentication provider using user detail services and password encoder validator with the input are valid or not ,
the flow will be given back to Authentication manager and authentication filter.

If user is valid, the authentication filter will pass that the authentication object to security context .

## Components:

1) **Authentication Filter** : It's a Filter in the FilterChain which detects an authentication attempt and forwards it
   to the AuthenticationManager


3) **Authentication** : This component specifies the type of authentication to be conducted. Its is an interface. Its
   implementation specifies the type of Authentication. For example, `UsernamePasswordAuthenticationToken` is an
   implementation of the `Authentication` interface which specifies that the user wants to authenticate using a username
   and password. Other examples include `OpenIDAuthenticationToken` , `RememberMeAuthenticationToken` .


4) **Authentication Manager** : The main job of this component is to delegate the authenticate() call to the correct
   AuthenticationProvider. An application can have multiple AuthenticationProvider s, few of which are
   `DaoAuthenticationProvider`, `LdapAuthenticationProvider`, `OpenIDAuthenticationProvider`, etc. The Authentication
   Manager decides which Authentication Provider to delegate the call to by calling the supports() method on every
   available AuthenticationProvider. If the supports() method returns true then that AuthenticationProvider supports the
   Authentication type and is used to perform authentication.

4. **Authentication Provider** : It is an interface whose implementation processes a certain type of authentication . An
   AuthenticaionProvider has an authenticate method which takes in the Authentication type and performs authentication
   on it. On successful authentication, the AuthenticationProvider returns back an Authentication object of the same
   type that was taken as input with the authenticated property set to true. If authentication fails, then it throws an
   Authentication Exception. Following figure shows a generalized AuthenticationProvider.


5. **UserDetailsService**: This is a service which is responsible for fetching the details of the user from a
   “datasource”, most likely a database using the loadUserByUsername(String username) methods which takes in the
   username as a parameter. It then returns a UserDetails object populated with the user data fetched from the
   datasource ( database )
   . The three main fields of an UserDetails object are username, password and the roles/authorities.

----

### Read Me First
- **Precedence** of config value: config server > config from client application

- Config values can be included in file with **properties/yml format**.

- Config value can be achieved by client in 2 ways:
	- Using **@Value**: the usage is demonstrated in ConfigClientApplication.java
	- Using **@ConfigurationProperties**: the usage is demonstrated in ClientConfiguration.java, ConfigClientApplication.java


### Getting Started

##### Reference Documentation
- [Spring Cloud Config](https://spring.io/projects/spring-cloud-config)
- [Baeldung](https://www.baeldung.com/spring-cloud-configuration)

##### Guides
* `curl http://app:optsd1705@localhost:8888/config-client/development/master`
   
   --> use to get config data on the config server
   
   - *config-client*: application
   - *development*: profile
   - *master*: label (branch)
   

###### Ways to get the config data on the config server
```
- /{application}/{profile}[/{label}]
- /{application}-{profile}.[yml|properties]
- /{label}/{application}-{profile}.[yml|properties]
```


* `curl http://localhost:8080/whoami/OptSD`

   --> Endpoint to test getting config from server


* `curl -X POST http://app:optsd1705@localhost:8888/refresh`

   --> Refresh config server to get newest config value from GIT repo


* `curl -X POST --data-urlencode mypassword http://app:optsd1705@localhost:8888/encrypt`

   --> use to generate encrypted key from plain text


* `curl -X POST --data-urlencode myencryptedkey http://app:optsd1705@localhost:8888/decrypt`

   --> use to decrypt the encrypted key to plain text  


* `curl -X POST http://app:optsd1705@localhost:8888/refresh`

   --> JUST FOR DEVELOPMENT. force bean to refresh its configuration from config server (by default, it just reads at startup and not again)
   
Wherein: **app:optsd1705** is username:password use to connect to config server
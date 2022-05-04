# Project Details

1. custom-authentication-method-level-security :: In this spring boot project I have provided in-house solution for spring security. 

# Maven Dependencies 

1. spring-boot-starter-security
2. spring-boot-starter-web
3. spring-boot-starter-jdbc
4. spring-boot-starter-data-jpa
5. mariadb-java-client


# SQL For Creating Customer Table

CREATE TABLE customers (
id INT NOT NULL auto_increment,
email VARCHAR (45) NOT NULL,
PASSWORD VARCHAR(45) NOT NULL,
role VARCHAR(45) NOT NULL,
PRIMARY KEY(id)
)


CREATE TABLE authorities(
	id INT  NOT NULL AUTO_INCREMENT, 
	customer_id INT,
	authority VARCHAR(120),
	PRIMARY KEY(id),
	FOREIGN KEY (customer_id) REFERENCES customer(id)
)

# Running project from CMD line 

run the mvn commands and generate JAR file for executing this project anywhere
1. mvn clean
2. mvn install


# Project Details
1. Eureka Discovery Server :: For Registering coupon microservice with eureka server.
2. coupon-service :: It's our ResourceServer
3. auth-server :: It's our Authorization Server. Which is responsible for generating token for us.


# 1.Eureka Discovery Client 

Allows microservices to register themselves with Naming server. 

# Maven Dependencies

1. spring-cloud-starter-netflix-eureka-server

This server will run on port 8761. Below are app.properties:: 

server.port=8761                
eureka.client.register-with-eureka=false                            #Eureka should not register itself with eureka that's why it's set to false.
eureka.client.fetch-registry=false                                  #Also it should not fetch client registy 


# Generate Public And Private key 

Public Key will be used by coupon-service to validate the token. 
Private Key will be used by auth-server to generate the token.


# Generate Keystore file

Use following command ::
keytool -genkeypair -alias indicia-worldwide -keyalg RSA -keypass indicia-worldwide -keystore indicia.jks -storepass indicia-worldwide

Get public key from generated keystore file using following command

keytool -list -rfc  --keystore indicica.jks | openssl x509 -inform pem -pubkey

Add following properties in app.properties of auth-server

keyFile =indicia.jks
password=worldwide
alias=indicia


Add this property in AuthorizationServerConfig and Configure Two Beans

  @Value("${keyFile}")
	private String keyFile;

	@Value("${password}")
	private String password;

	@Value("${alias}")
	private String alias;
  




  @Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
	JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		
	KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(keyFile), password.toCharArray());
		
  KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias);
	jwtAccessTokenConverter.setKeyPair(keyPair) ;
	return jwtAccessTokenConverter;
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}


  // Use this tokenStore() in configure method
  
  @Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore()).accessTokenConverter(jwtAccessTokenConverter()).authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}
  
  For Generating Token Spring gives us following URL 
  
  http://localhost:9091/oauth/token
  
  In Authorization  -> Select  Basic Auth -> Pass Username = coupon-client-app and password =9999
  In Form Data -> Pass following parameters
  username -> mahi
  password -> Ma120397@
  grant_type -> password
  scopes -> read write

  This will return access_token, token_type , refresh_token , expires_in, scope, jti.
  
  In coupon-service we use public-key and that public key will be injected to the app.properties.
  
  e.g. 
  
  #Static Public Key
  #publicKey=-----BEGIN PUBLIC KEY-----\r\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu7K9wkg7b5gTfvfRtlNT\r\nuneoqgfAHV7cfbs8OLAw1+g5O1wL58zktmXHXaMRSLfQc7u21wnu4+e1nkYiaQHh\r\nNMb/vfUXIVxVjlsUTM069zWRg3GUB4HHgWTc3RTaPBcVgMmToOiqqk2HAkM3CaDR\r\nmt/1RzZq+LyK8BZ1bXV+IfB0VpVYVuy6H1sGIvhy00CuBlDAoO0dASEj8Key/ln5\r\nO/dvIwsHzK93njXKvI1IglFzE7yRMIRwULZDC8Jb6wiyv/0NRxUSmzpv8m3Dmjnr\r\n6IovaoXJGzzxHt7EZTV311qY0z1xpVnhTlqZgzOdVyqEIMi27baeltY+S0LzYKD2\r\nyQIDAQAB\r\n-----END PUBLIC KEY-----
  
We also need two beans in ResourceServer. We don't have to configure whole thing in there because ResourceServer doesn't worry about the private key.

@Value("{$publicKey}") 
private String publicKey;  
  
@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
	JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		
	jwtAccessTokenConverter.setVerifierKey(publicKey) ;
	return jwtAccessTokenConverter;
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
  


    We can use public dynamically. Instead of adding it manually in app.properties.
    To use the public key dynamically. add following Restful endpoint in app.properties.
    
    security.oauth2.resource.jwt.key-uri=http://localhost:9092/oauth/token_key
    
    And in AuthorizationServer allow everyone to access public key by adding following ::
    
    @Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()");
	}
    
    
    

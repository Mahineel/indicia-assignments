

CREATE TABLE oauth_access_token(
	token_id VARCHAR (255) NOT NULL,
	token BLOB,
	authentication_id VARCHAR(255) DEFAULT NULL,
	user_name VARCHAR(255) DEFAULT NULL,
	client_id VARCHAR(255) NOT NULL,
	authentication BLOB, 
	refresh_token VARCHAR(255) DEFAULT null,
	PRIMARY KEY(token_id)
)

CREATE TABLE oauth_refresh_token(
	token_id VARCHAR (255) NOT NULL,
	token BLOB,
	authentication BLOB, 
	PRIMARY KEY(token_id)
)
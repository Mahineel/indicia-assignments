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

 INSERT INTO authorities (customer_id, authority) VALUES(3,'READ') 
 INSERT INTO authorities (customer_id, authority) VALUES(3,'DELETE') 



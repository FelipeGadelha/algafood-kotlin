CREATE TABLE users (
	id SERIAL NOT NULL,
	creation_date TIMESTAMP NOT NULL,
	name VARCHAR(100),
	email VARCHAR(255),
	password VARCHAR(255),

	PRIMARY KEY (id)
);
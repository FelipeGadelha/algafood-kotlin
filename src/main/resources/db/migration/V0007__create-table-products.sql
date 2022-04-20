CREATE TABLE products (
	id SERIAL NOT NULL,
	name VARCHAR(100) NOT NULL,
	description TEXT NOT NULL,
	price NUMERIC(10, 2) NOT NULL,
	active BOOLEAN NOT NULL,
	restaurant_id BIGINT NOT NULL,

	PRIMARY KEY (id)
);

ALTER TABLE products ADD CONSTRAINT FK_products_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants (id);

CREATE TABLE order_item (
	id SERIAL NOT NULL,
	observation VARCHAR(255),
	quantity int4 NOT NULL,
	total_price NUMERIC(10,2) NOT NULL,
	unit_price NUMERIC(10,2) NOT NULL,
	order_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	PRIMARY KEY (id)
	);

ALTER TABLE order_item ADD CONSTRAINT FK_order_item_order FOREIGN KEY (order_id) REFERENCES orders;
ALTER TABLE order_item ADD CONSTRAINT FK_order_item_product FOREIGN KEY (product_id) REFERENCES products;
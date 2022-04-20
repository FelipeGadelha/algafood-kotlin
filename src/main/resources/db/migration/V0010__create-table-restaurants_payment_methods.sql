CREATE TABLE restaurants_payment_methods (
	restaurant_id BIGINT NOT NULL,
	payment_method_id BIGINT NOT NULL
	);

ALTER TABLE restaurants_payment_methods ADD CONSTRAINT FK_restaurants_payment_methods_restaurants FOREIGN KEY (restaurant_id) REFERENCES restaurants (id);
ALTER TABLE restaurants_payment_methods ADD CONSTRAINT FK_restaurants_payment_methods_payment_methods FOREIGN KEY (payment_method_id) REFERENCES payment_methods (id);

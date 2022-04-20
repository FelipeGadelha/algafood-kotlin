CREATE TABLE users_restaurants_owner (
	user_id BIGINT NOT NULL,
	restaurant_id BIGINT NOT NULL,

	PRIMARY KEY (user_id, restaurant_id)
);

ALTER TABLE users_restaurants_owner ADD CONSTRAINT FK_users_restaurants_owner_user FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE users_restaurants_owner ADD CONSTRAINT FK_users_restaurants_owner_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants (id);

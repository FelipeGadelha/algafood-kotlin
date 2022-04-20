CREATE TABLE users_groups (
	users_id BIGINT NOT NULL,
	groups_id BIGINT NOT NULL
);

ALTER TABLE users_groups ADD CONSTRAINT FK_users_groups_users FOREIGN KEY (users_id) REFERENCES users (id);
ALTER TABLE users_groups ADD CONSTRAINT FK_users_groups_groups FOREIGN KEY (groups_id) REFERENCES groups (id);

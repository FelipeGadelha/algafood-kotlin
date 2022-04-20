CREATE TABLE groups_permissions (
	groups_id BIGINT NOT NULL,
	permissions_id BIGINT NOT NULL
	);

ALTER TABLE groups_permissions ADD CONSTRAINT FK_groups_permissions_groups FOREIGN KEY (groups_id) REFERENCES groups (id);
ALTER TABLE groups_permissions ADD CONSTRAINT FK_groups_permissions_permissions FOREIGN KEY (permissions_id) REFERENCES permissions (id);
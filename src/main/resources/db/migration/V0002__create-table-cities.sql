CREATE TABLE cities (
    id SERIAL NOT NULL,
    name VARCHAR(60) NOT NULL,
    state_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
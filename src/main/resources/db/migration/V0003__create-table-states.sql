CREATE TABLE states (
    id SERIAL NOT NULL,
    name VARCHAR(60) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE cities ADD CONSTRAINT FK_cities_states FOREIGN KEY (state_id) REFERENCES states (id);
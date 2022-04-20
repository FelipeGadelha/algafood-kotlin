CREATE TABLE product_image (
    product_id INT8 NOT NULL,
    file_name VARCHAR(150) NOT NULL,
    description VARCHAR(150) NOT NULL,
    content_type VARCHAR(80) NOT NULL,
    size INT NOT NULL,

    PRIMARY KEY (product_id),
    CONSTRAINT FK_product_image_product FOREIGN KEY (product_id) REFERENCES products (id)
);
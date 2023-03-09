INSERT INTO categories (name, slug) VALUES ('Médical', 'medical');
INSERT INTO categories (name, slug) VALUES ('Loisirs', 'loisirs');
INSERT INTO categories (name, slug) VALUES ('Mécanique', 'mecanique');
INSERT INTO categories (name, slug) VALUES ('Informatique', 'informatique');
INSERT INTO categories (name, slug) VALUES ('Sport', 'sport');

INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Aspartane', 20.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Bicarbonate de soude', 10.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Bicarbonate de sodium', 10.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Bicarbonate de potassium', 10.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Vélo', 10.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Trottinette', 10.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail, highlighted) VALUES ('Ordinateur', 1000.00, 5, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null, true);
INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Casque', 3.00, 500, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Téléphone', 500.00, 4, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Tablette', 850.00, 1, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Tapis de course', 1000.00, 1, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO products (name, price, quantity, created_at, updated_at, description, thumbnail, highlighted) VALUES ('Tapis de sol', 1000.00, 1, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null, true);

INSERT INTO products_categories (product_id, category_id) VALUES (1, 1);
INSERT INTO products_categories (product_id, category_id) VALUES (2, 1);
INSERT INTO products_categories (product_id, category_id) VALUES (3, 1);
INSERT INTO products_categories (product_id, category_id) VALUES (4, 1);
INSERT INTO products_categories (product_id, category_id) VALUES (5, 2);
INSERT INTO products_categories (product_id, category_id) VALUES (6, 2);
INSERT INTO products_categories (product_id, category_id) VALUES (7, 4);
INSERT INTO products_categories (product_id, category_id) VALUES (8, 4);
INSERT INTO products_categories (product_id, category_id) VALUES (9, 4);
INSERT INTO products_categories (product_id, category_id) VALUES (10, 4);
INSERT INTO products_categories (product_id, category_id) VALUES (11, 5);
INSERT INTO products_categories (product_id, category_id) VALUES (12, 5);

INSERT INTO users (email, password, name, firstname, created_at, updated_at, enabled) VALUES ('john@doe.tld', '$2y$10$pF2qXuc8pe2V3zdFXqToBeccuO6/i/021Bk6Zrn/AoqcvkV8lUCHK', 'DOE', 'John', '2023-02-20 09:13:27.716409', '2023-02-20 09:13:27.716409', true);
INSERT INTO users (email, password, name, firstname, created_at, updated_at, enabled) VALUES ('test@test.tld', '$2y$10$pF2qXuc8pe2V3zdFXqToBeccuO6/i/021Bk6Zrn/AoqcvkV8lUCHK', 'TEST', 'Test', '2023-02-20 09:13:27.716409', '2023-02-20 09:13:27.716409', true);


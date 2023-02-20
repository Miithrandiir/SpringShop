TRUNCATE TABLE user_orders_items CASCADE;
TRUNCATE TABLE user_orders CASCADE;
TRUNCATE TABLE users CASCADE;
TRUNCATE TABLE products_images CASCADE;
TRUNCATE TABLE products_categories CASCADE;
TRUNCATE TABLE products CASCADE;
TRUNCATE TABLE categories CASCADE;

ALTER SEQUENCE user_orders_items_id_seq RESTART WITH 1;
ALTER SEQUENCE user_orders_id_seq RESTART WITH 1;
ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE products_images_id_seq RESTART WITH 1;
ALTER SEQUENCE products_id_seq RESTART WITH 1;
ALTER SEQUENCE categories_id_seq RESTART WITH 1;

INSERT INTO categories (name, slug) VALUES ('Médical', 'medical');
INSERT INTO categories (name, slug) VALUES ('Loisirs', 'loisirs');
INSERT INTO categories (name, slug) VALUES ('Mécanique', 'mecanique');
INSERT INTO categories (name, slug) VALUES ('Informatique', 'informatique');
INSERT INTO categories (name, slug) VALUES ('Sport', 'sport');

INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Aspartane', 20.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Bicarbonate de soude', 10.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Bicarbonate de sodium', 10.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Bicarbonate de potassium', 10.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Vélo', 10.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Trottinette', 10.00, 50, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail, highlighted) VALUES ('Ordinateur', 1000.00, 5, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null, true);
INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Casque', 3.00, 500, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Téléphone', 500.00, 4, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Tablette', 850.00, 1, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail) VALUES ('Tapis de course', 1000.00, 1, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null);
INSERT INTO public.products (name, price, quantity, created_at, updated_at, description, thumbnail, highlighted) VALUES ('Tapis de sol', 1000.00, 1, '2023-02-04 11:41:45.000000', '2023-02-04 11:41:48.000000', '', null, true);

INSERT INTO public.products_categories (product_id, category_id) VALUES (1, 1);
INSERT INTO public.products_categories (product_id, category_id) VALUES (2, 1);
INSERT INTO public.products_categories (product_id, category_id) VALUES (3, 1);
INSERT INTO public.products_categories (product_id, category_id) VALUES (4, 1);
INSERT INTO public.products_categories (product_id, category_id) VALUES (5, 2);
INSERT INTO public.products_categories (product_id, category_id) VALUES (6, 2);
INSERT INTO public.products_categories (product_id, category_id) VALUES (7, 4);
INSERT INTO public.products_categories (product_id, category_id) VALUES (8, 4);
INSERT INTO public.products_categories (product_id, category_id) VALUES (9, 4);
INSERT INTO public.products_categories (product_id, category_id) VALUES (10, 4);
INSERT INTO public.products_categories (product_id, category_id) VALUES (11, 5);
INSERT INTO public.products_categories (product_id, category_id) VALUES (12, 5);

INSERT INTO public.users (email, password, name, firstname, created_at, updated_at, enabled) VALUES ('john@doe.tld', 'disabled-password', 'DOE', 'John', '2023-02-20 09:13:27.716409', '2023-02-20 09:13:27.716409', true);
INSERT INTO public.users (email, password, name, firstname, created_at, updated_at, enabled) VALUES ('test@test.tld', 'disabled-password', 'TEST', 'Test', '2023-02-20 09:13:27.716409', '2023-02-20 09:13:27.716409', true);


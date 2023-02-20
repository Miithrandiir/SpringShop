DROP TABLE IF EXISTS user_orders_items;
DROP TABLE IF EXISTS user_orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products_images;
DROP TABLE IF EXISTS products_categories;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

create table categories
(
    id   INT GENERATED ALWAYS AS IDENTITY,
    name varchar(255) not null unique,
    slug varchar(255) not null unique,
    primary key (id)
);

create table products
(
    id          INT GENERATED ALWAYS AS IDENTITY,
    name        varchar(255)   not null,
    price       decimal(10, 2) not null,
    quantity    int check ( quantity >= 0 ),
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    description text         default null,
    thumbnail   varchar(255) default null,
    highlighted bool         default false,
    primary key (id)
);

create table products_categories
(
    product_id  int not null,
    category_id int not null,
    constraint fk_products foreign key (product_id) references products (id),
    constraint fk_category foreign key (category_id) references categories (id),
    primary key (product_id, category_id)
);


create table products_images
(
    id         INT GENERATED ALWAYS AS IDENTITY,
    image      varchar(255) not null,
    product_id int          not null,
    primary key (id),
    constraint fk_product foreign key (product_id) references products (id)
);


create table users
(
    id         INT GENERATED ALWAYS AS IDENTITY,
    email      varchar(255) not null unique,
    password   varchar(255) not null,
    name       varchar(255) not null,
    firstname  varchar(255) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    enabled    bool      default true,
    primary key (id)
);

create table user_orders
(
    id         INT GENERATED ALWAYS AS IDENTITY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id    int not null,
    constraint fk_user foreign key (user_id) references users (id),
    primary key (id)
);

create table user_orders_items
(
    id         INT GENERATED ALWAYS AS IDENTITY,
    order_id   int not null,
    product_id int not null,
    quantity   int check ( quantity > 0 ),
    primary key (id),
    constraint fk_product foreign key (product_id) references products (id),
    constraint fk_order foreign key (order_id) references user_orders (id)
)


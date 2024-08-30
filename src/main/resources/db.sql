CREATE TABLE users (
  id BIGINT AUTO_INCREMENT NOT NULL,
   full_name VARCHAR(255) NOT NULL,
   mobile VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   address_line_1 VARCHAR(255) NOT NULL,
   address_line_2 VARCHAR(255) NOT NULL,
   state VARCHAR(255) NOT NULL,
   country VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   card_name VARCHAR(255) NOT NULL,
   card_holder VARCHAR(255) NOT NULL,
   card_no VARCHAR(255) NOT NULL,
   exp VARCHAR(255) NOT NULL,
   cvv VARCHAR(255) NOT NULL,
   status VARCHAR(255) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   is_deleted BIT(1) NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE carts (
  id BIGINT AUTO_INCREMENT NOT NULL,
   item_id BIGINT NOT NULL,
   customer_id BIGINT NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   is_deleted BIT(1) NOT NULL,
   CONSTRAINT pk_carts PRIMARY KEY (id)
);

CREATE TABLE contacts (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) NULL,
   message VARCHAR(255) NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   is_deleted BIT(1) NOT NULL,
   CONSTRAINT pk_contacts PRIMARY KEY (id)
);

CREATE TABLE inquiries (
  id BIGINT AUTO_INCREMENT NOT NULL,
   customer_id BIGINT NOT NULL,
   date VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   title VARCHAR(255) NULL,
   response VARCHAR(255) NULL,
   status VARCHAR(255) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   is_deleted BIT(1) NOT NULL,
   CONSTRAINT pk_inquiries PRIMARY KEY (id)
);

CREATE TABLE items (
  id BIGINT AUTO_INCREMENT NOT NULL,
   item_name VARCHAR(255) NOT NULL,
   item_description VARCHAR(255) NULL,
   item_price DOUBLE NULL,
   size VARCHAR(255) NOT NULL,
   category VARCHAR(255) NOT NULL,
   status VARCHAR(255) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   is_deleted BIT(1) NOT NULL,
   CONSTRAINT pk_items PRIMARY KEY (id)
);

CREATE TABLE orders (
  id BIGINT AUTO_INCREMENT NOT NULL,
   customer_id VARCHAR(255) NOT NULL,
   order_date VARCHAR(255) NOT NULL,
   status VARCHAR(255) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   total_price DOUBLE NOT NULL,
   CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE order_details (
  id BIGINT AUTO_INCREMENT NOT NULL,
   order_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL,
   quantity INT NOT NULL,
   price DOUBLE NOT NULL,
   total DOUBLE NOT NULL,
   CONSTRAINT pk_order_details PRIMARY KEY (id),
   CONSTRAINT FK_ORDER_DETAILS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id)
);

CREATE TABLE reservations (
  id BIGINT AUTO_INCREMENT NOT NULL,
   customer_name VARCHAR(255) NOT NULL,
   date VARCHAR(255) NULL,
   head_count INT NULL,
   buffet VARCHAR(255) NOT NULL,
   branch VARCHAR(255) NOT NULL,
   status VARCHAR(255) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   is_deleted BIT(1) NOT NULL,
   CONSTRAINT pk_reservations PRIMARY KEY (id)
);
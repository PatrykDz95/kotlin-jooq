
CREATE TYPE customer_status AS ENUM ('ACTIVE', 'DEACTIVATED');


CREATE TABLE customer_address
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT null,
    last_name VARCHAR(100) NOT null,
    address VARCHAR(100) NOT null,
    zipcode VARCHAR(10) NOT null,
    phone_number VARCHAR(15) NOT null
);

CREATE TABLE customer
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    balance DECIMAL NOT NULL,
    status customer_status NOT null,
    customer_address_id  INT  NOT NULL,
    CONSTRAINT customer_fk_customer_address_id FOREIGN KEY (customer_address_id) REFERENCES customer_address (id)
);

--INSERT INTO public.customer_address
--(first_name, last_name, address, zipcode, phone_number)
--VALUES('first_name', 'last_name', 'address', 'zipcode', 'phone_number');
--
--
--INSERT INTO public.customer
--(username, user_password, email, balance, status, customer_address_id)
--VALUES('username', 'user_password', 'email', 0, 'ACTIVE', 2);
--
--SELECT *
--FROM public.customer;
--
--SELECT *
--FROM public.customer_address;

CREATE TABLE item
(
    id SERIAL PRIMARY KEY,
    cost VARCHAR(100) NOT NULL,
    item_name VARCHAR(100) NOT NULL,
    purchase_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE review
(
    id SERIAL PRIMARY KEY,
    text VARCHAR(100) NOT NULL,
    reviewDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    customer_id  INT  NOT NULL,
    CONSTRAINT review_fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE customer_order
(
    id SERIAL PRIMARY KEY,
    order_id  VARCHAR(100) NOT null,
    customer_id  INT  NOT NULL,
    item_id  INT  NOT NULL,
    CONSTRAINT customer_order_fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT customer_order_fk_item_id FOREIGN KEY (item_id) REFERENCES item (id)
);

CREATE TABLE admin
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);
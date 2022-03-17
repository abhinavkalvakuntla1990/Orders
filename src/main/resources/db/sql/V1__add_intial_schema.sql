create table if not exists orders
(
id  bigserial constraint order_pk primary key,
order_id VARCHAR  UNIQUE,
status VARCHAR  NOT NULL,
customer_id bigint NOT NULL,
sub_total DOUBLE precision NOT NULL,
tax DOUBLE precision NOT NULL,
shipping_charges DOUBLE precision NOT NULL,
total DOUBLE precision NOT NULL,
shipping_address_id bigint,
delivery_method VARCHAR  NOT NULL,
created_at              timestamp without time zone,
created_by              VARCHAR,
modified_at             timestamp without time zone,
modified_by             VARCHAR
);


create table if not exists items
(
id bigserial constraint item_pk primary key,
name VARCHAR,
quantity integer,
status VARCHAR  NOT NULL,
order_id bigint,
CONSTRAINT fk_item_order_order_id FOREIGN KEY (order_id)
        REFERENCES public.orders (id) MATCH SIMPLE
			
);

create table if not exists payments
(
id bigserial constraint payment_pk primary key,
payment_method varchar NOT NULL,		
billing_address_id bigint,
confirmation_number bigint,
order_id bigint,
payment_date timestamp without time zone,
CONSTRAINT fk_item_order_order_id FOREIGN KEY (order_id)
        REFERENCES public.orders (id) MATCH SIMPLE
);

create table if not exists address
(
id bigserial constraint address_pk primary key,
line1 varchar,
line2 varchar,
city varchar,
state varchar,
zip varchar	
);
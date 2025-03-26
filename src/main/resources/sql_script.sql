DROP TABLE IF EXISTS book CASCADE;
DROP TABLE IF EXISTS magazine CASCADE;
DROP TABLE IF EXISTS publisher CASCADE;

CREATE TABLE IF NOT EXISTS book (
	id serial NOT NULL,
	title varchar(200) NOT NULL,
	quantity integer NOT NULL,
	CONSTRAINT book_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS publisher (
	id serial NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT publisher_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS magazine (
	id serial NOT NULL,
	title varchar(200) NOT NULL,
	quantity integer NOT NULL,
	publisher_id integer,
	CONSTRAINT magazine_pkey PRIMARY KEY (id),
	CONSTRAINT fk_publisher_id FOREIGN KEY (publisher_id)
		REFERENCES publisher (id) MATCH SIMPLE
		ON UPDATE CASCADE
		ON DELETE SET NULL
		NOT VALID
);

INSERT INTO book (title, quantity)
VALUES ('Java The Complete Reference 9th Edition', 40),
       ('Design Patterns: Elements of Reusable Object-Oriented Software', 30),
       ('Grokking Algorithms', 25),
       ('War and Peace', 30),
       ('Adventures of Huckleberry Finn', 50),
       ('Dune', 45);

INSERT INTO publisher (name)
VALUES ('National Geographic Society'),
       ('The National Trust'),
       ('Immediate Media Company');

INSERT INTO magazine (title, quantity, publisher_id)
VALUES ('National Geographic', 50, 1),
       ('National Trust Magazine', 40, 2),
       ('Radio Times', 25, 3),
       ('BBC Good Food', 20, 3),
       ('BBC Gardeners'' World', 20, 3),
       ('BBC Top Gear Magazine', 20, 3);

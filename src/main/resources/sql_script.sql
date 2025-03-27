DROP TABLE IF EXISTS book CASCADE;
DROP TABLE IF EXISTS magazine CASCADE;
DROP TABLE IF EXISTS publisher CASCADE;
DROP TABLE IF EXISTS magazine_fetchmode CASCADE;
DROP TABLE IF EXISTS publisher_fetchmode CASCADE;
DROP TABLE IF EXISTS magazine_batchsize CASCADE;
DROP TABLE IF EXISTS publisher_batchsize CASCADE;

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

CREATE TABLE IF NOT EXISTS publisher_fetchmode (
	id serial NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT publisher_fetchmode_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS magazine_fetchmode (
	id serial NOT NULL,
	title varchar(200) NOT NULL,
	quantity integer NOT NULL,
	publisher_id integer,
	CONSTRAINT magazine_fetchmode_pkey PRIMARY KEY (id),
	CONSTRAINT fk_publisher_fetchmode_id FOREIGN KEY (publisher_id)
		REFERENCES publisher_fetchmode (id) MATCH SIMPLE
		ON UPDATE CASCADE
		ON DELETE SET NULL
		NOT VALID
);

CREATE TABLE IF NOT EXISTS publisher_batchsize (
	id serial NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT publisher_batchsize_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS magazine_batchsize (
	id serial NOT NULL,
	title varchar(200) NOT NULL,
	quantity integer NOT NULL,
	publisher_id integer,
	CONSTRAINT magazine_batchsize_pkey PRIMARY KEY (id),
	CONSTRAINT fk_publisher_batchsize_id FOREIGN KEY (publisher_id)
		REFERENCES publisher_batchsize (id) MATCH SIMPLE
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
       ('Immediate Media Company'),
       ('Condé Nast');

INSERT INTO magazine (title, quantity, publisher_id)
VALUES ('National Geographic', 50, 1),
       ('National Trust Magazine', 40, 2),
       ('Radio Times', 25, 3),
       ('BBC Good Food', 20, 3),
       ('BBC Gardeners'' World', 20, 3),
       ('BBC Top Gear Magazine', 20, 3),
       ('Golf Digest', 20, 4),
       ('Vogue', 20, 4),
       ('The New Yorker', 20, 4),
       ('Vanity Fair', 20, 4),
       ('GQ', 20, 4),
       ('Bon Appétit', 20, 4),
       ('Architectural Digest', 20, 4),
       ('Condé Nast Traveler', 20, 4),
       ('Wired', 20, 4);

INSERT INTO publisher_fetchmode (name)
VALUES ('National Geographic Society'),
       ('The National Trust'),
       ('Immediate Media Company'),
       ('Condé Nast');

INSERT INTO magazine_fetchmode (title, quantity, publisher_id)
VALUES ('National Geographic', 50, 1),
       ('National Trust Magazine', 40, 2),
       ('Radio Times', 25, 3),
       ('BBC Good Food', 20, 3),
       ('BBC Gardeners'' World', 20, 3),
       ('BBC Top Gear Magazine', 20, 3),
       ('Golf Digest', 20, 4),
       ('Vogue', 20, 4),
       ('The New Yorker', 20, 4),
       ('Vanity Fair', 20, 4),
       ('GQ', 20, 4),
       ('Bon Appétit', 20, 4),
       ('Architectural Digest', 20, 4),
       ('Condé Nast Traveler', 20, 4),
       ('Wired', 20, 4);

INSERT INTO publisher_batchsize (name)
VALUES ('National Geographic Society'),
              ('The National Trust'),
              ('Immediate Media Company'),
              ('Condé Nast');

INSERT INTO magazine_batchsize (title, quantity, publisher_id)
VALUES ('National Geographic', 50, 1),
              ('National Trust Magazine', 40, 2),
              ('Radio Times', 25, 3),
              ('BBC Good Food', 20, 3),
              ('BBC Gardeners'' World', 20, 3),
              ('BBC Top Gear Magazine', 20, 3),
              ('Golf Digest', 20, 4),
              ('Vogue', 20, 4),
              ('The New Yorker', 20, 4),
              ('Vanity Fair', 20, 4),
              ('GQ', 20, 4),
              ('Bon Appétit', 20, 4),
              ('Architectural Digest', 20, 4),
              ('Condé Nast Traveler', 20, 4),
              ('Wired', 20, 4);

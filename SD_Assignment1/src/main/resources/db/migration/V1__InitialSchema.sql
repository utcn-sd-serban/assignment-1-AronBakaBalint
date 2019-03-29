CREATE TABLE IF NOT EXISTS question
(
	id serial PRIMARY KEY,
	title VARCHAR(32) NOT NULL,
	body VARCHAR(64) NOT NULL,
	author VARCHAR(32) NOT NULL,
	creationdate VARCHAR(30) NOT NULL,
	score int	
);

CREATE TABLE IF NOT EXISTS answers
(
	answerid serial PRIMARY KEY,
	author VARCHAR(32) NOT NULL,
	questionid int NOT NULL,
	answer VARCHAR(64) NOT NULL,
	creationdate VARCHAR(32) NOT NULL,
	score int
);

CREATE TABLE IF NOT EXISTS users
(
	id serial PRIMARY KEY,
	username VARCHAR(32) NOT NULL,
	password VARCHAR(64) NOT NULL
	
);

CREATE TABLE IF NOT EXISTS qvotes
(
	voteid serial PRIMARY KEY,
	questionid int NOT NULL,
	author VARCHAR(32) NOT NULL,
	score int,
	vote_type int
);

CREATE TABLE IF NOT EXISTS avotes
(
	voteid serial PRIMARY KEY,
	answerid int NOT NULL,
	author VARCHAR(32) NOT NULL,
	score int,
	vote_type int
);

CREATE TABLE IF NOT EXISTS tags
(
	tagid serial PRIMARY KEY,
	questionid int,
	tags VARCHAR(64)	
);
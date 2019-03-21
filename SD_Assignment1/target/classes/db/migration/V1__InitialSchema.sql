CREATE TABLE IF NOT EXISTS question
(
	id serial PRIMARY KEY,
	title VARCHAR(32) NOT NULL,
	text VARCHAR(64) NOT NULL
	
);

CREATE TABLE IF NOT EXISTS users
(
	id serial PRIMARY KEY,
	username VARCHAR(32) NOT NULL,
	password VARCHAR(64) NOT NULL
	
);

CREATE TABLE IF NOT EXISTS tags
(
	tagid serial PRIMARY KEY,
	questionid int,
	tags VARCHAR(64)	
);
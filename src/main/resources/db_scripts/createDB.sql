CREATE DATABASE quizowanie;
USE quizowanie;

CREATE TABLE question (
	quest_id INT NOT NULL,
	title VARCHAR(128) NOT NULL,
	description VARCHAR(500),
	PRIMARY KEY (quest_id)
);

CREATE TABLE answer (
	ans_id INT NOT NULL,
	text VARCHAR(256) NOT NULL,
	is_correct BOOLEAN NOT NULL,
	PRIMARY KEY (ans_id)
);

CREATE TABLE quiz (
	quiz_id INT NOT NULL,
	name VARCHAR(128) NOT NULL,
	maxTime int NOT NULL,
	PRIMARY KEY (quiz_id)
);

CREATE TABLE user (
	user_id INT NOT NULL,
	username VARCHAR(64) NOT NULL,
	password BINARY NOT NULL,
	user_type CHAR NOT NULL,
	PRIMARY KEY (user_id),
	UNIQUE (username)
);

CREATE TABLE quest_quiz (
	quest_id INT NOT NULL,
	quiz_id INT NOT NULL,
	CONSTRAINT quest_guiz_pk PRIMARY KEY (quest_id, quiz_id)
);

CREATE TABLE quest_ans (
	quest_id INT NOT NULL,
	ans_id INT NOT NULL,
	CONSTRAINT quest_ans_pk PRIMARY KEY (quest_id, ans_id)
);

ALTER TABLE quest_quiz 
	ADD FOREIGN KEY (quest_id) REFERENCES question(quest_id),
	ADD FOREIGN KEY (quiz_id) REFERENCES quiz(quiz_id);

ALTER TABLE quest_ans
	ADD FOREIGN KEY (quest_id) REFERENCES question(quest_id),
	ADD FOREIGN KEY (ans_id) REFERENCES answer(ans_id);


INSERT INTO question VALUES (1, 'What does the fox say?', 'Some description');

INSERT INTO answer VALUES (1, 'Hatee-hatee-hatee-ho', true);
INSERT INTO answer VALUES (2, 'Fox does not say anything', false);

INSERT INTO quiz VALUES (1, 'Fox quiz', 12);

INSERT into quest_ans VALUES (1, 1);
INSERT into quest_ans VALUES (1, 2);

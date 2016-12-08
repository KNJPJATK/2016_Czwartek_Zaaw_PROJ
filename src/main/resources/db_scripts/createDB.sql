CREATE TABLE question (
	id INT NOT NULL,
	title VARCHAR(128) NOT NULL,
	description TEXT,
	PRIMARY KEY (id)
);

CREATE TABLE answer (
	id INT NOT NULL,
	text VARCHAR(256) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE quiz (
	id INT NOT NULL,
	name VARCHAR(128) NOT NULL,
	max_time int NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE vuser (
	id INT NOT NULL,
	username VARCHAR(64) NOT NULL,
	password TEXT NOT NULL,
	user_type CHAR NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (username)
);

CREATE TABLE question_quiz (
  	id INT NOT NULL,
	question_id INT NOT NULL,
	quiz_id INT NOT NULL,
	CONSTRAINT question_quiz_pk PRIMARY KEY (id),
	CONSTRAINT unique_question_id_quiz_id UNIQUE (question_id, quiz_id)
);

CREATE TABLE question_answer (
  	id INT NOT NULL,
	question_id INT NOT NULL,
	answer_id INT NOT NULL,
	is_correct BOOLEAN NOT NULL,
	CONSTRAINT question_answer_pk PRIMARY KEY (id),
	CONSTRAINT unique_question_id_answer_id UNIQUE (question_id, answer_id)
);

ALTER TABLE question_quiz
	ADD FOREIGN KEY (question_id) REFERENCES question(id),
	ADD FOREIGN KEY (quiz_id) REFERENCES quiz(id);

ALTER TABLE question_answer
	ADD FOREIGN KEY (question_id) REFERENCES question(id),
	ADD FOREIGN KEY (answer_id) REFERENCES answer(id);


INSERT INTO question(id, title, description) VALUES (1, 'What does the fox say?', 'Some description');
INSERT INTO question(id, title, description) VALUES (2, 'What is the best programming language?', 'Do not think too much!');
INSERT INTO question(id, title, description) VALUES (3, 'How many primitive data types are in Java?', 'You know it!');

INSERT INTO answer(id, text) VALUES (1, 'Hatee-hatee-hatee-ho');
INSERT INTO answer(id, text) VALUES (2, 'Fox does not say anything');
INSERT INTO answer(id, text) VALUES (3, 'Java!');
INSERT INTO answer(id, text) VALUES (4, 'CeKratka!');
INSERT INTO answer(id, text) VALUES (5, 'Asembler'); 
INSERT INTO answer(id, text) VALUES (6, '6');
INSERT INTO answer(id, text) VALUES (7, '7');
INSERT INTO answer(id, text) VALUES (8, '8');
INSERT INTO answer(id, text) VALUES (9, '9');

INSERT INTO quiz(id, name, max_time) VALUES (1, 'Fox quiz', 12);
INSERT INTO quiz(id, name, max_time) VALUES (2, 'Java quiz', 30);

INSERT into question_answer(id, question_id, answer_id, is_correct) VALUES (1, 1, 1, true);
INSERT into question_answer(id, question_id, answer_id, is_correct) VALUES (2, 1, 2, false);
INSERT into question_answer(id, question_id, answer_id, is_correct) VALUES (3, 2, 3, true);
INSERT into question_answer(id, question_id, answer_id, is_correct) VALUES (4, 2, 4, false);
INSERT into question_answer(id, question_id, answer_id, is_correct) VALUES (5, 2, 5, false);
INSERT into question_answer(id, question_id, answer_id, is_correct) VALUES (6, 3, 6, false);
INSERT into question_answer(id, question_id, answer_id, is_correct) VALUES (7, 3, 7, false);
INSERT into question_answer(id, question_id, answer_id, is_correct) VALUES (8, 3, 8, true);
INSERT into question_answer(id, question_id, answer_id, is_correct) VALUES (9, 3, 9, false);

INSERT INTO question_quiz (id, question_id, quiz_id) VALUES (1, 1, 1);
INSERT INTO question_quiz (id, question_id, quiz_id) VALUES (2, 2, 2);
INSERT INTO question_quiz (id, question_id, quiz_id) VALUES (3, 3, 2);




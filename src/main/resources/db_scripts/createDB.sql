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

CREATE TABLE correct_answer (
	id INT PRIMARY KEY,
	q_id INT, 
	a_id INT,
	FOREIGN KEY (q_id) REFERENCES question(id),
	FOREIGN KEY (a_id) REFERENCES answer(id)
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

CREATE TABLE quest_quiz (
  	id INT NOT NULL,
	question_id INT NOT NULL,
	quiz_id INT NOT NULL,
	CONSTRAINT quest_guiz_pk PRIMARY KEY (id),
	CONSTRAINT u_question_id_quiz_id UNIQUE (question_id, quiz_id)
);

CREATE TABLE quest_ans (
  	id INT NOT NULL,
	question_id INT NOT NULL,
	answer_id INT NOT NULL,
	CONSTRAINT quest_ans_pk PRIMARY KEY (id),
	CONSTRAINT u_question_id_answer_id UNIQUE (question_id, answer_id)
);

ALTER TABLE quest_quiz
	ADD FOREIGN KEY (question_id) REFERENCES question(id),
	ADD FOREIGN KEY (quiz_id) REFERENCES quiz(id);

ALTER TABLE quest_ans
	ADD FOREIGN KEY (question_id) REFERENCES question(id),
	ADD FOREIGN KEY (answer_id) REFERENCES answer(id);


INSERT INTO question(id, title, description) VALUES (1, 'What does the fox say?', 'Some description');
INSERT INTO question(id, title, description) VALUES (2, 'What is the best programming language?', "Don't think too much!");
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

INSERT into quest_ans(id, question_id, answer_id) VALUES (1, 1, 1);
INSERT into quest_ans(id, question_id, answer_id) VALUES (2, 1, 2);
INSERT into quest_ans(id, question_id, answer_id) VALUES (3, 2, 3);
INSERT into quest_ans(id, question_id, answer_id) VALUES (4, 2, 4);
INSERT into quest_ans(id, question_id, answer_id) VALUES (5, 2, 5);
INSERT into quest_ans(id, question_id, answer_id) VALUES (6, 3, 6);
INSERT into quest_ans(id, question_id, answer_id) VALUES (7, 3, 7);
INSERT into quest_ans(id, question_id, answer_id) VALUES (8, 3, 8);
INSERT into quest_ans(id, question_id, answer_id) VALUES (9, 3, 9);

INSERT into correct_answer(id, q_id, a_id) VALUES (1, 1, 1);
INSERT into correct_answer(id, q_id, a_id) VALUES (2, 2, 3);
INSERT into correct_answer(id, q_id, a_id) VALUES (3, 3, 8);

INSERT INTO quest_quiz (id, question_id, quiz_id) VALUES (1, 1, 1);
INSERT INTO quest_quiz (id, question_id, quiz_id) VALUES (2, 2, 2);
INSERT INTO quest_quiz (id, question_id, quiz_id) VALUES (3, 3, 2);




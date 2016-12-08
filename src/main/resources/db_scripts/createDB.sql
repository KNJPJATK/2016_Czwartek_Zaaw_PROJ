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
	q_id FOREIGN KEY REFERENCES question(id), 
	a_id FOREIGN KEY REFERENCES answer(id)
);

CREATE TABLE quiz (
	id INT NOT NULL,
	name VARCHAR(128) NOT NULL,
	maxTime int NOT NULL,
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

INSERT INTO answer(id, text, is_correct) VALUES (1, 'Hatee-hatee-hatee-ho', true);
INSERT INTO answer(id, text, is_correct) VALUES (2, 'Fox does not say anything', false);

INSERT INTO quiz(id, name, maxTime) VALUES (1, 'Fox quiz', 12);

INSERT into quest_ans(id, question_id, answer_id) VALUES (1, 1, 1);
INSERT into quest_ans(id, question_id, answer_id) VALUES (2, 1, 2);

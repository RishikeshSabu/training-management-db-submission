CREATE DATABASE training_management;
USE training_management;


CREATE TABLE course(
	course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(20)
);

CREATE TABLE course_topic(
	topic_id INT PRIMARY KEY AUTO_INCREMENT,
    topic_name VARCHAR(30),
    course_id INT,
    FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE
);

CREATE TABLE batch(
	batch_id INT PRIMARY KEY AUTO_INCREMENT,
    batch_name VARCHAR(30),
    start_date DATE,
    stop_date DATE,
    batch_code VARCHAR(30),
    course_id INT,
    FOREIGN KEY (course_id) REFERENCES course(course_id)
);

CREATE TABLE trainer(
	trainer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    email VARCHAR(50),
    phone VARCHAR(10)
);
CREATE TABLE batch_trainer(
	batch_id INT,
    trainer_id INT,
    PRIMARY KEY(batch_id,trainer_id),
    FOREIGN KEY (batch_id) REFERENCES batch(batch_id),
    FOREIGN KEY (trainer_id) REFERENCES trainer(trainer_id)
);

CREATE TABLE candidate(
	candidate_id INT PRIMARY KEY,
    name VARCHAR(30),
    email VARCHAR(30),
    phone VARCHAR(10),
    address VARCHAR(100)
);


CREATE TABLE candidate_status(
	cand_id INT,
    batch_id INT,
    status VARCHAR(15),
    PRIMARY KEY(cand_id,batch_id),
    FOREIGN KEY (cand_id) REFERENCES candidate(candidate_id),
	FOREIGN KEY (batch_id) REFERENCES batch(batch_id)
);

CREATE TABLE assignment(
	assignment_id INT PRIMARY KEY auto_increment,
    batch_id INT,
    title VARCHAR(50),
    decription VARCHAR(100),
    assignment_date DATE,
    due_date DATE,
    FOREIGN KEY (batch_id) REFERENCES batch(batch_id)
);

CREATE TABLE submission(
	assignment_id INT,
    candidate_id INT,
    submission_date DATE,
    score INT,
    PRIMARY KEY (assignment_id,candidate_id),
    FOREIGN KEY (assignment_id) REFERENCES assignment(assignment_id),
    FOREIGN KEY (candidate_id) REFERENCES candidate(candidate_id)
);



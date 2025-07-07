USE training_management;

INSERT INTO course(course_name) VALUES
('Software Testing'),
('App development'),
('Intro to SQL');

SELECT * FROM course;

INSERT INTO course_topic VALUES
(1,'Intro to Software Testing',2),
(2,'Types of testing',1),
(3,'SDLC',3);

SELECT * FROM course_topic;

INSERT INTO batch(batch_name,start_date,stop_date,batch_code,course_id) VALUES
('Batch 1','2025-06-01','2025-12-01','B1',1),
('Batch 2','2025-02-01','2025-08-01','B2',2),
('Batch 3','2025-03-01','2025-09-01','B3',3);

SELECT * FROM batch;

INSERT INTO trainer(name,email,phone) VALUES
('Akshay John', 'akshay@gmail.com', '9876543210'),
('Rohith MS', 'rohith@gmail.com', '9812345670'),
('Abhiraj MR', 'abhiraj@gmail.com', '9870123456');
SELECT * FROM trainer;
INSERT INTO batch_trainer (batch_id,trainer_id) VALUES 
(4, 1),
(5, 1),
(6, 2);

SELECT * from batch_trainer;

INSERT INTO candidate (candidate_id, name, email, phone, address) VALUES 
(1, 'Divinit', 'divinit@gmail.com', '9123456709', 'kochi'),
(2, 'Abbhinav Prakash', 'abhbinav@gmail.com', '9322556789', 'Kochi'),
(3, 'Ajay CH', 'ajay@gmail.com', '9995883219', 'Palakkad');

INSERT INTO candidate_status (cand_id, batch_id, status) VALUES 
(1, 4, 'In Progress'),
(2, 4, 'In Progress'),
(3, 5, 'Completed');

INSERT INTO assignment (batch_id, title, decription, assignment_date, due_date) VALUES
(4,'Testing assignment1 ','Basics of testing','2025-07-04','2025-07-11'),
(5,'Flutter assignment1 ','Basics of Flutter','2025-07-03','2025-07-10'),
(4,'Testing assignment2 ','Types of testing','2025-07-12','2025-07-19');

SELECT * FROM assignment;

iNSERT INTO submission (assignment_id, candidate_id, submission_date, score) VALUES 
(1, 1, '2025-07-06', 85),
(1, 2, '2025-07-09', 90),
(2, 3, '2025-07-06', 14);

USE training_management;

-- Get all batches a candidate is enrolled in, with their status.
SELECT c.candidate_id,b.batch_id,b.batch_name
FROM candidate c
JOIN candidate_status cs ON c.candidate_id=cs.cand_id
JOIN batch b ON b.batch_id=cs.batch_id;

-- Get all trainers assigned to a batch. 
SELECT t.trainer_id,t.name,b.batch_name
FROM trainer t
JOIN batch_trainer bt
ON bt.trainer_id=t.trainer_id
JOIN batch b
ON b.batch_id=bt.batch_id;

-- Get all topics under a course.
SELECT ct.topic_name,c.course_name
FROM course_topic ct
JOIN course c
ON ct.course_id=c.course_id;

-- List assignment scores for a candidate in a batch.
SELECT c.name,b.batch_name,s.score,a.title
FROM submission s
JOIN candidate c ON s.candidate_id = c.candidate_id
JOIN assignment a ON s.assignment_id = a.assignment_id
JOIN batch b ON a.batch_id = b.batch_id
WHERE c.candidate_id = 1 AND b.batch_id = 4;

-- List candidates with status "Completed" in a given batch. 
SELECT c.name,b.batch_name,cs.status
FROM candidate c
JOIN candidate_status cs
ON cs.cand_id=c.candidate_id
JOIN batch b
ON b.batch_id=cs.batch_id
WHERE cs.status='Completed';
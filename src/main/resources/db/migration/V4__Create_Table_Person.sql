CREATE TABLE tb_person
(
    person_id VARCHAR(36) PRIMARY KEY DEFAULT gen_random_uuid(),
    code      INTEGER,
    firt_name VARCHAR(255),
    last_name VARCHAR(255),
    password  VARCHAR(255)
);

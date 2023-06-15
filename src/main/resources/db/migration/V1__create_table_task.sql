
CREATE TABLE IF NOT EXISTS Task (
    id              SERIAL PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    description     TEXT,
    due_date        DATE,
    due_time        TIME,
    is_completed    BOOLEAN
);
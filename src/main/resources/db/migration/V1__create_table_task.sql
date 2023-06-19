
CREATE TABLE IF NOT EXISTS Task (
    id              BIGSERIAL PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    description     TEXT,
    due_date        DATE,
    due_time        TIME,
    is_completed    BOOLEAN NOT NULL
);
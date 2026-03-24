-- =========================
-- ENUMS
-- =========================
CREATE TYPE request_status AS ENUM (
    'PENDING',
    'APPROVED',
    'REJECTED'
);

CREATE TYPE user_role AS ENUM (
    'ADMIN'
);

-- =========================
-- TABLA: USERS
-- =========================
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    
    role user_role NOT NULL DEFAULT 'ADMIN',
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

-- =========================
-- TABLA: RESOURCES
-- =========================
CREATE TABLE resources (
    id SERIAL PRIMARY KEY,
    
    name VARCHAR(100) NOT NULL,
    description TEXT,
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    
    CONSTRAINT unique_resource_name UNIQUE(name)
);

-- =========================
-- TABLA: REQUESTS
-- =========================
CREATE TABLE requests (
    id SERIAL PRIMARY KEY,
    
    applicant_name VARCHAR(100) NOT NULL,
    applicant_email VARCHAR(150) NOT NULL,
    
    resource_id INTEGER NOT NULL,
    status request_status NOT NULL DEFAULT 'PENDING',
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    
    CONSTRAINT fk_resource
        FOREIGN KEY (resource_id)
        REFERENCES resources(id)
        ON DELETE RESTRICT
);

-- =========================
-- ÍNDICES
-- =========================
CREATE INDEX idx_requests_status ON requests(status);
CREATE INDEX idx_requests_email ON requests(applicant_email);
CREATE INDEX idx_requests_name ON requests(applicant_name);
CREATE INDEX idx_resources_name ON resources(name);

-- =========================
-- TRIGGER updated_at
-- =========================
CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = CURRENT_TIMESTAMP;
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_users_updated
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_resources_updated
BEFORE UPDATE ON resources
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_requests_updated
BEFORE UPDATE ON requests
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

-- =========================
-- DATA INICIAL (ADMIN)
-- =========================
INSERT INTO users (name, email, password, role)
VALUES (
    'Admin',
    'admin@test.com',
    '$$2a$10$cHYnTJg0p45VM6ekVinFAetMxPVD8kQ/N7SxXlBcufX56Y1J9fBh.', 
    'ADMIN'
);
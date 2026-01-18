CREATE TABLE IF NOT EXISTS conventions (
    convention_id VARCHAR(255) PRIMARY KEY,
    agency_name VARCHAR(255) NOT NULL,
    convention_type VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    description VARCHAR(1000),
    contact_person VARCHAR(255) NOT NULL,
    contact_email VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS convention_required_documents (
    convention_id VARCHAR(255) NOT NULL,
    document_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (convention_id, document_name),
    FOREIGN KEY (convention_id) REFERENCES conventions(convention_id) ON DELETE CASCADE
);
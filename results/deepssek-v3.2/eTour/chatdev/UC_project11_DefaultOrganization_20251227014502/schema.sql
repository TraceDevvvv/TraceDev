/**
 * SQL Schema file for Convention History database.
 * This file is used by H2 database to initialize the schema.
 */
-- Create conventions table
CREATE TABLE IF NOT EXISTS conventions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    point_of_rest VARCHAR(100) NOT NULL,
    date VARCHAR(20) NOT NULL,
    description TEXT
);
-- Create index for faster queries on point_of_rest
CREATE INDEX IF NOT EXISTS idx_point_of_rest ON conventions(point_of_rest);
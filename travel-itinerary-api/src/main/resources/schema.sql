-- Create account table
CREATE TABLE IF NOT EXISTS account (
                                       id SERIAL PRIMARY KEY,
                                       username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
    );

-- Create destination table
CREATE TABLE IF NOT EXISTS destination (
                                           id SERIAL PRIMARY KEY,
                                           name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    location VARCHAR(255),
    best_time_to_visit VARCHAR(255)
    );

-- Create itinerary table
CREATE TABLE IF NOT EXISTS itinerary (
                                         id SERIAL PRIMARY KEY,
                                         name VARCHAR(255),
    start_date VARCHAR(255),
    end_date VARCHAR(255),
    user_id BIGINT REFERENCES account(id),
    destination_id BIGINT REFERENCES destination(id)
    );

-- Create booking table
CREATE TABLE IF NOT EXISTS booking (
                                       id SERIAL PRIMARY KEY,
                                       type VARCHAR(255),
    details VARCHAR(255),
    booking_date DATE,
    itinerary_id BIGINT REFERENCES itinerary(id)
    );

-- Create activity table
CREATE TABLE IF NOT EXISTS activity (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(255),
    description VARCHAR(255),
    date DATE,
    itinerary_id BIGINT REFERENCES itinerary(id)
    );

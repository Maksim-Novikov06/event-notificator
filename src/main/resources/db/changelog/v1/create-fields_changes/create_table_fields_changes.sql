CREATE TABLE fields_changes (
                                id serial PRIMARY KEY,

                                notification_id BIGINT NOT NULL UNIQUE REFERENCES notifications(id) ON DELETE CASCADE,

                                name VARCHAR(255),
                                max_places INTEGER,
                                date TIMESTAMP,
                                cost INTEGER,
                                duration INTEGER,
                                location_id BIGINT,
                                status VARCHAR(255)
);
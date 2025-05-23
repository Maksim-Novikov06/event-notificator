CREATE TABLE notification_registrations (
                                            notification_id BIGINT NOT NULL,
                                            registrations BIGINT NOT NULL,
                                            PRIMARY KEY (notification_id, registrations),
                                            FOREIGN KEY (notification_id) REFERENCES notifications(id) ON DELETE CASCADE
);
CREATE TABLE notifications (
                              id serial PRIMARY KEY,
                              event_id BIGINT NOT NULL,
                              modifier_id BIGINT,
                              owner_event_id BIGINT NOT NULL,
                              is_read BOOLEAN NOT NULL,
                              created_at TIMESTAMP NOT NULL
);
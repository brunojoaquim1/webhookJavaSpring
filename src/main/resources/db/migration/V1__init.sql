create table webhook_events (
    id bigserial primary key,
    message_id varchar(255),
    payload text,
    processed boolean default false,
    received_at timestamp
);

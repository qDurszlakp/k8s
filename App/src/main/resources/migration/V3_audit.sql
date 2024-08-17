--liquibase formatted sql

--changeset durszlak:8
CREATE TABLE AUDIT
(
    AUDIT_ID      BIGSERIAL PRIMARY KEY,
    URL           TEXT,
    USER_UUID     UUID,
    ACTION_TIME   TIMESTAMPTZ
);

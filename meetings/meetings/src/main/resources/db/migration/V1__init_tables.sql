CREATE TABLE IF NOT EXISTS meeting (
    id BIGSERIAL PRIMARY KEY,
    location VARCHAR(200) NOT NULL,
    starts_at TIMESTAMP NOT NULL,
    date TIMESTAMP NOT NULL,
    host_user_id BIGINT NOT NULL,
    client_user_id BIGINT NOT NULL,
    status VARCHAR(200) NOT NULL,
    duration INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    price INTEGER
    );



CREATE TABLE IF NOT EXISTS proposition_offre (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(200) NOT NULL,
    meeetingId BIGINT NOT NULL,
    proposerId BIGINT NOT NULL,
    price INTEGER,
    date TIMESTAMP NOT NULL DEFAULT NOW()
    );


CREATE TABLE IF NOT EXISTS announce (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL,
    location VARCHAR(200) NOT NULL,
    title VARCHAR(200) NOT NULL,
    hostUserId BIGINT NOT NULL,
    disposability JSON,
    price INTEGER,
    createdAt TIMESTAMP NOT NULL DEFAULT NOW()
    );



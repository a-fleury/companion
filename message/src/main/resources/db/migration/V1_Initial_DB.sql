-- Création de la table conversation
CREATE TABLE conversation (
    id SERIAL PRIMARY KEY,
    user1_id VARCHAR(50) NOT NULL,
    user2_id VARCHAR(50) NOT NULL
);

-- Création de la table message
CREATE TABLE message (
    id SERIAL PRIMARY KEY,
    conversation_id INT NOT NULL,
    message_type VARCHAR(20) NOT NULL,
    sender_id VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    readed BOOLEAN NOT NULL DEFAULT FALSE,
    sent_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NULL
);

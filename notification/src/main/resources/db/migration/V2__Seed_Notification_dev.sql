INSERT INTO notification (user_id, subject, content, image_url, created_at, is_read)
VALUES
    ('123', 'Bienvenue !', 'Bienvenue sur notre application.', 'https://example.com/welcome.jpg', NOW(), FALSE),
    ('123', 'Nouvelle fonctionnalité', 'Découvrez notre nouvelle fonctionnalité.', 'https://example.com/feature.jpg', NOW(), FALSE);
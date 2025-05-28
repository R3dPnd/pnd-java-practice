-- Create test users (password is 'password' encoded with BCrypt)
INSERT INTO users (username, password, email, enabled)
VALUES 
    ('admin', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'admin@example.com', true),
    ('user', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'user@example.com', true)
ON CONFLICT (username) DO NOTHING;

-- Create user roles
INSERT INTO user_roles (user_id, role)
SELECT id, 'ADMIN' FROM users WHERE username = 'admin'
ON CONFLICT DO NOTHING;

INSERT INTO user_roles (user_id, role)
SELECT id, 'USER' FROM users WHERE username = 'admin'
ON CONFLICT DO NOTHING;

INSERT INTO user_roles (user_id, role)
SELECT id, 'USER' FROM users WHERE username = 'user'
ON CONFLICT DO NOTHING; 
DELETE FROM users;
DELETE FROM user_role;

INSERT INTO users (id, active, username, password) VALUES (1, true , 'test', '$2y$12$BKMGSZTvVqBgwwEatqTxHOQ1YEdHnqqHnbaWNMZK.aZPiNzZGHj9q');

INSERT INTO user_role (user_id, roles) VALUES (1, 'USER');
DELETE FROM albums;
DELETE FROM artists;
INSERT INTO artists(_id,name) VALUES (1, 'Metallica');
INSERT INTO albums(_id,name,artist) VALUES(1, 'Album 1', 1);
INSERT INTO albums(_id,name,artist) VALUES(2, 'Album 2', 1);

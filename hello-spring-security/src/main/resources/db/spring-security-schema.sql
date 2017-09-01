CREATE TABLE users (
  username VARCHAR (50) NOT NULL PRIMARY KEY,
  PASSWORD VARCHAR (50) NOT NULL,
  enabled BOOLEAN NOT NULL
) ;

CREATE TABLE authorities(
username VARCHAR(50) NOT NULL,
authority VARCHAR(50) NOT NULL,
FOREIGN KEY fk_authorities_users (username) REFERENCES users(username)
);
CREATE UNIQUE INDEX ix_auth_username ON authorities(username,authority);

CREATE TABLE groups(
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
group_name VARCHAR(50) NOT NULL
);

CREATE TABLE group_authorities(
group_id BIGINT NOT NULL,
authority VARCHAR(50) NOT NULL,
FOREIGN KEY (group_id) REFERENCES groups(id)
);

CREATE TABLE group_members(
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
group_id BIGINT NOT NULL,
FOREIGN KEY(group_id) REFERENCES groups(id),
FOREIGN KEY(username) REFERENCES users(username)
);

CREATE TABLE persistent_logins(
username VARCHAR(50) NOT NULL,
series VARCHAR(64) PRIMARY KEY ,
token VARCHAR(64) NOT NULL,
last_used TIMESTAMP NOT NULL
);
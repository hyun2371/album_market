use album_market;
drop table if exists album;

CREATE TABLE album (
       album_id	bigint PRIMARY KEY AUTO_INCREMENT,
       title	varchar(50)	NOT NULL,
       artist	varchar(50)	NOT NULL,
       category	varchar(50)	NOT NULL,
       img_url	varchar(255)	NOT NULL,
       release_date	date NOT NULL,
       price	int	NOT NULL,
       stock	int	NULL	DEFAULT 0,
       like_count	int	NULL	DEFAULT 0,
       created_at	timestamp	NULL	DEFAULT current_timestamp,
       updated_at	timestamp	NULL	DEFAULT current_timestamp
);

drop table if exists song;
CREATE TABLE song (
      song_id	bigint	PRIMARY KEY AUTO_INCREMENT,
      album_id	bigint	NOT NULL,
      name	varchar(50)	NOT NULL,
      is_title	tinyint(1)	NULL	DEFAULT 0,
      created_at	timestamp	NULL	DEFAULT current_timestamp,
      updated_at	timestamp	NULL	DEFAULT current_timestamp
);

drop table if exists album_like;
CREATE TABLE album_like (
    album_like_id	bigint	PRIMARY KEY AUTO_INCREMENT,
    member_id	bigint	NOT NULL,
    album_id	bigint	NOT NULL,
    created_at	timestamp	NULL	DEFAULT current_timestamp,
    updated_at	timestamp	NULL	DEFAULT current_timestamp
);
drop table if exists member;
CREATE TABLE member (
    member_id	bigint	PRIMARY KEY AUTO_INCREMENT,
    email	varchar(50)	NOT NULL,
    password	varchar(60)	NOT NULL,
    name	varchar(30)	NOT NULL,
    phone_number	varchar(30)	NOT NULL,
    city	varchar(30)	NULL,
    street	varchar(30)	NOT NULL,
    zipcode	varchar(30)	NOT NULL,
    balance	int	NULL	DEFAULT 0,
    created_at	timestamp	NULL	DEFAULT current_timestamp,
    updated_at	timestamp	NULL	DEFAULT current_timestamp
);

drop table if exists orders;
CREATE TABLE orders (
    order_id	bigint	PRIMARY KEY AUTO_INCREMENT,
    member_id	bigint	NOT NULL,
    album_id	bigint	NOT NULL,
    total_price	int	NOT NULL,
    order_status varchar(30)	NOT NULL,
    created_at	timestamp	NULL	DEFAULT current_timestamp,
    updated_at	timestamp	NULL	DEFAULT current_timestamp
);

use ssafy;

drop table comments;
drop table articles;
drop table accounts;

create table accounts(
account_no INT auto_increment,
account_email VARCHAR(64) NOT NULL unique,
account_password VARCHAR(256) NOT NULL,
account_name VARCHAR(32) NOT NULL,
account_gitlab_email VARCHAR(64),
account_role_name varchar(32),
account_register_date DATETIME NOT NULL default CURRENT_TIMESTAMP,
PRIMARY KEY (account_no)
);

create table articles(
article_no INT AUTO_INCREMENT,
article_title VARCHAR(64) not null,
article_content VARCHAR(4096) not null,
article_imgsrc VARCHAR(128) not null,
article_category_id INT NOT NULL,
article_write_date DATETIME NOT NULL default CURRENT_TIMESTAMP,
article_views INT DEFAULT 0 NOT NULL,

account_no INT,
account_name VARCHAR(32),

PRIMARY KEY (article_no),
CONSTRAINT fk_articles_accounts_account_no 
FOREIGN KEY (account_no)
REFERENCES accounts (account_no)
on update cascade
on delete cascade
);

create table comments(
comment_no INT AUTO_INCREMENT,
comment_content VARCHAR(2048) NOT NULL,
comment_write_date DATETIME NOT NULL default CURRENT_TIMESTAMP,

article_no INT,
account_no INT,
account_name VARCHAR(32),

PRIMARY KEY (comment_no),

CONSTRAINT fk_comments_accounts_account_no 
FOREIGN KEY (account_no)
REFERENCES accounts (account_no)
on update cascade
on delete cascade,

CONSTRAINT fk_comments_articles_article_no
FOREIGN KEY (article_no)
REFERENCES articles (article_no)
on update cascade
on delete cascade
);

select * from accounts;
select * from articles;
select * from comments;

INSERT INTO `ssafy`.`accounts` (`account_password`, `account_name`, `account_email`, `account_gitlab_email`) VALUES ( '1234', '이기문', 'aaaa@naver.com', '');
INSERT INTO `ssafy`.`accounts` ( `account_password`, `account_name`, `account_email`, `account_gitlab_email`) VALUES ( '1234', '최재형', 'bbbb@naver.com', '');
INSERT INTO `ssafy`.`accounts` ( `account_password`, `account_name`, `account_email`, `account_gitlab_email`) VALUES ( '1234', '강성진', 'cccc@naver.com', '');

INSERT INTO `ssafy`.`articles` (`article_title`, `article_content`, `article_imgsrc`, `article_category_id`, `account_no`, `account_name`) 
VALUES ('art_aaa', 'art_aaa', 'https://picsum.photos/200/300', '1', '1', '이기문');
INSERT INTO `ssafy`.`articles` (`article_title`, `article_content`, `article_imgsrc`, `article_category_id`, `account_no`, `account_name`) VALUES ('art_bbb', 'art_bbb', 'https://picsum.photos/200/300', '1', '1', '이기문');
INSERT INTO `ssafy`.`articles` (`article_title`, `article_content`, `article_imgsrc`, `article_category_id`, `account_no`, `account_name`) VALUES ('art_ccc', 'art_ccc', 'https://picsum.photos/200/300', '2', '2', '최재형');
INSERT INTO `ssafy`.`articles` (`article_title`, `article_content`, `article_imgsrc`, `article_category_id`, `account_no`, `account_name`) VALUES ('art_ddd', 'art_ddd', 'https://picsum.photos/200/300', '2', '3', '강성진');

INSERT INTO `ssafy`.`comments` (`comment_content`, `article_no`, `account_no`, `account_name`) VALUES ('바보', '1', '1', '이기문');
INSERT INTO `ssafy`.`comments` (`comment_content`, `article_no`, `account_no`, `account_name`) VALUES ('멍청이', '2', '1', '이기문');
INSERT INTO `ssafy`.`comments` (`comment_content`, `article_no`, `account_no`, `account_name`) VALUES ('바보바보', '3', '2', '이기문');
INSERT INTO `ssafy`.`comments` (`comment_content`, `article_no`, `account_no`, `account_name`) VALUES ('멍청이멍청', '4', '3', '이기문');

# vanillax-framework-webmvc-example

* Get Started
    - copy db/vanilla-sample.db to db/vanilla.db
    - run Tomcat8Launcher (test/java/vanillax/webmvc/example/Tomcat8Launcher.java)
    - open on browser : http://localhost:8080/
    
* DB Setup
```
CREATE TABLE File  ( 
	id      	int(11)  NOT NULL,
	fileName	varchar(200)  NOT NULL,
	fileExt 	varchar(50)  NOT NULL,
	filePath	varchar(300)  NOT NULL,
	regUser 	varchar(100)  NULL ,
	regDate 	timestamp  NULL ,
	modUser 	varchar(100)  NULL ,
	modDate 	timestamp NULL ,
	PRIMARY KEY(id) 
)
;

CREATE TABLE Users  ( 
	userId         	varchar(100)  NOT NULL,
	userName       	varchar(200) NOT NULL,
	passwd         	varchar(200)  NOT NULL,
	email          	varchar(200) NULL ,
	permissionLevel	int(11)  NOT NULL,
	regUser        	varchar(100)  NULL ,
	regDate        	timestamp NULL ,
	modUser        	varchar(100)  NULL ,
	modDate        timestamp NULL ,
	PRIMARY KEY(userId) 
)
;

CREATE TABLE UsersToken  ( 
	id             	int(11) NOT NULL,
	userId         	varchar(100)  NOT NULL,
	token          	varchar(100)  NOT NULL,
	tokenUpdateDate	varchar(14)  NOT NULL,
	regUser        	varchar(100)  NULL ,
	regDate        	timestamp NULL ,
	modUser        	varchar(100)  NULL ,
	modDate        	timestamp NULL ,
	PRIMARY KEY(id) 
)
;
create unique index UsersToken_IDX01 on UsersToken(token)
;

CREATE TABLE CommonCode  ( 
	codeGroup  	varchar(100) NOT NULL,
	description	varchar(200) NOT NULL,
	deleteYn   	char(1)  NOT NULL DEFAULT 'N',
	regUser    	varchar(100)  NULL ,
	regDate    	timestamp NULL ,
	modUser    	varchar(100)  NULL ,
	modDate    	timestamp NULL ,
	PRIMARY KEY(codeGroup) 
)
;

CREATE TABLE CommonCodeDetail  ( 
	codeGroup 	varchar(100)  NOT NULL,
	codeDetail	varchar(100) NOT NULL,
	codeName  	varchar(200)  NOT NULL,
	sort      	int(11)  NOT NULL,
	regUser   	varchar(100)  NULL ,
	regDate   	timestamp NULL ,
	modUser   	varchar(100) NULL ,
	modDate   	timestamp NULL ,
	PRIMARY KEY(codeGroup,codeDetail) 
)
```

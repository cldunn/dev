USE [TestSys]
GO

EXEC sp_msforeachtable "ALTER TABLE ? NOCHECK CONSTRAINT all"

IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'USER_INFO')    
BEGIN
	drop table USER_INFO;
END

IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'USER_AUTHENTICATION')    
BEGIN
	drop table USER_AUTHENTICATION;
END

create table USER_AUTHENTICATION (
    ID bigint not null,
    CREATED_BY varchar(255) not null,
    CREATED_DATE datetime2 not null,
    MODIFIED_BY varchar(255),
    MODIFIED_DATE datetime2,
    VERSION bigint not null,
    IS_LOCKED bit,
    LAST_LOGIN_DATE datetime2,
    LOGIN varchar(255) not null,
    LOGIN_ATTEMPTS int,
    PASSWORD varchar(255) not null,
    PASSWORD_EXPIRE_DATE date,
    PASSWORD_MODIFIED_DATE datetime2,
    STATUS varchar(255) not null,
	CONSTRAINT [PK_USER_AUTHENTICATION] PRIMARY KEY CLUSTERED (ID)
    
);

create table USER_INFO (
    ID bigint not null,
    CREATED_BY varchar(255) not null,
    CREATED_DATE datetime2 not null,
    MODIFIED_BY varchar(255),
    MODIFIED_DATE datetime2,
    VERSION bigint not null,
    ADDRESS_LINE1 varchar(255),
    ADDRESS_LINE2 varchar(255),
    CITY varchar(255),
    COUNTRY varchar(255),
    DATE_FORMAT varchar(255),
    EMAIL_ADDRESS varchar(255) not null,
    FAX varchar(255),
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    LOCALE_COUNTRY varchar(255) default 'us',
    LOCALE_LANGUAGE varchar(255) not null default 'en',
    LOCALE_VARIANT varchar(255),
    PRIMARY_PHONE varchar(255),
    PROVINCE varchar(255),
    SECONDARY_PHONE varchar(255),
    STATE varchar(255),
    TIME_FORMAT varchar(255),
    TIME_ZONE varchar(255),
    ZIP varchar(255),
    USER_AUTHENTICATION_ID bigint not null,
    CONSTRAINT [PK_USER_INFO] PRIMARY KEY CLUSTERED (ID),
	CONSTRAINT FK_USER_INFO_AUTHENTICATION_ID FOREIGN KEY (USER_AUTHENTICATION_ID) REFERENCES USER_AUTHENTICATION (ID) ON DELETE CASCADE
);

alter table USER_INFO 
    add constraint UK_USER_AUTHENTICATION_ID unique (USER_AUTHENTICATION_ID);

alter table USER_AUTHENTICATION 
    add constraint UK_LOGIN unique (LOGIN);

alter table USER_INFO 
    add constraint UK_EMAIL_ADDRESS unique (EMAIL_ADDRESS);

EXEC sp_msforeachtable "ALTER TABLE ? WITH CHECK CHECK CONSTRAINT all"

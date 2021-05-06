USE [CldBizSysTest]
GO

EXEC sp_msforeachtable "ALTER TABLE ? NOCHECK CONSTRAINT all"

-- IF EXISTS, REMOVE UK_EMAIL_ADDRESS and UK_LOGIN, NECESSARY?

IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'USER_INFO')    
BEGIN
	drop table USER_INFO;
END

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
    IS_LOCKED bit,
    LAST_LOGIN_DATE datetime2,
    LAST_NAME varchar(255),
    LOCALE_COUNTRY varchar(255),
    LOCALE_LANGUAGE varchar(255) not null,
    LOCALE_VARIANT varchar(255),
    LOGIN varchar(255) not null,
    LOGIN_ATTEMPTS int,
    PASSWORD varchar(255) not null,
    PASSWORD_EXPIRE_DATE date,
    PASSWORD_MODIFIED_DATE datetime2,
    PRIMARY_PHONE varchar(255),
    PROVINCE varchar(255),
    SECONDARY_PHONE varchar(255),
    STATE varchar(255),
    STATUS varchar(255) not null,
    TIME_FORMAT varchar(255),
    TIME_ZONE varchar(255),
    ZIP varchar(255),
    CONSTRAINT [PK_USER_INFO] PRIMARY KEY CLUSTERED (ID)
);

alter table USER_INFO 
    add constraint UK_EMAIL_ADDRESS unique (EMAIL_ADDRESS);

alter table USER_INFO 
    add constraint UK_LOGIN unique (LOGIN);

EXEC sp_msforeachtable "ALTER TABLE ? WITH CHECK CHECK CONSTRAINT all"
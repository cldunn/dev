	USE [CldBizTest]
	GO
	
	EXEC sp_MSforeachtable 'ALTER TABLE ? NOCHECK CONSTRAINT all'

	if exists (SELECT * FROM INFORMATION_SCHEMA.VIEWS WHERE TABLE_NAME = N'USER_INFO')    
	begin
		drop view USER_INFO;
	end

	IF EXISTS (SELECT * FROM SYS.SYNONYMS WHERE NAME = N'SN_USER_INFO') 
	BEGIN
		DROP SYNONYM [dbo].SN_USER_INFO
	END

	create synonym [dbo].SN_USER_INFO for [CldBizSys].[dbo].USER_INFO

	go

	if exists (SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE CONSTRAINT_NAME = 'FK_PARENT_ASSET_ID')
	begin
		alter table ASSET drop constraint FK_PARENT_ASSET_ID;
	end

	if exists (SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE CONSTRAINT_NAME = 'FK_ROLE_ASSET_ASSET_ID')
	begin
		alter table ROLE_ASSET drop constraint FK_ROLE_ASSET_ASSET_ID;
	end
	
	if exists (SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE CONSTRAINT_NAME = 'FK_ROLE_ASSET_ROLE_ID')
	begin
		alter table ROLE_ASSET drop constraint FK_ROLE_ASSET_ROLE_ID;
	end

	if exists (SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE CONSTRAINT_NAME = 'FK_USER_INFO_ROLE_ROLE_ID')
	begin
		alter table USER_INFO_ROLE drop constraint FK_USER_INFO_ROLE_ROLE_ID;
	end

	if OBJECT_ID ('tr_FK_USER_INFO_ROLE_USER_INFO_ID','TR') IS NOT NULL
	begin
		drop trigger tr_FK_USER_INFO_ROLE_USER_INFO_ID;
	end

	if OBJECT_ID ('tr_DEL_USER_INFO_USER_INFO_ROLE','TR') IS NOT NULL
	begin
		drop trigger tr_DEL_USER_INFO_USER_INFO_ROLE;
	end

	go

	if exists (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'ASSET')    
	begin
		drop table ASSET;
	end
	
	if exists (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'ROLE')    
	begin
		drop table ROLE;
	end

	if exists (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'ROLE_ASSET')    
	begin
		drop table ROLE_ASSET;
	end
	
	if exists (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'USER_INFO_ROLE')    
	begin
		drop table USER_INFO_ROLE;
	end

	if exists (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'PRODUCT')    
	begin
		drop table PRODUCT;
	end

	if exists (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'UNIQUE_ID')    
	begin
		drop table UNIQUE_ID;
	end

	go
	
	create view [dbo].[USER_INFO] as
	select 
	    ID,
	    CREATED_BY,
	    CREATED_DATE,
	    MODIFIED_BY,
	    MODIFIED_DATE,
	    VERSION bigint,
	    ADDRESS_LINE1,
	    ADDRESS_LINE2,
	    CITY,
	    COUNTRY,
	    DATE_FORMAT,
	    EMAIL_ADDRESS,
	    FAX,
	    FIRST_NAME,
	    IS_LOCKED,
	    LAST_LOGIN_DATE,
	    LAST_NAME,
	    LOCALE_COUNTRY,
	    LOCALE_LANGUAGE,
	    LOCALE_VARIANT,
	    LOGIN,
	    LOGIN_ATTEMPTS,
	    PASSWORD,
	    PASSWORD_EXPIRE_DATE,
	    PASSWORD_MODIFIED_DATE,
	    PRIMARY_PHONE,
	    PROVINCE,
	    SECONDARY_PHONE,
	    STATE,
	    STATUS,
	    TIME_FORMAT,
	    TIME_ZONE,
	    ZIP
	from SN_USER_INFO
	
	go

    create table PRODUCT (
        ID bigint not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime2 not null,
        MODIFIED_BY varchar(255),
        MODIFIED_DATE datetime2,
        VERSION bigint not null,
        CODE varchar(8) not null,
        DESCRIPTION varchar(255),
        ID_RANGE_END bigint not null,
        ID_RANGE_START bigint not null,
        KEY_NAME varchar(80) not null,
        LANDING_PAGE varchar(255) not null,
        SORT_ORDER bigint not null,
        STATUS bit not null,
        CONSTRAINT [PK_PRODUCT] PRIMARY KEY CLUSTERED (ID)
    );

	create table ASSET (
        ID bigint not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime2 not null,
        MODIFIED_BY varchar(255),
        MODIFIED_DATE datetime2,
        VERSION bigint not null,
        ACTIVE bit not null,
        KEY_NAME varchar(255) not null,
        PRODUCT_ID bigint not null,
        TYPE varchar(255) not null,
        PARENT_ASSET_ID bigint,
        CONSTRAINT [PK_ASSET] PRIMARY KEY CLUSTERED (ID),
		CONSTRAINT FK_PARENT_ASSET_ID FOREIGN KEY (PARENT_ASSET_ID) REFERENCES ASSET (ID)
    );

    create table ROLE (
        ID bigint not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime2 not null,
        MODIFIED_BY varchar(255),
        MODIFIED_DATE datetime2,
        VERSION bigint not null,
        DESCRIPTION varchar(255) not null,
        NAME varchar(255) not null,
        PRODUCT_ID bigint not null,
        CONSTRAINT [PK_ROLE] PRIMARY KEY CLUSTERED (ID),
		CONSTRAINT FK_ROLE_PRODUCT_ID FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (ID) ON DELETE CASCADE
    );

    create table ROLE_ASSET (
        ASSET_ID bigint not null,
        ROLE_ID bigint not null,
        IS_CREATABLE bit,
        IS_DELETABLE bit,
        IS_READABLE bit,
        IS_UPDATABLE bit,
        CONSTRAINT [PK_ROLE_ASSET] PRIMARY KEY CLUSTERED (ROLE_ID, ASSET_ID),
		CONSTRAINT FK_ROLE_ASSET_ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ID) ON DELETE CASCADE,
		CONSTRAINT FK_ROLE_ASSET_ASSET_ID FOREIGN KEY (ASSET_ID) REFERENCES ASSET (ID) ON DELETE CASCADE
    );

    create table USER_INFO_ROLE (
        USER_INFO_ID bigint not null,
        ROLE_ID bigint not null,
        CONSTRAINT [PK_USER_INFO_ROLE] PRIMARY KEY CLUSTERED (ROLE_ID, USER_INFO_ID),
		CONSTRAINT FK_USER_INFO_ROLE_ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ID) ON DELETE CASCADE
    );

    create table UNIQUE_ID (
        ID_NAME varchar(255) not null,
        NEXT_ID bigint not null,
        CONSTRAINT [PK_UNIQUE_ID] PRIMARY KEY CLUSTERED (ID_NAME)
    );

	go

	CREATE TRIGGER tr_DEL_ASSET_PARENT_ASSET_ID ON dbo.ASSET
	AFTER DELETE
	AS
	BEGIN
		delete a from ASSET a inner join deleted d on  a.PARENT_ASSET_ID = d.id
	END

	go

	-- Trigger to enforce FK constaint between USER_INFO_ROLE and USER_INFO (view) and ROLE
	CREATE TRIGGER tr_FK_USER_INFO_ROLE_USER_INFO_ID ON dbo.USER_INFO_ROLE
	AFTER INSERT
	AS
	BEGIN
		IF NOT EXISTS (SELECT * FROM USER_INFO AS ui JOIN inserted AS i ON ui.id = i.USER_INFO_ID)
		BEGIN
			RAISERROR ('USER_INFO_ROLE FK exception, no user exists.', -1, -1);
			ROLLBACK TRANSACTION;
			RETURN 
		END
		IF NOT EXISTS (SELECT * FROM ROLE AS r JOIN inserted AS i ON r.id = i.ROLE_ID)
		BEGIN
			RAISERROR ('USER_INFO_ROLE FK exception, no role exists.', -1, -1);
			ROLLBACK TRANSACTION;
			RETURN 
		END
	END	

	go

	CREATE TRIGGER tr_DEL_USER_INFO_USER_INFO_ROLE on dbo.USER_INFO
	INSTEAD OF DELETE
	AS
	BEGIN
		delete uir from USER_INFO_ROLE uir inner join deleted d on  uir.USER_INFO_ID = d.id
		delete ui from USER_INFO ui inner join deleted d on  ui.id = d.id
	END

	go

    insert into UNIQUE_ID (ID_NAME, NEXT_ID) values ('USER_INFO', 1001)
    insert into UNIQUE_ID (ID_NAME, NEXT_ID) values ('ROLE', 1001)
    insert into UNIQUE_ID (ID_NAME, NEXT_ID) values ('ASSET', 1001)
    
	EXEC sp_msforeachtable "ALTER TABLE ? WITH CHECK CHECK CONSTRAINT all"

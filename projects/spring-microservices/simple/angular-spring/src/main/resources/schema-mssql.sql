
    drop table APP_CODE;

    drop table USER_PROFILE;

    drop sequence hibernate_sequence;
	create sequence hibernate_sequence start with 10000 increment by 10;

    create table APP_CODE (
       ID bigint not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime2 not null,
        MAINTAINED_BY varchar(255),
        MAINTAINED_DATE datetime2,
        VERSION bigint,
        CODE varchar(255),
        DEFAULT_FLAG bit,
        DESCRIPTION varchar(255),
        IS_ACTIVE bit,
        TYPE varchar(255),
        USER_DEFINED bit,
        VALUE_TEXT varchar(255),
        primary key (ID)
    );

    create table USER_PROFILE (
       ID bigint not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime2 not null,
        MAINTAINED_BY varchar(255),
        MAINTAINED_DATE datetime2,
        VERSION bigint,
        DS_KEY varchar(255),
        EMAIL varchar(255),
        FIRST_NAME varchar(255),
        LAST_NAME varchar(255),
        primary key (ID)
    );

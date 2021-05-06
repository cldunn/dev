
    alter table RESOURCE 
        drop constraint FK_PARENT_RESOURCE_ID;

    alter table ROLE_RESOURCE 
        drop constraint FKkah7v6qmaqe6damf1bweasqwd;

    alter table USER_INFO 
        drop constraint FK_USER_AUTHENTICATION_ID;

    alter table USER_INFO_ROLE 
        drop constraint FKl4w335sax9ta6x98g60mdvn00;

    alter table USER_INFO_ROLE 
        drop constraint FKpt0e49s2g0muveko61g4bk9w5;

    drop table EMPLOYEE;

    drop table RESOURCE;

    drop table ROLE;

    drop table ROLE_RESOURCE;

    drop table UNIQUE_ID;

    drop table USER_AUTHENTICATION;

    drop table USER_INFO;

    drop table USER_INFO_ROLE;

    create table EMPLOYEE (
        ID bigint not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime2 not null,
        MODIFIED_BY varchar(255),
        MODIFIED_DATE datetime2,
        VERSION bigint not null,
        FIRST_NAME varchar(255),
        HIRE_DATE date,
        LAST_NAME varchar(255),
        SALARY double precision,
        primary key (ID)
    );

    create table RESOURCE (
        ID bigint not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime2 not null,
        MODIFIED_BY varchar(255),
        MODIFIED_DATE datetime2,
        VERSION bigint not null,
        NAME varchar(255) not null,
        REF_KEY varchar(255) not null,
        PARENT_ID bigint,
        primary key (ID)
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
        primary key (ID)
    );

    create table ROLE_RESOURCE (
        RESOURCE_ID bigint not null,
        ROLE_ID bigint not null,
        IS_CREATABLE bit,
        IS_DELETABLE bit,
        IS_READABLE bit,
        IS_UPDATABLE bit,
        primary key (RESOURCE_ID, ROLE_ID)
    );

    create table UNIQUE_ID (
        ID_NAME varchar(255) not null,
        NEXT_ID bigint not null,
        primary key (ID_NAME)
    );

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
        primary key (ID)
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
        LOCALE_COUNTRY varchar(255),
        LOCALE_LANGUAGE varchar(255) not null,
        LOCALE_VARIANT varchar(255),
        PRIMARY_PHONE varchar(255),
        PROVINCE varchar(255),
        SECONDARY_PHONE varchar(255),
        STATE varchar(255),
        TIME_FORMAT varchar(255),
        TIME_ZONE varchar(255),
        ZIP varchar(255),
        USER_AUTHENTICATION_ID bigint not null,
        primary key (ID)
    );

    create table USER_INFO_ROLE (
        ROLE_ID bigint not null,
        USER_INFO_ID bigint not null,
        primary key (ROLE_ID, USER_INFO_ID)
    );

    create index EMPLOYEE_LAST_NAME on EMPLOYEE (LAST_NAME);

    alter table RESOURCE 
        add constraint UK_REF_KEY unique (REF_KEY);

    alter table USER_AUTHENTICATION 
        add constraint UK_LOGIN unique (LOGIN);

    alter table USER_INFO 
        add constraint UK_USER_AUTHENTICATION_ID unique (USER_AUTHENTICATION_ID);

    alter table USER_INFO 
        add constraint UK_EMAIL_ADDRESS unique (EMAIL_ADDRESS);

    alter table RESOURCE 
        add constraint FK_PARENT_RESOURCE_ID 
        foreign key (PARENT_ID) 
        references RESOURCE;

    alter table ROLE_RESOURCE 
        add constraint FKkah7v6qmaqe6damf1bweasqwd 
        foreign key (ROLE_ID) 
        references ROLE;

    alter table USER_INFO 
        add constraint FK_USER_AUTHENTICATION_ID 
        foreign key (USER_AUTHENTICATION_ID) 
        references USER_AUTHENTICATION;

    alter table USER_INFO_ROLE 
        add constraint FKl4w335sax9ta6x98g60mdvn00 
        foreign key (USER_INFO_ID) 
        references USER_INFO;

    alter table USER_INFO_ROLE 
        add constraint FKpt0e49s2g0muveko61g4bk9w5 
        foreign key (ROLE_ID) 
        references ROLE;

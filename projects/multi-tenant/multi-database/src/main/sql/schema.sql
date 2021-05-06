
    alter table ASSET 
        drop constraint FK_PARENT_ASSET_ID;

    alter table ROLE_ASSET 
        drop constraint FK_ASSET_ID;

    alter table ROLE_ASSET 
        drop constraint FKq8ntgq1rykga3wdht5q9ttii1;

    alter table USER_INFO_ROLE 
        drop constraint FK_ROLE_ID;

    alter table USER_INFO_ROLE 
        drop constraint FK_USER_INFO_ID;

    drop table ASSET;

    drop table PRODUCT;

    drop table ROLE;

    drop table ROLE_ASSET;

    drop table UNIQUE_ID;

    drop table USER_INFO;

    drop table USER_INFO_ROLE;

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
        primary key (ID)
    );

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
        PRODUCT_ID bigint not null,
        primary key (ID)
    );

    create table ROLE_ASSET (
        ASSET_ID bigint not null,
        ROLE_ID bigint not null,
        IS_CREATABLE bit,
        IS_DELETABLE bit,
        IS_READABLE bit,
        IS_UPDATABLE bit,
        primary key (ASSET_ID, ROLE_ID)
    );

    create table UNIQUE_ID (
        ID_NAME varchar(255) not null,
        NEXT_ID bigint not null,
        primary key (ID_NAME)
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
        primary key (ID)
    );

    create table USER_INFO_ROLE (
        USER_INFO_ID bigint not null,
        ROLE_ID bigint not null
    );

    alter table PRODUCT 
        add constraint UK_7ijckq42ih57ou5vyqghoqwm7 unique (CODE);

    alter table PRODUCT 
        add constraint UK_as0i17igsx7b67it5xmcb8o3v unique (KEY_NAME);

    alter table USER_INFO 
        add constraint UK_EMAIL_ADDRESS unique (EMAIL_ADDRESS);

    alter table USER_INFO 
        add constraint UK_LOGIN unique (LOGIN);

    alter table ASSET 
        add constraint FK_PARENT_ASSET_ID 
        foreign key (PARENT_ASSET_ID) 
        references ASSET;

    alter table ROLE_ASSET 
        add constraint FK_ASSET_ID 
        foreign key (ASSET_ID) 
        references ASSET;

    alter table ROLE_ASSET 
        add constraint FKq8ntgq1rykga3wdht5q9ttii1 
        foreign key (ROLE_ID) 
        references ROLE;

    alter table USER_INFO_ROLE 
        add constraint FK_ROLE_ID 
        foreign key (ROLE_ID) 
        references ROLE;

    alter table USER_INFO_ROLE 
        add constraint FK_USER_INFO_ID 
        foreign key (USER_INFO_ID) 
        references USER_INFO;

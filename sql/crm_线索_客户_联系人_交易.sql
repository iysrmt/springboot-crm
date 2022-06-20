drop table if exists tbl_clue;

drop table if exists tbl_clue_activity_relation;

drop table if exists tbl_clue_remark;

drop table if exists tbl_contacts;

drop table if exists tbl_contacts_activity_relation;

drop table if exists tbl_contacts_remark;

drop table if exists tbl_customer;

drop table if exists tbl_customer_remark;

drop table if exists tbl_tran;

drop table if exists tbl_tran_history;

drop table if exists tbl_tran_remark;

/*==============================================================*/
/* Table: tbl_clue                                              */
/*==============================================================*/
create table tbl_clue
(
    id                char(32) not null,
    full_name         varchar(255),
    appellation       char(32),
    owner             char(32),
    company           varchar(255),
    job               varchar(255),
    email             varchar(255),
    phone             varchar(255),
    website           varchar(255),
    m_phone           varchar(255),
    state             char(32),
    source            char(32),
    create_by         char(32),
    create_time       char(19),
    edit_by           char(32),
    edit_time         char(19),
    description       varchar(255),
    contact_summary   varchar(255),
    next_contact_time char(10),
    address           varchar(255),
    primary key (id)
);

/*==============================================================*/
/* Table: tbl_clue_activity_relation                            */
/*==============================================================*/
create table tbl_clue_activity_relation
(
    id          char(32) not null,
    clue_id     char(32),
    activity_id char(32),
    primary key (id)
);

/*==============================================================*/
/* Table: tbl_clue_remark                                       */
/*==============================================================*/
create table tbl_clue_remark
(
    id           char(32) not null,
    note_content varchar(255),
    create_by    char(32),
    create_time  char(19),
    edit_by      char(32),
    edit_time    char(19),
    edit_flag    char(1),
    clue_id      char(32),
    primary key (id)
);

/*==============================================================*/
/* Table: tbl_contacts                                          */
/*==============================================================*/
create table tbl_contacts
(
    id                char(32) not null,
    owner             char(32),
    source            char(32),
    customer_id       char(32),
    full_name         varchar(255),
    appellation       char(32),
    email             varchar(255),
    m_phone           varchar(255),
    job               varchar(255),
    create_by         char(32),
    create_time       char(19),
    edit_by           char(32),
    edit_time         char(19),
    description       varchar(255),
    contact_summary   varchar(255),
    next_contact_time char(10),
    address           varchar(255),
    primary key (id)
);

/*==============================================================*/
/* Table: tbl_contacts_activity_relation                        */
/*==============================================================*/
create table tbl_contacts_activity_relation
(
    id          char(32) not null,
    contacts_id char(32),
    activity_id char(32),
    primary key (id)
);

/*==============================================================*/
/* Table: tbl_contacts_remark                                   */
/*==============================================================*/
create table tbl_contacts_remark
(
    id           char(32) not null,
    note_content varchar(255),
    create_by    char(32),
    create_time  char(19),
    edit_by      char(32),
    edit_time    char(19),
    edit_flag    char(1),
    contacts_id  char(32),
    primary key (id)
);

/*==============================================================*/
/* Table: tbl_customer                                          */
/*==============================================================*/
create table tbl_customer
(
    id                char(32) not null,
    owner             char(32),
    name              varchar(255),
    website           varchar(255),
    phone             varchar(255),
    create_by         char(32),
    create_time       char(19),
    edit_by           char(32),
    edit_time         char(19),
    contact_summary   varchar(255),
    next_contact_time char(10),
    description       varchar(255),
    address           varchar(255),
    primary key (id)
);

/*==============================================================*/
/* Table: tbl_customer_remark                                   */
/*==============================================================*/
create table tbl_customer_remark
(
    id           char(32) not null,
    note_content varchar(255),
    create_by    char(32),
    create_time  char(19),
    edit_by      char(32),
    edit_time    char(19),
    edit_flag    char(1),
    customer_id  char(32),
    primary key (id)
);

/*==============================================================*/
/* Table: tbl_tran                                              */
/*==============================================================*/
create table tbl_tran
(
    id                char(32) not null,
    owner             char(32),
    money             varchar(255),
    name              varchar(255),
    expected_date     char(10),
    customer_id       char(32),
    stage             char(32),
    type              char(32),
    source            char(32),
    activity_id       char(32),
    contacts_id       char(32),
    create_by         char(32),
    create_time       char(19),
    edit_by           char(32),
    edit_time         char(19),
    description       varchar(255),
    contact_summary   varchar(255),
    next_contact_time char(10),
    primary key (id)
);

/*==============================================================*/
/* Table: tbl_tran_history                                      */
/*==============================================================*/
create table tbl_tran_history
(
    id            char(32) not null,
    stage         char(32),
    money         varchar(255),
    expected_date char(10),
    create_time   char(19),
    create_by     char(32),
    tran_id       char(32),
    primary key (id)
);

/*==============================================================*/
/* Table: tbl_tran_remark                                       */
/*==============================================================*/
create table tbl_tran_remark
(
    id           char(32) not null,
    note_content varchar(255),
    create_by    char(32),
    create_time  char(19),
    edit_by      char(32),
    edit_time    char(19),
    edit_flag    char(1),
    tran_id      char(32),
    primary key (id)
);

drop database if exists shop;

create database shop character set utf8;

use shop;

create table user
(
    id          bigint,
    username    varchar(10) not null unique,
    password    char(32)    not null,
    nickname    varchar(10) not null,
    balance     decimal(16, 2) default 0.00,
    type        tinyint(1)     default 1,
    create_time datetime    not null,
    update_time datetime    null,
    deleted     tinyint(1)     default 0,
    primary key (id)
);

insert into user(id, username, password, nickname, balance, type, create_time)
values (100001, 'zero', 'admin', 'zero', 0.00, 2, now());
insert into user(id, username, password, nickname, balance, type, create_time)
values (5000001, 'test1', 'test', '测试员1', 100.00, 1, now());
insert into user(id, username, password, nickname, balance, type, create_time)
values (5000002, 'test2', 'test', '测试员2', 200.00, 1, now());
insert into user(id, username, password, nickname, balance, type, create_time, deleted)
values (5000003, 'test3', 'test', '测试员3', 0.00, 1, now(), 1);


create table address
(
    id          bigint,
    province    varchar(10) not null,
    city        varchar(10) not null,
    area        varchar(10) not null,
    detail      varchar(10) not null,
    name        varchar(5)  not null,
    phone       char(11)    not null,
    postal_code varchar(10) not null,
    user_id     bigint      not null,
    create_time datetime    not null,
    update_time datetime    null,
    deleted     tinyint(1) default 0,
    primary key (id)
);

insert into address(id, province, city, area, detail, name, phone, postal_code, user_id, create_time)
values (1001, '测试省', '测试市', '测试区', '详细测试地址', '测试人员1', '1001', '1001', 5000001, now());

create table recharge
(
    id          bigint,
    user_id     bigint         not null,
    amount      int            not null,
    old_balance decimal(16, 2) not null,
    new_balance decimal(16, 2) not null,
    create_time datetime       not null,
    primary key (id)
);

create table image
(
    id          bigint,
    url         varchar(128) not null,
    create_time datetime     not null,
    primary key (id)
);

INSERT INTO `image`
VALUES (33, '/api/pic/22c88234-3005-44f0-ac42-e6e09d18063c.jpg', now());
INSERT INTO `image`
VALUES (34, '/api/pic/4398986a-4c38-4ba1-b7f8-f1493bae9198.jpg', now());
INSERT INTO `image`
VALUES (35, '/api/pic/590fcf93-ff38-49f3-87d5-f5396c8d9c2a.png', now());
INSERT INTO `image`
VALUES (36, '/api/pic/7149f2f7-33f3-4419-897f-2c6135b0d545.jpg', now());
INSERT INTO `image`
VALUES (40, '/api/pic/0ea6cc13-4f30-48f7-9d4d-e86c45a83a12.jpg', now());
INSERT INTO `image`
VALUES (41, '/api/pic/05411e7d-aacf-4fa9-be86-3d22a88ff2e2.jpg', now());
INSERT INTO `image`
VALUES (42, '/api/pic/2e5e9cff-0e28-4d4e-8799-399b801d7b74.jpg', now());
INSERT INTO `image`
VALUES (43, '/api/pic/62c25e1a-f5b8-4b46-8e66-bd03d6191645.jpg', now());
INSERT INTO `image`
VALUES (44, '/api/pic/84a7faa2-5f2a-4afe-b7f5-6003b1eb8628.png', now());
INSERT INTO `image`
VALUES (45, '/api/pic/194336f1-3258-4d01-955e-f7d18f12759b.jpg', now());
INSERT INTO `image`
VALUES (46, '/api/pic/83d6e7bc-1194-49ef-8074-5981e944ed82.jpg', now());
INSERT INTO `image`
VALUES (47, '/api/pic/d6a5c710-26e5-4c89-8424-345d0148a34a.jpg', now());
INSERT INTO `image`
VALUES (48, '/api/pic/ef648a1a-6450-48a3-a1c7-14f884b38372.jpg', now());
INSERT INTO `image`
VALUES (49, '/api/pic/3e78b02e-bf68-458c-983f-0898d582480a.jpg', now());
INSERT INTO `image`
VALUES (50, '/api/pic/4b851042-3368-443e-a66c-1b4ebedfef95.jpg', now());
INSERT INTO `image`
VALUES (51, '/api/pic/49b9ce9a-7d23-449a-8eb3-a7171b944950.jpg', now());
INSERT INTO `image`
VALUES (52, '/api/pic/246bc591-0e52-4656-9882-5f12ba46d2ed.jpg', now());
INSERT INTO `image`
VALUES (53, '/api/pic/2a11ea79-6d96-4d98-95b3-b9a0bc8496d5.jpg', now());
INSERT INTO `image`
VALUES (54, '/api/pic/4a0c5630-6fd8-43cc-8d61-b17f57b616c3.jpg', now());
INSERT INTO `image`
VALUES (55, '/api/pic/2030b1e4-9b52-4bea-bdcc-f2a2f1e219d2.jpg', now());
INSERT INTO `image`
VALUES (56, '/api/pic/51b4461d-b072-4735-b31b-1e631ee75703.jpg', now());
INSERT INTO `image`
VALUES (57, '/api/pic/13a8c619-301f-4069-9f0c-69302f972576.jpg', now());
INSERT INTO `image`
VALUES (58, '/api/pic/c9882dc4-7407-4eaf-8f82-c403d1690a17.jpg', now());
INSERT INTO `image`
VALUES (59, '/api/pic/77341ed9-2172-4bcb-ac93-73be8a8a064b.jpg', now());
INSERT INTO `image`
VALUES (60, '/api/pic/cc707de7-83c1-4d69-b809-ebfe26c84c3c.jpg', now());
INSERT INTO `image`
VALUES (61, '/api/pic/6d88a685-e80d-4647-90a2-19b73d680fca.jpg', now());
INSERT INTO `image`
VALUES (62, '/api/pic/536c72ec-aea1-4647-8eff-73c5a73fdeaa.jpg', now());
INSERT INTO `image`
VALUES (63, '/api/pic/8f3cae67-b926-48b7-a041-b81bd15a1211.jpg', now());
INSERT INTO `image`
VALUES (64, '/api/pic/95466f8d-dfb5-4ae6-8446-bd1cce64b7da.jpg', now());
INSERT INTO `image`
VALUES (65, '/api/pic/5d0b6e06-cf48-45a8-a7cb-6662a0da2663.jpg', now());


create table banner
(
    id          int auto_increment,
    title       varchar(20) not null,
    image_id    int         not null,
    create_time datetime    not null,
    primary key (id)
);

create table category
(
    id        int auto_increment,
    name      varchar(20) not null,
    parent_id int         null,
    hot       tinyint(1) default 0,
    primary key (id)
);

INSERT INTO category
VALUES (1, 'Apple', NULL, 0);
INSERT INTO category
VALUES (2, 'Mac', 1, 0);
INSERT INTO category
VALUES (3, 'MacBook Air', 2, 0);
INSERT INTO `category`
VALUES (4, 'MacBook Pro', 2, 0);
INSERT INTO `category`
VALUES (5, 'iMac', 2, 0);
INSERT INTO `category`
VALUES (6, 'iPad', 1, 0);
INSERT INTO `category`
VALUES (7, 'iPhone', 1, 0);
INSERT INTO `category`
VALUES (8, 'Samsung', NULL, 0);
INSERT INTO `category`
VALUES (9, 'Mobile', 8, 0);
INSERT INTO `category`
VALUES (10, 'Computing', 8, 0);
INSERT INTO `category`
VALUES (13, 'Billie', 1, 0);
INSERT INTO `category`
VALUES (16, 'Sadye', 1, 0);
INSERT INTO `category`
VALUES (17, 'Jonathon', 1, 0);
INSERT INTO `category`
VALUES (18, 'Violette', 1, 0);
INSERT INTO `category`
VALUES (19, 'Anibal', 1, 0);
INSERT INTO `category`
VALUES (21, 'Mayra', 1, 0);
INSERT INTO `category`
VALUES (22, 'Larry', 1, 0);


create table product
(
    id           bigint,
    title        varchar(20)    not null,
    code         char(21)       not null,
    introduction text           not null,
    main_picture varchar(128)   null,
    sub_pictures json           null,
    category_id  int            not null,
    to_price     decimal(10, 2) not null,
    cost_price   decimal(10, 2) not null,
    unit         varchar(5)     not null,
    stock        int        default 0,
    hot          tinyint(1) default 0,
    create_time  datetime       not null,
    update_time  datetime       null,
    deleted      tinyint(1) default 0,
    primary key (id)
);

create table favorite
(
    id          bigint,
    user_id     bigint   not null,
    product_id  bigint   not null,
    create_time datetime not null,
    unique key (user_id, product_id),
    primary key (id)
);

create table orders
(
    id          bigint,
    code        char(21)       null,
    quantity    int            not null,
    price       decimal(10, 2) not null,
    state       int            not null,
    product_id  bigint         not null,
    address_id  bigint         not null,
    user_id     bigint         not null,
    create_time datetime       not null,
    update_time datetime       null,
    deleted     tinyint(1) default 0,
    primary key (id)
);

create table comment
(
    id          bigint,
    content     varchar(255) not null,
    star        tinyint(1)   not null,
    user_id     bigint       not null,
    product_id  bigint       not null,
    create_time datetime     not null,
    deleted     tinyint(1) default 0,
    primary key (id)
);










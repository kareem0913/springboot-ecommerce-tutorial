CREATE TABLE categories
(
    id                 BIGINT       NOT NULL,
    created_at         datetime NULL,
    updated_at         datetime NULL,
    created_by         VARCHAR(255) NULL,
    last_modified_by   VARCHAR(255) NULL,
    name               VARCHAR(255) NOT NULL,
    `description`      VARCHAR(255) NULL,
    sub_category_count INT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    price DOUBLE NULL,
    stack         INT NOT NULL,
    category_id   BIGINT NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE subcategories
(
    id               BIGINT       NOT NULL,
    created_at       datetime NULL,
    updated_at       datetime NULL,
    created_by       VARCHAR(255) NULL,
    last_modified_by VARCHAR(255) NULL,
    name             VARCHAR(255) NOT NULL,
    `description`    VARCHAR(255) NULL,
    category_id      BIGINT       NOT NULL,
    CONSTRAINT pk_subcategories PRIMARY KEY (id)
);

ALTER TABLE categories
    ADD CONSTRAINT uc_categories_name UNIQUE (name);

ALTER TABLE subcategories
    ADD CONSTRAINT uc_subcategories_name UNIQUE (name);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE subcategories
    ADD CONSTRAINT FK_SUBCATEGORIES_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);
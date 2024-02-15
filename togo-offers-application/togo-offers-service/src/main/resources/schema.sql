-- Create a read-only (select) user to view database state in running application.
DROP USER IF EXISTS guest;
CREATE USER guest PASSWORD 'guest';

-- The default schema in H2 is 'PUBLIC'.
GRANT SELECT ON SCHEMA PUBLIC TO guest;


DROP TABLE IF EXISTS OFFER CASCADE;
CREATE TABLE OFFER
(
    OFFER_ID          IDENTITY     NOT NULL COMMENT 'A surrogate primary key id, instead of the name.' PRIMARY KEY,
    OFFER_NAME        VARCHAR(20)  NOT NULL COMMENT 'A unique name of the offer' UNIQUE CHECK REGEXP_LIKE(OFFER_NAME, '^[0-9a-zA-Z -]{2,20}$'),
    OFFER_CATEGORY    VARCHAR(20)  NOT NULL COMMENT 'The category of this offer' CHECK REGEXP_LIKE(OFFER_CATEGORY, '^(CITY|BEACH|NATURE|SNOW|MOUNTAIN)$'),
    OFFER_DESCRIPTION VARCHAR(200) NOT NULL COMMENT 'A description describing the offer.' CHECK REGEXP_LIKE(OFFER_DESCRIPTION, '^[0-9a-zA-Z '',.-]{2,200}$')
);
COMMENT ON TABLE OFFER IS 'Loyalty Offer that a loyalty member may be interested in';
CREATE INDEX OFFER_INDEX ON OFFER (OFFER_NAME);
CREATE INDEX OFFER_CATEGORY_INDEX ON OFFER (OFFER_CATEGORY);


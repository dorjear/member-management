INSERT INTO PROGRAM
(PROGRAM_ID,
 PROGRAM_NAME,
 PROGRAM_DESCRIPTION)
VALUES ('FF', 'Frequent Flyer', 'Our frequent flyers program rewards you with every flight you take and for purchases with our partners'),
       ('PL', 'Premiere Lounge', 'Our Premier Lounge program provides you access to our airport lounges'),
       ('ER', 'Extra Rewards', 'With Extra Rewards you will earn more points for both flights and purchases');

INSERT INTO MEMBER
(MEMBER_ID,
 ACCOUNT_STATUS,
 GIVEN_NAME,
 SURNAME,
 ADDRESS,
 ENROLLED_SINCE,
 PREFERENCE)
VALUES ('110020', 'ACTIVE', 'Fred', 'Flintstone', '{"line1":"Unit 1","line2":"10 Bourke road","city":"Mascot","state":"NSW","postcode":"2020","country":"Australia"}' FORMAT JSON, '2017-08-12', 'CITY'),
       ('110021', 'ACTIVE', 'Wilma', 'Flintstone', '{"line1":"Unit 1","line2":"10 Bourke road","city":"Mascot","state":"NSW","postcode":"2020","country":"Australia"}' FORMAT JSON, '2017-09-15', 'BEACH'),
       ('110022', 'SUSPENDED', 'Barney', 'Rubble', '{"line1":"Unit 1","line2":"10 Bourke road","city":"Mascot","state":"NSW","postcode":"2020","country":"Australia"}' FORMAT JSON, '2017-09-16', 'NATURE'),
       ('110023', 'PENDING', 'Betty', 'Rubble', '{"line1":"Unit 1","line2":"10 Bourke road","city":"Mascot","state":"NSW","postcode":"2020","country":"Australia"}' FORMAT JSON, '2018-11-20', 'MOUNTAIN'),
       ('110024', 'PENDING', 'Bambam', 'Flintstone', '{"line1":"Unit 1","line2":"10 Bourke road","city":"Mascot","state":"NSW","postcode":"2020","country":"Australia"}' FORMAT JSON, '2018-12-31', 'SNOW');

INSERT INTO MEMBER_PROGRAM
(MEMBER_ID,
 PROGRAM_ID)
VALUES ('110020', 'FF'),
       ('110020', 'PL'),
       ('110020', 'ER'),
       ('110021', 'FF'),
       ('110021', 'PL'),
       ('110022', 'FF'),
       ('110023', 'FF');


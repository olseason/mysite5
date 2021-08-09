CREATE TABLE gallery (
    no        NUMBER,
    PRIMARY KEY ( no ),
    user_no   NUMBER,
    content   VARCHAR2(1000),
    filepath  VARCHAR2(500),
    orgname   VARCHAR2(200),
    savename  VARCHAR2(500),
    filesize  NUMBER,
    CONSTRAINT gallery_fk FOREIGN KEY ( user_no )
        REFERENCES users ( no )
);

CREATE SEQUENCE seq_gallery_no INCREMENT BY 1 START WITH 1 NOCACHE;

INSERT INTO gallery VALUES (
    seq_gallery_no.NEXTVAL,
    10,
    'content',
    'filepath',
    'orgname',
    'savename',
    1234
);

SELECT
    no,
    user_no,
    content,
    filepath,
    orgname,
    savename,
    filesize
FROM
    gallery
WHERE
    no = 3;

DELETE FROM gallery
WHERE
    no = 3;
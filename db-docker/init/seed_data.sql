DROP TABLE IF EXISTS `reserve`;

CREATE TABLE reserve(
    reservationID  INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (reservationID),
    roomNum INT NOT NULL,
    startDay INT,
    endDay int
);

INSERT INTO `reserve` (roomNum, startDay, endDay)
VALUES (1,1,2);
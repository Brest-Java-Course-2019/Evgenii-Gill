DROP TABLE IF EXISTS author;
CREATE TABLE author (
  authorID INT NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(30) NOT NULL,
  lastName VARCHAR(30) NOT NULL,
  PRIMARY KEY (authorID));

DROP TABLE IF EXISTS book;
CREATE TABLE book (
  bookId INT NOT NULL AUTO_INCREMENT,
  bookTitle VARCHAR(15) NOT NULL,
  releaseDate DATE NOT NULL,
  bookAuthorId int,
  PRIMARY KEY (bookId),
  FOREIGN KEY (bookAuthorId) REFERENCES author (authorID));

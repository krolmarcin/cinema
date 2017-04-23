INSERT INTO CINEMA (ID, CITY, NAME)
VALUES ('1', 'Lublin', 'Felicity');

INSERT INTO CINEMA (ID, CITY, NAME)
VALUES ('2', 'Lublin', 'Plaza');

INSERT INTO CINEMA (ID, CITY, NAME)
VALUES ('3', 'Lublin', 'Olimp');

INSERT INTO MOVIE (ID, DESCRIPTION, LENGTH, MIN_AGE, TITLE)
VALUES ('1', 'description one', 120, 16, 'Pulp Fiction');

INSERT INTO MOVIE (ID, DESCRIPTION, LENGTH, MIN_AGE, TITLE)
VALUES ('2', 'description two', 150, 12, 'Pulp Fiction 2');

INSERT INTO MOVIE_ACTORS (MOVIE_ID, ACTORS)
VALUES ('1', 'Uma Thurman');

INSERT INTO MOVIE_ACTORS (MOVIE_ID, ACTORS)
VALUES ('1', 'Samuel L. Jackson');

INSERT INTO MOVIE_ACTORS (MOVIE_ID, ACTORS)
VALUES ('1', 'John Travolta');

INSERT INTO MOVIE_ACTORS (MOVIE_ID, ACTORS)
VALUES ('2', 'Uma Thurman');

INSERT INTO MOVIE_ACTORS (MOVIE_ID, ACTORS)
VALUES ('2', 'Samuel L. Jackson');

INSERT INTO MOVIE_ACTORS (MOVIE_ID, ACTORS)
VALUES ('2', 'John Travolta');

INSERT INTO MOVIE_GENRES (MOVIE_ID, GENRES)
VALUES ('1', 'Drama');

INSERT INTO MOVIE_GENRES (MOVIE_ID, GENRES)
VALUES ('1', 'Crime');

INSERT INTO MOVIE_GENRES (MOVIE_ID, GENRES)
VALUES ('2', 'Drama');

INSERT INTO MOVIE_GENRES (MOVIE_ID, GENRES)
VALUES ('2', 'Crime');

INSERT INTO SHOWING (ID, BEGINS_AT, CINEMA_ID, MOVIE_ID)
VALUES ('1', TIMESTAMP '2017-04-20 07:15:00.000000', 1, 1);

INSERT INTO SHOWING (ID, BEGINS_AT, CINEMA_ID, MOVIE_ID)
VALUES ('2', TIMESTAMP '2017-04-20 10:15:00.000000', 1, 1);

INSERT INTO SHOWING (ID, BEGINS_AT, CINEMA_ID, MOVIE_ID)
VALUES ('3', TIMESTAMP '2017-04-20 11:15:00.000000', 2, 1);

INSERT INTO SHOWING (ID, BEGINS_AT, CINEMA_ID, MOVIE_ID)
VALUES ('4', TIMESTAMP '2017-04-20 07:15:00.000000', 1, 2);

drop database Instituto;
create database Instituto;
use Instituto;

CREATE TABLE ALUMNOS (
                         MAT  VARCHAR(10) NOT NULL  PRIMARY KEY,
                         APEL_NOM  VARCHAR(30) DEFAULT NULL,
                         POBLACION  VARCHAR(15) DEFAULT NULL,
                         TELEFONO  VARCHAR(10) DEFAULT NULL
) ENGINE=InnoDB;

INSERT INTO ALUMNOS VALUES ('25889878C', 'Martinez  Lopez , Manuel','SEVILLA','987755465');
INSERT INTO ALUMNOS VALUES ('45665448K', 'Jimenez Choren, M. Isabel','CORUNiA','686987785');
INSERT INTO ALUMNOS VALUES ('45665884R', 'Ortiz Sanchez, M. Jose','VIGO','623154687');
INSERT INTO ALUMNOS VALUES ('45687998X', 'Guitierrez Gomez, Elias','SANTIAGO','614658779');
INSERT INTO ALUMNOS VALUES ('53259877Q', 'Diaz Fernandez, Pablo','MADRID','684455664');
INSERT INTO ALUMNOS VALUES ('67897546S', 'Rodriguez Alonso, Juan','BARCELONA','651154465');
INSERT INTO ALUMNOS VALUES ('68798545B', 'Fernandez Maria, Justo','MADRID','987756654');

CREATE TABLE ASIGNATURAS (
                             COD  int(2) NOT NULL PRIMARY KEY,
                             NOMBRE  VARCHAR(25) DEFAULT NULL
) ENGINE=InnoDB;

INSERT INTO ASIGNATURAS VALUES
                            (1, 'FOL'),
                            (2, 'Sistemas Operativos'),
                            (3, 'Lenguaje de Marcas'),
                            (4, 'Programacion'),
                            (5, 'Entornos de Desarrollo'),
                            (6, 'Bases de datos');

CREATE TABLE NOTAS (
                       Mat  VARCHAR(10) NOT NULL,
                       COD  int(2) NOT NULL DEFAULT '0',
                       NOTA1 INT(2) DEFAULT NULL,
                       NOTA2 INT(2) DEFAULT NULL,
                       NOTA3 INT(2) DEFAULT NULL,
                       PRIMARY KEY (Mat,Cod),
                       FOREIGN KEY (Mat)  REFERENCES ALUMNOS (Mat),
                       FOREIGN KEY (COD) REFERENCES  ASIGNATURAS (COD)
) ENGINE=InnoDB;

INSERT INTO NOTAS  VALUES
                       ('25889878C', 2, 6,4,7),
                       ('25889878C', 5, 5,9,3),
                       ('45665884R', 1, 8,8,7),
                       ('45665884R', 2, 8,6,6),
                       ('45665884R', 3, 3,4,2),
                       ('45687998X', 1, 6,9,6),
                       ('45687998X', 6, 4,5,3),
                       ('53259877Q', 4, 6,6,6),
                       ('53259877Q', 5, 5,7,6);


CREATE TABLE topicos(

    id BIGINT AUTO_INCREMENT,
    titulo varchar(100) not null unique,
    mensaje varchar(500) not null unique,
    fecha_de_creacion DATETIME,
    status varchar(100) not null,
    autor varchar(100) not null,
    curso varchar(100) not null,

    PRIMARY KEY (id)

);

CREATE TABLE usuarios(

    id BIGINT AUTO_INCREMENT,
    login varchar(100) not null,
    clave varchar(300) not null,

    PRIMARY KEY(id)

);
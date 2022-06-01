create database Recauderia;
use  Recauderia;
create table inventario(
ID BIGINT UNSIGNED PRIMARY KEY,
Estatus boolean default true,
Nombre VARCHAR(150),
Precio decimal(6,2),
FechaRegistro datetime,
FechaModificacion datetime
)

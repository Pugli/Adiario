create database ProyectoADiario;

use ProyectoADiario;

create table product(
    id_product int auto_increment,
    product_name varchar(100) not null,
    quantity int not null,
    quantity_sell int,
    product_value float not null,
    category varchar(100) not null,
    product_date date not null,
    primary key(id_product)
);

create table sell(
    id_sell int auto_increment,
    fk_product int,
    sell_date date not null,
    sell_value int not null,
    primary key(id_sell),
    foreign key(fk_product) references product(id_product)
);

alter table quantity_sell 



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

select p.product_name,p.category,p.product_date,s.id_sell,s.fk_product,s.sell_date,s.sell_value,s.quantity_sell from product as p inner join sell as s ON p.id_product=s.fk_product"+
            		"where s.sell_date BETWEEN "2019-10-24" AND "2019-10-24";



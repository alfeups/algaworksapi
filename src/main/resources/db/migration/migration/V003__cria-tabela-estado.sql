create table estado(
       id bigint not null auto_increment,
       nome varchar(80) not null,

       primary key(id)
) engine=InnoDB default charset=utf8;

insert into estado(nome) select distinct nome_estado from cidade;

alter table cidade ADD COLUMN estado_id bigint not null;

update cidade c
    inner join estado e on e.nome = c.nome_estado
    set c.estado_id = e.id;

alter table cidade add constraint fk_cidade_estado
    foreign key (estado_id) references estado(id);

alter table cidade drop column nome_estado;

alter table cidade change nome_cidade nome varchar(80) not null;
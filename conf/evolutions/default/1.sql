# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbl_clientes (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  email                         varchar(255),
  telefone                      varchar(255),
  senha                         varchar(255),
  constraint pk_tbl_clientes primary key (id)
);


# --- !Downs

drop table if exists tbl_clientes;


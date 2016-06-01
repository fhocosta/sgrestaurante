# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbl_cargo (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  constraint pk_tbl_cargo primary key (id)
);

create table tbl_clientes (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  email                         varchar(255),
  telefone                      varchar(255),
  senha                         varchar(255),
  cpf                           varchar(255),
  endereco                      varchar(255),
  constraint pk_tbl_clientes primary key (id)
);

create table tbl_fornecedor (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  cnpj                          varchar(255),
  endereco                      varchar(255),
  telefone                      varchar(255),
  constraint pk_tbl_fornecedor primary key (id)
);

create table tbl_funcionario (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  cpf                           varchar(255),
  endereco                      varchar(255),
  cargo_id                      bigint,
  email                         varchar(255),
  constraint uq_tbl_funcionario_cargo_id unique (cargo_id),
  constraint pk_tbl_funcionario primary key (id)
);

alter table tbl_funcionario add constraint fk_tbl_funcionario_cargo_id foreign key (cargo_id) references tbl_cargo (id) on delete restrict on update restrict;


# --- !Downs

alter table tbl_funcionario drop foreign key fk_tbl_funcionario_cargo_id;

drop table if exists tbl_cargo;

drop table if exists tbl_clientes;

drop table if exists tbl_fornecedor;

drop table if exists tbl_funcionario;


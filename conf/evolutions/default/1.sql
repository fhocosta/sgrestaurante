# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbl_cardapio (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  descricao                     varchar(255),
  disponivel                    tinyint(1) default 0,
  data                          datetime(6),
  constraint pk_tbl_cardapio primary key (id)
);

create table tbl_cardapio_tbl_produto (
  tbl_cardapio_id               bigint not null,
  tbl_produto_id                bigint not null,
  constraint pk_tbl_cardapio_tbl_produto primary key (tbl_cardapio_id,tbl_produto_id)
);

create table tbl_cargo (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  constraint pk_tbl_cargo primary key (id)
);

create table tbl_comanda (
  id                            bigint auto_increment not null,
  data_abertura                 datetime(6),
  data_fechamento               datetime(6),
  total                         float,
  aberta                        tinyint(1) default 0,
  atendente_id                  bigint,
  cliente_id                    bigint,
  mesa_id                       bigint,
  cortesia_id                   bigint,
  constraint pk_tbl_comanda primary key (id)
);

create table tbl_cortesia (
  id                            bigint auto_increment not null,
  quantidade                    integer,
  disponibilidade               datetime(6),
  produto_id                    bigint,
  constraint pk_tbl_cortesia primary key (id)
);

create table tbl_fornecedor (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  cnpj                          varchar(255),
  endereco                      varchar(255),
  telefone                      varchar(255),
  constraint pk_tbl_fornecedor primary key (id)
);

create table tbl_mesa (
  id                            bigint auto_increment not null,
  numero                        integer,
  lugares                       integer,
  reserva_id                    bigint,
  constraint pk_tbl_mesa primary key (id)
);

create table tbl_pedido (
  id                            bigint auto_increment not null,
  status_id                     bigint,
  comanda_id                    bigint,
  constraint pk_tbl_pedido primary key (id)
);

create table tbl_pedido_tbl_produto (
  tbl_pedido_id                 bigint not null,
  tbl_produto_id                bigint not null,
  constraint pk_tbl_pedido_tbl_produto primary key (tbl_pedido_id,tbl_produto_id)
);

create table tbl_produto (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  descricao                     varchar(255),
  disponivel                    tinyint(1) default 0,
  valor                         float,
  constraint pk_tbl_produto primary key (id)
);

create table tbl_produto_tbl_cardapio (
  tbl_produto_id                bigint not null,
  tbl_cardapio_id               bigint not null,
  constraint pk_tbl_produto_tbl_cardapio primary key (tbl_produto_id,tbl_cardapio_id)
);

create table tbl_produto_tbl_pedido (
  tbl_produto_id                bigint not null,
  tbl_pedido_id                 bigint not null,
  constraint pk_tbl_produto_tbl_pedido primary key (tbl_produto_id,tbl_pedido_id)
);

create table tbl_reserva (
  id                            bigint auto_increment not null,
  cliente_id                    bigint,
  atendente_id                  bigint,
  data                          datetime(6),
  observacao                    varchar(255),
  quantidade_convidados         integer,
  constraint uq_tbl_reserva_cliente_id unique (cliente_id),
  constraint pk_tbl_reserva primary key (id)
);

create table tbl_status (
  id                            bigint auto_increment not null,
  descricao                     varchar(255),
  constraint pk_tbl_status primary key (id)
);

create table tbl_sugestao (
  id                            bigint auto_increment not null,
  titulo                        varchar(255),
  descricao                     varchar(255),
  usuario_id                    bigint,
  constraint pk_tbl_sugestao primary key (id)
);

create table tbl_usuario (
  dtype                         varchar(10) not null,
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  email                         varchar(255),
  username                      varchar(255),
  senha                         varchar(255),
  cpf                           varchar(255),
  endereco                      varchar(255),
  telefone                      varchar(255),
  cargo_id                      bigint,
  receber_marketing             tinyint(1) default 0,
  constraint pk_tbl_usuario primary key (id)
);

alter table tbl_cardapio_tbl_produto add constraint fk_tbl_cardapio_tbl_produto_tbl_cardapio foreign key (tbl_cardapio_id) references tbl_cardapio (id) on delete restrict on update restrict;
create index ix_tbl_cardapio_tbl_produto_tbl_cardapio on tbl_cardapio_tbl_produto (tbl_cardapio_id);

alter table tbl_cardapio_tbl_produto add constraint fk_tbl_cardapio_tbl_produto_tbl_produto foreign key (tbl_produto_id) references tbl_produto (id) on delete restrict on update restrict;
create index ix_tbl_cardapio_tbl_produto_tbl_produto on tbl_cardapio_tbl_produto (tbl_produto_id);

alter table tbl_comanda add constraint fk_tbl_comanda_atendente_id foreign key (atendente_id) references tbl_usuario (id) on delete restrict on update restrict;
create index ix_tbl_comanda_atendente_id on tbl_comanda (atendente_id);

alter table tbl_comanda add constraint fk_tbl_comanda_cliente_id foreign key (cliente_id) references tbl_usuario (id) on delete restrict on update restrict;
create index ix_tbl_comanda_cliente_id on tbl_comanda (cliente_id);

alter table tbl_comanda add constraint fk_tbl_comanda_mesa_id foreign key (mesa_id) references tbl_mesa (id) on delete restrict on update restrict;
create index ix_tbl_comanda_mesa_id on tbl_comanda (mesa_id);

alter table tbl_comanda add constraint fk_tbl_comanda_cortesia_id foreign key (cortesia_id) references tbl_cortesia (id) on delete restrict on update restrict;
create index ix_tbl_comanda_cortesia_id on tbl_comanda (cortesia_id);

alter table tbl_cortesia add constraint fk_tbl_cortesia_produto_id foreign key (produto_id) references tbl_produto (id) on delete restrict on update restrict;
create index ix_tbl_cortesia_produto_id on tbl_cortesia (produto_id);

alter table tbl_mesa add constraint fk_tbl_mesa_reserva_id foreign key (reserva_id) references tbl_reserva (id) on delete restrict on update restrict;
create index ix_tbl_mesa_reserva_id on tbl_mesa (reserva_id);

alter table tbl_pedido add constraint fk_tbl_pedido_status_id foreign key (status_id) references tbl_status (id) on delete restrict on update restrict;
create index ix_tbl_pedido_status_id on tbl_pedido (status_id);

alter table tbl_pedido add constraint fk_tbl_pedido_comanda_id foreign key (comanda_id) references tbl_comanda (id) on delete restrict on update restrict;
create index ix_tbl_pedido_comanda_id on tbl_pedido (comanda_id);

alter table tbl_pedido_tbl_produto add constraint fk_tbl_pedido_tbl_produto_tbl_pedido foreign key (tbl_pedido_id) references tbl_pedido (id) on delete restrict on update restrict;
create index ix_tbl_pedido_tbl_produto_tbl_pedido on tbl_pedido_tbl_produto (tbl_pedido_id);

alter table tbl_pedido_tbl_produto add constraint fk_tbl_pedido_tbl_produto_tbl_produto foreign key (tbl_produto_id) references tbl_produto (id) on delete restrict on update restrict;
create index ix_tbl_pedido_tbl_produto_tbl_produto on tbl_pedido_tbl_produto (tbl_produto_id);

alter table tbl_produto_tbl_cardapio add constraint fk_tbl_produto_tbl_cardapio_tbl_produto foreign key (tbl_produto_id) references tbl_produto (id) on delete restrict on update restrict;
create index ix_tbl_produto_tbl_cardapio_tbl_produto on tbl_produto_tbl_cardapio (tbl_produto_id);

alter table tbl_produto_tbl_cardapio add constraint fk_tbl_produto_tbl_cardapio_tbl_cardapio foreign key (tbl_cardapio_id) references tbl_cardapio (id) on delete restrict on update restrict;
create index ix_tbl_produto_tbl_cardapio_tbl_cardapio on tbl_produto_tbl_cardapio (tbl_cardapio_id);

alter table tbl_produto_tbl_pedido add constraint fk_tbl_produto_tbl_pedido_tbl_produto foreign key (tbl_produto_id) references tbl_produto (id) on delete restrict on update restrict;
create index ix_tbl_produto_tbl_pedido_tbl_produto on tbl_produto_tbl_pedido (tbl_produto_id);

alter table tbl_produto_tbl_pedido add constraint fk_tbl_produto_tbl_pedido_tbl_pedido foreign key (tbl_pedido_id) references tbl_pedido (id) on delete restrict on update restrict;
create index ix_tbl_produto_tbl_pedido_tbl_pedido on tbl_produto_tbl_pedido (tbl_pedido_id);

alter table tbl_reserva add constraint fk_tbl_reserva_cliente_id foreign key (cliente_id) references tbl_usuario (id) on delete restrict on update restrict;

alter table tbl_reserva add constraint fk_tbl_reserva_atendente_id foreign key (atendente_id) references tbl_usuario (id) on delete restrict on update restrict;
create index ix_tbl_reserva_atendente_id on tbl_reserva (atendente_id);

alter table tbl_sugestao add constraint fk_tbl_sugestao_usuario_id foreign key (usuario_id) references tbl_usuario (id) on delete restrict on update restrict;
create index ix_tbl_sugestao_usuario_id on tbl_sugestao (usuario_id);

alter table tbl_usuario add constraint fk_tbl_usuario_cargo_id foreign key (cargo_id) references tbl_cargo (id) on delete restrict on update restrict;
create index ix_tbl_usuario_cargo_id on tbl_usuario (cargo_id);


# --- !Downs

alter table tbl_cardapio_tbl_produto drop foreign key fk_tbl_cardapio_tbl_produto_tbl_cardapio;
drop index ix_tbl_cardapio_tbl_produto_tbl_cardapio on tbl_cardapio_tbl_produto;

alter table tbl_cardapio_tbl_produto drop foreign key fk_tbl_cardapio_tbl_produto_tbl_produto;
drop index ix_tbl_cardapio_tbl_produto_tbl_produto on tbl_cardapio_tbl_produto;

alter table tbl_comanda drop foreign key fk_tbl_comanda_atendente_id;
drop index ix_tbl_comanda_atendente_id on tbl_comanda;

alter table tbl_comanda drop foreign key fk_tbl_comanda_cliente_id;
drop index ix_tbl_comanda_cliente_id on tbl_comanda;

alter table tbl_comanda drop foreign key fk_tbl_comanda_mesa_id;
drop index ix_tbl_comanda_mesa_id on tbl_comanda;

alter table tbl_comanda drop foreign key fk_tbl_comanda_cortesia_id;
drop index ix_tbl_comanda_cortesia_id on tbl_comanda;

alter table tbl_cortesia drop foreign key fk_tbl_cortesia_produto_id;
drop index ix_tbl_cortesia_produto_id on tbl_cortesia;

alter table tbl_mesa drop foreign key fk_tbl_mesa_reserva_id;
drop index ix_tbl_mesa_reserva_id on tbl_mesa;

alter table tbl_pedido drop foreign key fk_tbl_pedido_status_id;
drop index ix_tbl_pedido_status_id on tbl_pedido;

alter table tbl_pedido drop foreign key fk_tbl_pedido_comanda_id;
drop index ix_tbl_pedido_comanda_id on tbl_pedido;

alter table tbl_pedido_tbl_produto drop foreign key fk_tbl_pedido_tbl_produto_tbl_pedido;
drop index ix_tbl_pedido_tbl_produto_tbl_pedido on tbl_pedido_tbl_produto;

alter table tbl_pedido_tbl_produto drop foreign key fk_tbl_pedido_tbl_produto_tbl_produto;
drop index ix_tbl_pedido_tbl_produto_tbl_produto on tbl_pedido_tbl_produto;

alter table tbl_produto_tbl_cardapio drop foreign key fk_tbl_produto_tbl_cardapio_tbl_produto;
drop index ix_tbl_produto_tbl_cardapio_tbl_produto on tbl_produto_tbl_cardapio;

alter table tbl_produto_tbl_cardapio drop foreign key fk_tbl_produto_tbl_cardapio_tbl_cardapio;
drop index ix_tbl_produto_tbl_cardapio_tbl_cardapio on tbl_produto_tbl_cardapio;

alter table tbl_produto_tbl_pedido drop foreign key fk_tbl_produto_tbl_pedido_tbl_produto;
drop index ix_tbl_produto_tbl_pedido_tbl_produto on tbl_produto_tbl_pedido;

alter table tbl_produto_tbl_pedido drop foreign key fk_tbl_produto_tbl_pedido_tbl_pedido;
drop index ix_tbl_produto_tbl_pedido_tbl_pedido on tbl_produto_tbl_pedido;

alter table tbl_reserva drop foreign key fk_tbl_reserva_cliente_id;

alter table tbl_reserva drop foreign key fk_tbl_reserva_atendente_id;
drop index ix_tbl_reserva_atendente_id on tbl_reserva;

alter table tbl_sugestao drop foreign key fk_tbl_sugestao_usuario_id;
drop index ix_tbl_sugestao_usuario_id on tbl_sugestao;

alter table tbl_usuario drop foreign key fk_tbl_usuario_cargo_id;
drop index ix_tbl_usuario_cargo_id on tbl_usuario;

drop table if exists tbl_cardapio;

drop table if exists tbl_cardapio_tbl_produto;

drop table if exists tbl_cargo;

drop table if exists tbl_comanda;

drop table if exists tbl_cortesia;

drop table if exists tbl_fornecedor;

drop table if exists tbl_mesa;

drop table if exists tbl_pedido;

drop table if exists tbl_pedido_tbl_produto;

drop table if exists tbl_produto;

drop table if exists tbl_produto_tbl_cardapio;

drop table if exists tbl_produto_tbl_pedido;

drop table if exists tbl_reserva;

drop table if exists tbl_status;

drop table if exists tbl_sugestao;

drop table if exists tbl_usuario;


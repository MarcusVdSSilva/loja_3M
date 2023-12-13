create database loja_3M;
use loja_3M;

create table produto(
`id` int primary key auto_increment,
`nome` varchar(100) not null,
`marca` varchar(100) not null,
`preco` float not null,
`foto` varchar(100) not null,
`foto64` blob not null
);

create table usuario(
`id` int primary key auto_increment,
`perfil` enum('0','1','2') not null default '0',
`status` enum('A','I') not null default 'A',
`nome` varchar(100) not null,
`cpf_cnpj` varchar(14) not null unique,
`email` varchar(255) not null unique,
`senha` varchar(255) not null,
`telefone` varchar(11) not null

);

create table venda(
`id` int primary key auto_increment,
`valor` float not null,
`data` timestamp not null,
`id_usuario` int,
foreign key (`id_usuario`) references usuario(`id`)
);

create table venda_produto(
`id` int primary key auto_increment,
`quantidade` int not null default 1,
`id_produto` int,
`id_venda` int,
foreign key (`id_produto`) references produto(`id`),
foreign key (`id_venda`) references venda(`id`)
);

create table venda()
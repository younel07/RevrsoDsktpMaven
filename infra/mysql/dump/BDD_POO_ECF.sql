/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de cr�ation :  27/02/2024 09:41:59                      */
/*==============================================================*/

use BDD_POO_ECF.sql;

drop table if exists CLIENT;

drop table if exists PROSPECT;

/*==============================================================*/
/* Table : CLIENT                                               */
/*==============================================================*/
create table CLIENT
(
   IDCLIENT             int not null auto_increment,
   RAISONSOCIAL        char(40) not null,
	CONSTRAINT UC_RAISSOCIAL UNIQUE (RAISONSOCIAL2),
   NUMRUE              char(10) not null,
   NOMRUE              char(40) not null,
   CDPOSTAL            char(5) not null,
   VILLE               char(40) not null,
   TELEPHONE           char(20) not null,
   MAIL                char(40) not null,
   COMMENTAIRES         char(200),
   CHIFFREAFFAIRES      decimal(10,2) not null,
   NBREMPLOYES          int not null,
   primary key (IDCLIENT)
);

/*==============================================================*/
/* Table : PROSPECT                                             */
/*==============================================================*/
create table PROSPECT
(
   IDPROSPECT           int not null auto_increment,
   RAISONSOCIAL         char(40) not null,
	CONSTRAINT UC_RAISSOCIAL UNIQUE (RAISONSOCIAL),
   NUMRUE               char(10) not null,
   NOMRUE               char(40) not null,
   CDPOSTAL             char(5) not null,
   VILLE                char(40) not null,
   TELEPHONE            char(20) not null,
   MAIL                 char(40) not null,
   COMMENTAIRES         char(200),
   DATEPROSPECTION      timestamp not null,
   PROSPECTINTERESSE    int not null,
   primary key (IDPROSPECT)
);


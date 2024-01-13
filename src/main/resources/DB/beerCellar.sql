drop database if exists beerCellar;
create database beerCellar;
use beerCellar;


create table Utente(
	IdUtente int NOT NULL AUTO_INCREMENT primary key,
    Nome char(30) not null,
	Email char(30) not null,
	Pass char(200) not null,
	Gestore boolean not null
);

insert into utente values
(1,"Nome1","Email@1","Pass1",false),
(2,"Nome1","Email@1","Pass1",false),
(3,"Nome1","Email@1","Pass1",false),
(4,"Nome1","Email@1","Pass1",true);

create table Ordine(
	IdOrdine int NOT NULL AUTO_INCREMENT primary key,
    IdUtente int not null,
	DataOrdine date not null,
    PrezzoTotale double not null,
    Indirizzo char(30) not null,
    Citta char(30) not null,
    CAP char (5) not null,
    Provincia char(30) not null,
    foreign key(IdUtente) references Utente(IdUtente) 
);

insert into ordine values
(1,1,'2000-06-21',190.9,"via c 34","Poggiomarino","80040","NA"),
(2,1,'2000-06-21',320.9,"via ce 35","Sarno","84040","NA"),
(3,2,'2000-06-21',130.0,"piazza sdfdc 224","Ter","8040","NA"),
(4,3,'2000-06-21',230.7,"via cre 377","BHO","83040","NA"),
(5,1,'2000-06-21',190.9,"via c 34","Poggiomarino","80040","NA"),
(6,1,'2000-06-21',320.9,"via ce 35","Sarno","84040","NA"),
(7,2,'2000-06-21',130.0,"piazza sdfdc 224","Ter","8040","NA"),
(8,3,'2000-06-21',230.7,"via cre 377","BHO","83040","NA"),
(9,1,'2000-06-21',190.9,"via c 34","Poggiomarino","80040","NA"),
(10,1,'2000-06-21',320.9,"via ce 35","Sarno","84040","NA"),
(11,2,'2000-06-21',130.0,"piazza sdfdc 224","Ter","8040","NA"),
(12,3,'2000-06-21',230.7,"via cre 377","BHO","83040","NA"),
(13,1,'2000-06-21',130.0,"piazza sdfdc 224","Ter","8040","NA"),
(14,1,'2000-06-21',230.7,"via cre 377","BHO","83040","NA"),
(15,1,'2000-06-21',130.0,"piazza sdfdc 224","Ter","8040","NA"),
(16,1,'2000-06-21',230.7,"via cre 377","BHO","83040","NA"),
(17,1,'2000-06-21',130.0,"piazza sdfdc 224","Ter","8040","NA"),
(18,1,'2000-06-21',230.7,"via cre 377","BHO","83040","NA");

create table Carrello(
	IdCarrello int primary key,
	foreign key(IdCarrello) references Utente(IDUtente)    
);

insert into carrello values
(1),
(2),
(3),
(4);

create table Prodotto(
	IdProdotto int NOT NULL AUTO_INCREMENT primary key,
	Nome char(40) not null,
    Formato char(30) not null,
    Prezzo double not null,
    Fermentazione char(30) not null,
    Stile char(30) not null,
    Colore char(30) not null,
    TassoAlcolico double not null,
	Descrizione char(255) not null,
    Glutine boolean not null,
    Birrificio char(30) not null,
    InCatalogo boolean not null
);

insert into prodotto values
(1,"prodotto1","lattina",13.45,"mista","IPA","rossa",5.7,"Questa è la descrizione della birra",false,"birrificio1",true),
(2,"prodotto2","lattina",16.45,"mista","IPA","bionda",4.7,"Questa è la descrizione della birra",false,"birrificio1",true),
(3,"prodotto3","fusto",7.45,"mista","IPA","rossa",8.7,"Questa è la descrizione della birra",false,"birrificio1",true),
(4,"prodotto4","bottiglia",23.45,"fer1","IPA","bionda",4.9,"Questa è la descrizione della birra",false,"birrificio1",true),
(5,"prodotto5","bottiglia",43.45,"fer1","IPA","scura",9.7,"Questa è la descrizione della birra",false,"birrificio1",false),
(6,"prodotto6","bottiglia",23.45,"mista","IPA","rossa",12.7,"Questa è la descrizione della birra",true,"birrificio1",true);

create table ContenutoCarrello(
    IdCarrello int not null,
	IdProdotto int not null,
    Quantita int not null,
    primary key(IdProdotto,IdCarrello),
	foreign key(IdProdotto) references Prodotto(IdProdotto),
	foreign key(IdCarrello) references Carrello(IdCarrello)
);

insert into contenutocarrello values
(1,1,3),
(1,2,2),
(2,1,1),
(2,3,3),
(3,1,3);

create table AcquistoProdotto(
	IdOrdine int not null,
	IdProdotto int not null,
    Quantita int not null,
    PrezzoAcquisto double not null,
    primary key(IdProdotto,IdOrdine),
    foreign key(IdProdotto) references Prodotto(IdProdotto),
	foreign key(IdOrdine) references Ordine(IdOrdine)
);

insert into AcquistoProdotto values
(1,1,3,30.45),
(1,2,2,14.45),
(2,3,1,24.45),
(2,1,3,38.45),
(3,1,2,90.45),
(3,3,3,50.45);

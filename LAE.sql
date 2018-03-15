
Create databse LAE-dB;

DROP TABLE IF EXISTS `attraction`;

  CREATE TABLE `attraction` ( 
   `idattraction` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL,
  `description` longtext NOT NULL,
  `address` longtext NOT NULL,
  `category` varchar(45) NOT NULL,
  `starthour` time DEFAULT NULL,
  `endhour` time DEFAULT NULL,
  `picture` longtext,
  `username` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`idattraction`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `usernamekey_idx` (`username`),
  CONSTRAINT `usernamekey` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table `attraction`

LOCK TABLES `attraction` WRITE;

INSERT INTO `attraction` VALUES (3,'attraction1','Great','University House Winston Churchill Avenue Portsmouth Hampshire PO1 2UP','Pubs','11:30:00','16:30:00','logo.jpg','admin','Live'),
(5,'attraction2','fffff','23 Southampton Road, Portsmouth Hampshire PO6 4SH','Museum','10:31:00','11:37:00','','admin','Live'),(7,'attraction3','Great place','Fratton Park  Frogmore Road  Portsmouth  PO4 8RA','Sport','11:30:00','16:50:00',NULL,'tourist','Pending');
 UNLOCK TABLES;
 
DROP TABLE IF EXISTS `rating`;
  `idrating` int(11) NOT NULL AUTO_INCREMENT,
  `idattraction` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`idrating`),
  KEY `attractionidkey_idx` (`idattraction`),
  CONSTRAINT `attractionidkey` FOREIGN KEY (`idattraction`) REFERENCES `attraction` (`idattraction`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table `rating`
 UNLOCK TABLES; 
  
DROP TABLE IF EXISTS `role`;
  `idrole` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table `role`
 LOCK TABLES `role` WRITE; 
  
 INSERT INTO `role` VALUES (1,'ROLE_TOURIST','tourist'),(2,'ROLE_ADMIN','admin'),(3,'ROLE_TOURIST','tourist2');
 UNLOCK TABLES;  
 
 -- Table structure for table `user`
 
 CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(200) NOT NULL,
  `lastname` varchar(200) NOT NULL,
  `phoneno` varchar(45) NOT NULL,
  `email` varchar(200) NOT NULL,
  `address` longtext NOT NULL,
  `active` int(11) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table `user`

LOCK TABLES `user` WRITE;

INSERT INTO `user` VALUES (1,'tourist','tourist123','Tourist Firstname','Tourist Surname','0775899994','a.adeseye007@gmail.com','23 Southampton Road, Portsmouth Hampshire PO6 4SH',1),(2,'admin','admin123','Admin','Admin Lastname','08557773','aish.lapy@gmail.com','University House\nWinston Churchill Avenue\nPortsmouth\nHampshire\nPO1 2UP',1),
(4,'tourist2','tourist2','Tourist 2','Tourist','786798715','adeyemi@brilloconnetz.com','23 Southampton Road, Portsmouth Hampshire PO6 4SH',1);

UNLOCK TABLES;


  create table Nightlife (
    Sector 
    Establishment name Varchar(20) not null, 
    Description Varchar(40) not null, 
    Business Hours Time
    Picture jpg 
    Reviews Varchar (100) not null,
    Longitude int(10) not null,
    Latitude int(10) not null

  );
  create table Restaurants (
    Sector 
    Establishment name Varchar(20) not null, 
    Description Varchar(40) not null, 
    Business Hours Time
    Picture jpg
    Reviews Varchar (100) not null,
    Longitude int(10) not null,
    Latitude int(10) not null
  );
  
  create table Bar/Pubs (
    Sector 
    Establishment name Varchar(20) not null, 
    Description Varchar(40) not null, 
    Business Hours Time
    Pictur jpg
    Reviews Varchar (100) not null,
    Longitude int(10) not null,
    Latitude int(10) not null
  );
  
  create table Cinemas/Theatre (
     Sector  
    Establishment name Varchar(20) not null, 
    Description Varchar(40) not null, 
    Business Hours Time
    Picture jpg
    Reviews Varchar (100) not null,
    Longitude int(10) not null,
    Latitude int(10) not null
  );
  create table Museums (
     Sector 
    Establishment name Varchar(20) not null, 
    Description Varchar(40) not null, 
    Business Hours Time
    Picture jpg
    Reviews Varchar (100) not null,
    Longitude int(10) not null,
    Latitude int(10) not null
  );
  create table Sport (
     Sector 
    Establishment name Varchar(20) not null, 
    Description Varchar(40) not null, 
    Business Hours Time
    Picture jpg
    Reviews Varchar (100) not null,
    Longitude int(10) not null,
    Latitude int(10) not null,
  );
  create table Parks (
    Sector 
    Establishment name Varchar(20) not null, 
    Description Varchar(40) not null, 
    Business Hours Time
    Picture jpg
    Reviews Varchar (100) not null
    Longitude int(10) not null,
    Latitude int(10) not null
  );
  create table Landmarks/POI (
    Sector 
    Establishment name Varchar(20) not null, 
    Description Varchar(40) not null, 
    Business Hours Time
    Picture jpg
    Reviews Varchar (100) not null,
    Longitude int(10) not null,
    Latitude int(10) not null
  );
  create table Transport (
    Sector 
    Establishment name Varchar(20) not null, 
    Description Varchar(40) not null, 
    Business Hours Time
    Picture jpg
    Reviews Varchar (100) not null,
    Longitude int(10) not null,
    Latitude int(10) not null
  );

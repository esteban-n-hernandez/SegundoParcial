CREATE TABLE Clientes (
    DNI VARCHAR(8) PRIMARY KEY,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50),
    Telefono VARCHAR(15),
    Email VARCHAR(50)
);

CREATE TABLE Administrativos (
    DNI VARCHAR(8) PRIMARY KEY,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50),
    Legajo VARCHAR(10),
    Telefono VARCHAR(15)
);

CREATE TABLE Autores (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50),
    Nacionalidad VARCHAR(50)
);

CREATE TABLE Editoriales (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NombreEditorial VARCHAR(50),
    Direccion VARCHAR(100),
    PaginaWeb VARCHAR(100),
    Email VARCHAR(50),
    NumeroDeContacto VARCHAR(15)
);

CREATE TABLE Libros (
    ISBN VARCHAR(13) PRIMARY KEY,
    Titulo VARCHAR(100),
    CantidadDePaginas INT,
    Genero VARCHAR(30),
    Edicion INT,
    AutorID INT,
    FOREIGN KEY (AutorID) REFERENCES Autores(ID)
);

CREATE TABLE LibrosEditoriales (
    LibroISBN VARCHAR(13),
    EditorialID INT,
    FOREIGN KEY (LibroISBN) REFERENCES Libros(ISBN),
    FOREIGN KEY (EditorialID) REFERENCES Editoriales(ID)
);

CREATE TABLE Reservas (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ClienteDNI VARCHAR(8),
    LibroISBN VARCHAR(13),
    FechaDeReserva DATE,
    FechaDeDevolucion DATE,
    FOREIGN KEY (ClienteDNI) REFERENCES Clientes(DNI),
    FOREIGN KEY (LibroISBN) REFERENCES Libros(ISBN)
);

CREATE TABLE Prestamos (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ReservaID INT,
    AdministrativoDNI VARCHAR(8),
    FechaDePrestamo DATE,
    FechaDeDevolucion DATE,
    FOREIGN KEY (ReservaID) REFERENCES Reservas(ID),
    FOREIGN KEY (AdministrativoDNI) REFERENCES Administrativos(DNI)
);

ALTER TABLE libros
ADD EditorialID int;

ALTER TABLE libros
ADD CONSTRAINT FK_Libro_Editorial
FOREIGN KEY (EditorialID) REFERENCES editoriales(ID);

-----

STORED PROCEDURES

DELIMITER //
CREATE PROCEDURE InsertarEnAutores(IN Nombre VARCHAR(50), IN Apellido VARCHAR(50), IN Nacionalidad VARCHAR(50))
BEGIN
    INSERT INTO Autores (Nombre, Apellido, Nacionalidad)
    VALUES (Nombre, Apellido, Nacionalidad);
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE EliminarAutor(IN ID INT)
BEGIN
    DELETE FROM Autores WHERE ID = ID;
END //
DELIMITER 

DELIMITER //
CREATE PROCEDURE InsertarEnReservas(IN ClienteDNI VARCHAR(8), IN LibroISBN VARCHAR(13), IN FechaDeReserva DATE, IN FechaDeDevolucion DATE)
BEGIN
    INSERT INTO Reservas (ClienteDNI, LibroISBN, FechaDeReserva, FechaDeDevolucion)
    VALUES (ClienteDNI, LibroISBN, FechaDeReserva, FechaDeDevolucion);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE ActualizarReservas(IN ID INT, IN ClienteDNI VARCHAR(8), IN LibroISBN VARCHAR(13), IN FechaDeReserva DATE, IN FechaDeDevolucion DATE)
BEGIN
    UPDATE Reservas 
    SET ClienteDNI = ClienteDNI, LibroISBN = LibroISBN, FechaDeReserva = FechaDeReserva, FechaDeDevolucion = FechaDeDevolucion
    WHERE ID = ID;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE InsertarEnEditoriales(IN NombreEditorial VARCHAR(50), IN Direccion VARCHAR(100), IN PaginaWeb VARCHAR(100), IN Email VARCHAR(50), IN NumeroDeContacto VARCHAR(15))
BEGIN
    INSERT INTO Editoriales (NombreEditorial, Direccion, PaginaWeb, Email, NumeroDeContacto)
    VALUES (NombreEditorial, Direccion, PaginaWeb, Email, NumeroDeContacto);
END //
DELIMITER ;

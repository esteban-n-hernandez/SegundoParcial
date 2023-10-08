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

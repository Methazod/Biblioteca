package biblioteca;

import java.sql.*;

public class Libro {
    int id_libro;
    String titulo;
    String autor;
    Date fecha_publicacion;
    int numero_paginas;
    String genero;
    String saga;
    Boolean leido;
    public Libro(int id_libro, String titulo, String autor, Date fecha_publicacion, int numero_paginas, String genero, String saga, Boolean leido){
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.autor = autor;
        this.fecha_publicacion = fecha_publicacion;
        this.numero_paginas = numero_paginas;
        this.genero = genero;
        this.saga = saga;
        this.leido = leido;
    }        
}

package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Libreria {
    List<Libro> libros;
    List<Genero> generos;
    String jdbc = "jdbc:mysql://localhost:3305/biblioteca";
    Connection conexion = null;

    public Libreria() {
        libros = new ArrayList<>();
        generos = new ArrayList<>();
        cargarLibros();
        cargarGeneros();
    }
    
    private void cargarGeneros(){
        conectar();
        String selectSQL = "SELECT id_genero, genero FROM genero";
        try{
            Statement statement = conexion.createStatement();
            ResultSet set = statement.executeQuery(selectSQL);
            while (set.next()) {
                generos.add(new Genero(set.getInt("id_genero"), set.getString("genero")));                
            }
            set.close();
            statement.close();
        } catch (SQLException sql) {
            sql.printStackTrace();
        } finally {
            cerrar();
        }
    }
    
    private int generoNumero(String generoTexto){        
        for(Genero g : generos)
            if(g.genero.equals(generoTexto))
                return g.id_genero;
        return -1;
    }

    private void anadirLibro(String titulo, String autor, Date fecha_publicacion, int numero_paginas, String genero, String saga, Boolean leido) {        
        anadirLibro(ultimoId()+1, titulo, autor, fecha_publicacion, numero_paginas, genero, saga, leido);
    }
    
    private void anadirLibro(int id_titulo, String titulo, String autor, Date fecha_publicacion, int numero_paginas, String genero, String saga, Boolean leido) {
        if(saga == "null") saga = null;
        libros.add(new Libro(id_titulo, titulo, autor, fecha_publicacion, numero_paginas, genero, saga, leido));
    }

    public void insertarLibro(String titulo, String autor, Date fecha_publicacion, int numero_paginas, String genero, String saga, Boolean leido) {
        if (saga.equals("null")) saga = null;        
        conectar();
        String insertSQL = "INSERT INTO libro(titulo, autor, fecha_publicacion, numero_paginas, genero, saga, leido) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conexion.prepareStatement(insertSQL);
            pstmt.setString(1, titulo);
            pstmt.setString(2, autor);
            pstmt.setDate(3, fecha_publicacion);
            pstmt.setInt(4, numero_paginas);
            pstmt.setInt(5, generoNumero(genero)); // Asegúrate de que generoNumero no use recursos cerrados
            pstmt.setString(6, saga);
            pstmt.setBoolean(7, leido);
            pstmt.executeUpdate();
            pstmt.close();
            anadirLibro(titulo, autor, fecha_publicacion, numero_paginas, genero, saga, leido);                        
        } catch (SQLException sql) {
            sql.printStackTrace();
        } finally {
            cerrar();
        }
    }   

    public void cargarLibros() {
        conectar();
        String selectSQL = "SELECT l.id_libro, l.titulo, l.autor, l.fecha_publicacion, l.numero_paginas, g.genero, l.saga, l.leido FROM libro l JOIN genero g ON l.genero = g.id_genero";
        try {
            Statement statement = conexion.createStatement(); 
            ResultSet set = statement.executeQuery(selectSQL);
            while (set.next()) {
                anadirLibro(set.getInt("id_libro"), set.getString("titulo"), set.getString("autor"), set.getDate("fecha_publicacion"), set.getInt("numero_paginas"), set.getString("genero"), set.getString("saga"), set.getBoolean("leido"));
            }
            set.close();
            statement.close();
        } catch (SQLException sql) {
            sql.printStackTrace();
        } finally {
            cerrar();
        }
    }
    
    public int ultimoId(){
        return libros.get(libros.size()-1).id_libro;
    }

    public void conectar() {
        try {
            conexion = DriverManager.getConnection(jdbc, "root", "root");
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    public void cerrar() {
        try {
            if (conexion != null) {
                conexion.close();
                conexion = null;
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }
}

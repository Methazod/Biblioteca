package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuscarLibro extends JFrame {
    static final int WIDTH = 800;
    static final int HEIGHT = 700;
    JPanel panelAbajo, panelArriba;
    JButton buscar, anadir, borrar, leido;
    JComboBox<String> generos = new JComboBox<>();
    JRadioButton largo, corto, si, no, siLeido, noLeido;
    ButtonGroup grupoDuracion, grupoSaga, grupoLeido;
    Connection conexion;
    String jdbc = "jdbc:mysql://localhost:3305/biblioteca";
    Libreria libreria;
    List<Libro> librosSeleccionados;

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new BuscarLibro());
    }

    public BuscarLibro() {
        super("Buscar libro segun parametro");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);              
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);        
        this.setVisible(true);
        libreria = new Libreria();
        librosSeleccionados = new ArrayList<>();
        this.inicializarComponentes();
    }    

    public void inicializarComponentes() {        
        panelAbajo();
        panelArriba();
    }

    private void panelAbajo() {
        panelAbajo = new JPanel();
        panelAbajo.setBackground(Color.LIGHT_GRAY);
        buscar = new JButton("Buscar Libro");
        anadir = new JButton("Añadir Libro");
        leido = new JButton("Cambiar leido");
        borrar = new JButton("Borrar resultados");

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Buscar el libro según parámetros
                buscarLibro();
            }
        });

        anadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar a insertarLibro
                insertarLibro();
            }
        });
        
        leido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Borrar los resultados
                cambiarLeido();
            }
        });
        
        borrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Borrar los resultados
                librosSeleccionados.clear();
                repaint();
            }
        });                

        panelAbajo.add(buscar);        
        panelAbajo.add(borrar);
        panelAbajo.add(anadir);
        panelAbajo.add(leido);
        add(panelAbajo, BorderLayout.SOUTH);
    }

    private void panelArriba() {
        panelArriba = new JPanel();
        panelArriba.setBackground(Color.LIGHT_GRAY);
        panelArriba.setLayout(new GridLayout(4, 2));        
        seleccionGenero();
        numPaginas();
        saga();
        leido();
        add(panelArriba, BorderLayout.NORTH);
    }

    private void seleccionGenero() {
        for(Genero g : libreria.generos)
            generos.addItem(g.genero);
        generos.setBackground(Color.LIGHT_GRAY);
        panelArriba.add(new JLabel("Selecciona el Género"));
        panelArriba.add(generos);
    }

    private void numPaginas() {
        JPanel panelDuracion = new JPanel();
        panelDuracion.setBackground(Color.LIGHT_GRAY);
        panelArriba.add(new JLabel("Corto o Largo"));
        largo = new JRadioButton("Largo", false);
        largo.setBackground(Color.LIGHT_GRAY);
        corto = new JRadioButton("Corto", false);
        corto.setBackground(Color.LIGHT_GRAY);
        grupoDuracion = new ButtonGroup();
        grupoDuracion.add(largo);
        grupoDuracion.add(corto);
        panelDuracion.add(largo);
        panelDuracion.add(corto);
        panelArriba.add(panelDuracion);
    }

    private void saga() {
        JPanel panelSaga = new JPanel();
        panelSaga.setBackground(Color.LIGHT_GRAY);
        panelArriba.add(new JLabel("Saga?"));
        si = new JRadioButton("Si", false);
        si.setBackground(Color.LIGHT_GRAY);
        no = new JRadioButton("No", false);
        no.setBackground(Color.LIGHT_GRAY);
        grupoSaga = new ButtonGroup();
        grupoSaga.add(si);
        grupoSaga.add(no);
        panelSaga.add(si);
        panelSaga.add(no);
        panelArriba.add(panelSaga);
    }
    
    private void leido() {
        JPanel panelLeido = new JPanel();
        panelLeido.setBackground(Color.LIGHT_GRAY);
        panelArriba.add(new JLabel("Leido?"));
        siLeido = new JRadioButton("Si", false);
        siLeido.setBackground(Color.LIGHT_GRAY);
        noLeido = new JRadioButton("No", false);
        noLeido.setBackground(Color.LIGHT_GRAY);
        grupoLeido = new ButtonGroup();
        grupoLeido.add(siLeido);
        grupoLeido.add(noLeido);
        panelLeido.add(siLeido);
        panelLeido.add(noLeido);
        panelArriba.add(panelLeido);
    }

    private void buscarLibro() {
        // Implementar la lógica para buscar el libro
        for(Libro l : libreria.libros){            
            if(l.genero.equals((String)generos.getSelectedItem())){
                if(corto.isSelected()){
                    if(l.numero_paginas < 300){
                        if(si.isSelected() && l.saga != null){                            
                            if(siLeido.isSelected() && l.leido){
                                librosSeleccionados.add(l);
                            } else if (noLeido.isSelected() && !l.leido){
                                librosSeleccionados.add(l);
                            }
                        } else if (no.isSelected() && l.saga == null) {                            
                            if(siLeido.isSelected() && l.leido){
                                librosSeleccionados.add(l); 
                            } else if (noLeido.isSelected() && !l.leido){
                                librosSeleccionados.add(l);
                            }                            
                        }
                    }
                } else if (largo.isSelected()) {
                    if(l.numero_paginas > 300){
                        if(si.isSelected() && l.saga != null){                            
                            if(siLeido.isSelected() && l.leido){
                                librosSeleccionados.add(l);
                            } else if (noLeido.isSelected() && !l.leido){
                                librosSeleccionados.add(l);
                            }
                        } else if (no.isSelected() && l.saga == null) {                            
                            if(siLeido.isSelected() && l.leido){
                                librosSeleccionados.add(l);
                            } else if (noLeido.isSelected() && !l.leido){
                                librosSeleccionados.add(l);
                            }                            
                        }
                    }
                }
            }
        }
        if(!librosSeleccionados.isEmpty()) 
            repaint();
        else
            JOptionPane.showMessageDialog(this, "No se encontraron libros.");
    }

    private void insertarLibro() {
        dispose();
        new InsertarLibro();
    }
    
    private void cambiarLeido() {
        dispose();
        new CambiarLeido();
    }
    
    @Override
    public void paint(Graphics g){        
        if(librosSeleccionados.isEmpty()){            
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, WIDTH, HEIGHT);        
            g.setColor(Color.BLACK);
            this.paintComponents(g);
        }
        int posicion = si.isSelected()?obtenerPosicion((int)(Math.random() * librosSeleccionados.size())):(int)(Math.random() * librosSeleccionados.size());
        
        if(!librosSeleccionados.isEmpty()){
            g.drawString(librosSeleccionados.get(posicion).titulo, 100, 300);
            g.drawString(librosSeleccionados.get(posicion).autor, 350, 300);
            g.drawString(""+librosSeleccionados.get(posicion).numero_paginas, 500, 300);
            if(librosSeleccionados.get(posicion).saga != null) g.drawString(librosSeleccionados.get(posicion).saga, 600, 300);
            
            librosSeleccionados.clear();
        }   
    }
    
    private int obtenerPosicion(int pos){
        if(pos == 0)
            return pos;
        String sagaActual = librosSeleccionados.get(pos).saga;
        String sagaAnterior = librosSeleccionados.get(pos-1).saga;
        if(!sagaActual.equals(sagaAnterior))
            return pos;
        else
            return obtenerPosicion(pos-1);
    }
}
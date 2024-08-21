package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CambiarLeido extends JFrame{
    static final int WIDTH = 800;
    static final int HEIGHT = 700;
    Libreria libreria;
    JPanel panelAbajo, panelArriba;
    JButton buscar, anadir, leido;
    JComboBox<String> libros = new JComboBox<>();
    List<Libro> librosLista;
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new CambiarLeido());
    }
    
    public CambiarLeido() {
        super("Cambia EXCLUSIVAMENTE si el libro no ha sido leido a leido");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);              
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);        
        this.setVisible(true);
        libreria = new Libreria();
        librosLista = new ArrayList();
        this.inicializarComponentes();
    }
    
    public void inicializarComponentes(){
        panelAbajo();
        panelArriba();
    }
    
    public void panelAbajo(){
        panelAbajo = new JPanel();
        panelAbajo.setBackground(Color.LIGHT_GRAY);
        buscar = new JButton("Buscar Libro");
        anadir = new JButton("Añadir Libro");
        leido = new JButton("Cambiar leido");

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
        
        leido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarLeido();
            }
        });  

        panelAbajo.add(leido);
        panelAbajo.add(anadir);
        panelAbajo.add(buscar);
        add(panelAbajo, BorderLayout.SOUTH);
    }
    
    public void panelArriba(){
        panelArriba = new JPanel();
        panelArriba.setBackground(Color.LIGHT_GRAY);
        panelArriba.setLayout(new GridLayout(2, 2));
        
        seleccionLibro();        
        
        add(panelArriba, BorderLayout.NORTH);
    }
    
    private void seleccionLibro() {
        libros.setBackground(Color.LIGHT_GRAY);
        for(Libro l : libreria.libros)
            if(!l.leido){
                libros.addItem("Titulo: " + l.titulo + " Autor/a: " + l.autor);
                librosLista.add(l);
            }        
        panelArriba.add(new JLabel("Selecciona el libro"));
        panelArriba.add(libros);
    }
    
    private void insertarLibro() {
        dispose();
        new InsertarLibro();
    }
    
    private void buscarLibro() {
        dispose();
        new BuscarLibro();
    }
    
    private void cambiarLeido() {
        Libro libroSeleccionado = librosLista.get(libros.getSelectedIndex());
        if(libroSeleccionado != null){
            libreria.cambiarLeido(libroSeleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "No existe el libro seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
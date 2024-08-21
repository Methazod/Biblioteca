package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class InsertarLibro extends JFrame {
    static final int WIDTH = 800;
    static final int HEIGHT = 700;
    JPanel panelAbajo, panelArriba;
    JButton insertar, buscar, leido;
    JTextField textNombre, textAutor, textPaginas, textFecha, textSaga;
    JComboBox<String> generos = new JComboBox<>();
    JRadioButton siLeido, noLeido;
    ButtonGroup grupoLeido;
    Connection conexion;
    String jdbc = "jdbc:mysql://localhost:3305/biblioteca";
    Libreria libreria;

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new InsertarLibro());
    }

    public InsertarLibro() {
        super("Inserta la informacion del libro");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);        
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setVisible(true);
        libreria = new Libreria();  // Inicializa la instancia de Libreria
        this.inicializarComponentes();        
    }

    public void inicializarComponentes() {
        panelAbajo();
        panelArriba();
    }

    private void panelAbajo() {
        panelAbajo = new JPanel();
        panelAbajo.setBackground(Color.LIGHT_GRAY);
        insertar = new JButton("Insertar Libro");
        buscar = new JButton("Buscar Libro");
        leido = new JButton("Cambiar Leido");

        insertar.addActionListener(e -> insertarLibro());
        buscar.addActionListener(e -> buscarLibro());
        leido.addActionListener(e -> cambiarLeido());

        panelAbajo.add(insertar);
        panelAbajo.add(buscar);
        panelAbajo.add(leido);
        add(panelAbajo, BorderLayout.SOUTH);
    }

    private void panelArriba() {
        panelArriba = new JPanel();
        panelArriba.setBackground(Color.LIGHT_GRAY);
        panelArriba.setLayout(new GridLayout(8, 2));

        seleccionGenero();

        panelArriba.add(new JLabel("Titulo"));
        panelArriba.add(textNombre = new JTextField());

        panelArriba.add(new JLabel("Autor"));
        panelArriba.add(textAutor = new JTextField());

        panelArriba.add(new JLabel("Fecha de Publicacion (año-mes-dia)"));
        panelArriba.add(textFecha = new JTextField());
        
        panelArriba.add(new JLabel("Numero de Paginas"));
        panelArriba.add(textPaginas = new JTextField());
        
        panelArriba.add(new JLabel("Saga (Si no pertenece a una saga escriba null)"));
        panelArriba.add(textSaga = new JTextField());
        
        panelArriba.add(new JLabel("Leido"));
        JPanel panelLeido = new JPanel();
        panelLeido.setBackground(Color.LIGHT_GRAY);        
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

        add(panelArriba, BorderLayout.NORTH);
    }

    private void seleccionGenero() {
        for(Genero g : libreria.generos)
            generos.addItem(g.genero);        
        panelArriba.add(new JLabel("Selecciona el Género"));
        panelArriba.add(generos);
    }

    private void insertarLibro() {
        try {            
            String titulo = textNombre.getText();
            String autor = textAutor.getText();
            Date fecha_publicacion = Date.valueOf(textFecha.getText()); // Conversión correcta de fecha
            int numeroPaginas = Integer.parseInt(textPaginas.getText());
            String genero = (String) generos.getSelectedItem();
            String saga = textSaga.getText();
            Boolean leido = siLeido.isSelected();                        

            libreria.insertarLibro(titulo, autor, fecha_publicacion, numeroPaginas, genero, saga, leido);
            JOptionPane.showMessageDialog(this, "Libro insertado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al insertar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void buscarLibro() {
        dispose();
        new BuscarLibro();
    }
    
    private void cambiarLeido() {        
        dispose();
        new CambiarLeido();
    }
}

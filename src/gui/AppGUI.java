package gui;

import gestion.Logistica;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AppGUI extends JFrame {

  JButton agregarBtn, eliminarBtn, guardarBtn, cancelarBtn;
  JTextField codigoField, clienteField, pesoField, distanciaField;
  JComboBox<String> tipoBox;
  JTable tablaEnvios;
  DefaultTableModel modeloTabla;

  private Logistica logistica;

  public AppGUI() {
    super("Operador Logístico");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600); // Tamaño grande para ver todos los paneles
    setLocationRelativeTo(null);
    setLayout(new BorderLayout(10, 10));

    // Panel superior para botones principales
    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    agregarBtn = new JButton("Agregar envío");
    eliminarBtn = new JButton("Eliminar envío");
    panelBotones.add(agregarBtn);
    panelBotones.add(eliminarBtn);
    add(panelBotones, BorderLayout.NORTH);

    // Panel central: formulario en 2 columnas
    JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));

    // Columna izquierda del formulario
    JPanel colIzq = new JPanel();
    colIzq.setLayout(new BoxLayout(colIzq, BoxLayout.Y_AXIS));
    colIzq.add(new JLabel("Número"));
    codigoField = new JTextField(10);
    colIzq.add(codigoField);
    colIzq.add(Box.createVerticalStrut(8));
    colIzq.add(new JLabel("Cliente"));
    clienteField = new JTextField(15);
    colIzq.add(clienteField);
    colIzq.add(Box.createVerticalStrut(8));
    colIzq.add(new JLabel("Peso"));
    pesoField = new JTextField(10);
    colIzq.add(pesoField);

    // Columna derecha del formulario
    JPanel colDer = new JPanel();
    colDer.setLayout(new BoxLayout(colDer, BoxLayout.Y_AXIS));
    colDer.add(new JLabel("Tipo"));
    tipoBox = new JComboBox<>(new String[] {"Terrestre", "Aéreo", "Marítimo"});
    colDer.add(tipoBox);
    colDer.add(Box.createVerticalStrut(8));
    colDer.add(new JLabel("Distancia en Km"));
    distanciaField = new JTextField(10);
    colDer.add(distanciaField);
    colDer.add(Box.createVerticalStrut(16));
    JPanel panelBotonesForm = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
    guardarBtn = new JButton("Guardar");
    cancelarBtn = new JButton("Cancelar");
    panelBotonesForm.add(guardarBtn);
    panelBotonesForm.add(cancelarBtn);
    colDer.add(panelBotonesForm);

    // Añadir columnas al panel central
    panelCentral.add(colIzq);
    panelCentral.add(colDer);
    panelCentral.setPreferredSize(new Dimension(600, 120));
    add(panelCentral, BorderLayout.CENTER);

    // Panel inferior: tabla de envíos
    String[] columnas = {"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};
    modeloTabla = new DefaultTableModel(columnas, 0);
    tablaEnvios = new JTable(modeloTabla);
    JScrollPane panelTabla = new JScrollPane(tablaEnvios);
    panelTabla.setPreferredSize(new Dimension(700, 240));
    add(panelTabla, BorderLayout.SOUTH);

    setVisible(true);


  }
}

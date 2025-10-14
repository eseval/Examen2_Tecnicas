package gui;

import envio.Envio;
import gestion.Logistica;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AppGUI extends JFrame {

  private JTable tblEnvios;
  private JPanel pnlEditarEnvio;
  private JTextField txtNumero, txtCliente, txtPeso, txtDistancia;
  private JComboBox<String> cmbTipo;
  private DefaultTableModel modeloTabla;
  private String[] encabezadosEnvios = {"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};

  Logistica logistica = new Logistica();

  public AppGUI() {
    setTitle("Operador Logístico");
    setSize(700, 500);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    // Barra de herramientas superior
    JToolBar tbLogistica = new JToolBar();

    JButton btnAgregarEnvio = new JButton();
    ImageIcon iconAgregar = new ImageIcon(getClass().getResource("/iconos/agregar.png"));
    Image imgAgregar = iconAgregar.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
    btnAgregarEnvio.setIcon(new ImageIcon(imgAgregar));
    //    new ActionListener() {
    //      public void actionPerformed(ActionEvent evt) {
    //        btnAgregarEnvioClick();
    //      }
    //    }
    btnAgregarEnvio.addActionListener(evt -> btnAgregarEnvioClick());
    tbLogistica.add(btnAgregarEnvio);

    JButton btnQuitarEnvio = new JButton();
    ImageIcon iconEliminar = new ImageIcon(getClass().getResource("/iconos/eliminar.png"));
    Image imgEliminar = iconEliminar.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
    btnQuitarEnvio.setIcon(new ImageIcon(imgEliminar));
    btnQuitarEnvio.addActionListener(
        //        new ActionListener() {
        //          public void actionPerformed(ActionEvent evt) {
        //            btnQuitarEnvioClick();
        //          }
        //        });
        evt -> btnQuitarEnvioClick());
    tbLogistica.add(btnQuitarEnvio);

    add(tbLogistica, BorderLayout.NORTH);

    // === Panel principal con BoxLayout ===
    JPanel pnlPrincipal = new JPanel();
    pnlPrincipal.setLayout(new BoxLayout(pnlPrincipal, BoxLayout.Y_AXIS));

    pnlEditarEnvio = new JPanel();
    pnlEditarEnvio.setLayout(null);
    pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 120));

    JLabel lblNumero = new JLabel("Número");
    lblNumero.setBounds(10, 10, 100, 25);
    pnlEditarEnvio.add(lblNumero);

    txtNumero = new JTextField();
    txtNumero.setBounds(110, 10, 100, 25);
    pnlEditarEnvio.add(txtNumero);

    JLabel lblCliente = new JLabel("Cliente");
    lblCliente.setBounds(10, 40, 100, 25);
    pnlEditarEnvio.add(lblCliente);

    txtCliente = new JTextField();
    txtCliente.setBounds(110, 40, 100, 25);
    pnlEditarEnvio.add(txtCliente);

    JLabel lblPeso = new JLabel("Peso");
    lblPeso.setBounds(10, 70, 100, 25);
    pnlEditarEnvio.add(lblPeso);

    txtPeso = new JTextField();
    txtPeso.setBounds(110, 70, 100, 25);
    pnlEditarEnvio.add(txtPeso);

    JLabel lblTipo = new JLabel("Tipo");
    lblTipo.setBounds(230, 10, 100, 25);
    pnlEditarEnvio.add(lblTipo);

    cmbTipo = new JComboBox<>(new String[] {"Terrestre", "Aéreo", "Marítimo"});
    cmbTipo.setBounds(330, 10, 120, 25);
    pnlEditarEnvio.add(cmbTipo);

    JLabel lblDistancia = new JLabel("Distancia en Km");
    lblDistancia.setBounds(230, 40, 100, 25);
    pnlEditarEnvio.add(lblDistancia);

    txtDistancia = new JTextField();
    txtDistancia.setBounds(330, 40, 120, 25);
    pnlEditarEnvio.add(txtDistancia);

    JButton btnGuardar = new JButton("Guardar");
    btnGuardar.setBounds(230, 80, 100, 25);
    btnGuardar.addActionListener(evt -> btnGuardarEnvioClick());

    pnlEditarEnvio.add(btnGuardar);

    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.setBounds(340, 80, 100, 25);
    btnCancelar.addActionListener(evt -> btnCancelarEnvioClick());
    pnlEditarEnvio.add(btnCancelar);

    pnlEditarEnvio.setVisible(false); // oculto al inicio

    // Tabla de envíos
    tblEnvios = new JTable();
    modeloTabla = new DefaultTableModel(null, encabezadosEnvios);
    tblEnvios.setModel(modeloTabla);

    JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

    // Agregar al panel principal
    pnlPrincipal.add(pnlEditarEnvio);
    pnlPrincipal.add(spListaEnvios);

    JScrollPane spEnvios = new JScrollPane(pnlPrincipal);
    spEnvios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    add(spEnvios, BorderLayout.CENTER);
  }

  // Muestra el panel para agregar un nuevo envío
  private void btnAgregarEnvioClick() {
    pnlEditarEnvio.setVisible(true);
    System.out.println("Mostrando panel de edición de envío");
  }

  // Eliminar el envío seleccionado
  private void btnQuitarEnvioClick() {
    int fila = tblEnvios.getSelectedRow();
    if (fila != -1) {
      String codigo = modeloTabla.getValueAt(fila, 1).toString();
      boolean fueEliminado = logistica.retirarEnvio(codigo);
      if (fueEliminado) {
        System.out.println("Eliminado envío con código: " + codigo);
      } else {
        System.out.println("No se encontró envío con código: " + codigo);
      }
      actualizarTabla();
    } else {
      JOptionPane.showMessageDialog(
          this, "Seleccione un envío para eliminar.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
  }

  private void btnGuardarEnvioClick() {
    String codigo = txtNumero.getText();
    String cliente = txtCliente.getText();
    String pesoString = txtPeso.getText();
    String distanciaString = txtDistancia.getText();

    // Validar que los campos estén diligenciados
    if (codigo.isEmpty() || cliente.isEmpty() || pesoString.isEmpty() || distanciaString.isEmpty()) {
      JOptionPane.showMessageDialog(
          this, "Por favor diligencie todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Validar que el código no se repita
    for (Envio e : logistica.getEnvios()) {
      if (e.getCodigo().equals(codigo)) {
        JOptionPane.showMessageDialog(
            this, "El código ya se encuentra registrado.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
    }

    double peso;
    double distancia;
    try {
      peso = Double.parseDouble(pesoString);
        distancia = Double.parseDouble(distanciaString);
      if (peso <= 0 || distancia <= 0) {
        JOptionPane.showMessageDialog(
            this, "Peso y distancia deben ser mayores a cero.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(
          this, "Peso y distancia deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    String tipo = (String) cmbTipo.getSelectedItem();
    Envio envio;
    if (tipo.equals("Terrestre")) {
      envio = new envio.Terrestre(codigo, cliente, peso, distancia);
    } else if (tipo.equals("Aéreo")) {
      envio = new envio.Aereo(codigo, cliente, peso, distancia);
    } else { // Marítimo
      envio = new envio.Maritimo(codigo, cliente, peso, distancia);
    }

    logistica.agregarEnvio(envio);
    System.out.println("Agregando envío con código: " + codigo);
    actualizarTabla();
//    pnlEditarEnvio.setVisible(false);
    limpiarCampos();
  }

  private void actualizarTabla() {
    modeloTabla.setRowCount(0); // Limpiar tabla
    for (Envio envio : logistica.getEnvios()) {
      modeloTabla.addRow(
          new Object[] {
            envio.getClass().getSimpleName(),
            envio.getCodigo(),
            envio.getCliente(),
            envio.getPeso(),
            envio.getDistancia(),
            envio.calcularTarifa()
          });
      System.out.println("Se muestra envío con código: " + envio.getCodigo());
    }
  }

  private void btnCancelarEnvioClick() {
    pnlEditarEnvio.setVisible(false);
    limpiarCampos();
    System.out.println("Cancelando edición de envío");
  }

  private void limpiarCampos() {
    txtNumero.setText("");
    txtCliente.setText("");
    txtPeso.setText("");
    txtDistancia.setText("");
    cmbTipo.setSelectedIndex(0);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new AppGUI().setVisible(true));
  }
}

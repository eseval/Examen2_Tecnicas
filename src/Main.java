public class Main {
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(() -> {
      gui.AppGUI app = new gui.AppGUI();
      app.setVisible(true);
    });
  }
}

package gestion;

import envio.Envio;
import java.util.ArrayList;

public class Logistica {
  private ArrayList<Envio> envios;

  public Logistica() {
    envios = new ArrayList<>();
  }

  public void agregarEnvio(Envio envio) {
    envios.add(envio);
  }

  public boolean retirarEnvio(String codigo) {
    return envios.removeIf(e -> e.getCodigo().equals(codigo));
  }

  public void listarEnvios() {
    for (Envio envio : envios) {
      System.out.println("Código: " + envio.getCodigo());
      System.out.println("Cliente: " + envio.getCliente());
      System.out.println("Medio: " + envio.getClass().getSimpleName());
      System.out.println("Peso (kg): " + envio.getPeso());
      System.out.println("Distancia (km): " + envio.getDistancia());
      System.out.println("Tarifa: $" + envio.calcularTarifa());
      System.out.println("---------------------------");
    }
  }
}

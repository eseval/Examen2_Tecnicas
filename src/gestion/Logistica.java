package gestion;

import envio.Envio;
import java.util.ArrayList;
import java.util.List;

public class Logistica {
  private List<Envio> envios = new ArrayList<>();

  public void agregarEnvio(Envio envio) {
    envios.add(envio);
  }

  public boolean retirarEnvio(String codigo) {
    return envios.removeIf(e -> e.getCodigo().equals(codigo));
  }

  public List<Envio> getEnvios() {
    return envios;
  }
}

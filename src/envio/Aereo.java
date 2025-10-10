package envio;

public class Aereo extends Envio {
  public Aereo(String codigo, String cliente, double peso, double distancia) {
    super(codigo, cliente, peso, distancia);
  }

  @Override
  public double calcularTarifa() {
    return (getDistancia() * 5000) + (getPeso() * 4000);
  }
}

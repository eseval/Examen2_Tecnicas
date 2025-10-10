package envio;

public class Maritimo extends Envio {
  public Maritimo(String codigo, String cliente, double peso, double distancia) {
    super(codigo, cliente, peso, distancia);
  }

  @Override
  public double calcularTarifa() {
    return (getDistancia() * 800) + (getPeso() * 1000);
  }
}

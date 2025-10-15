package servicios;

public class ResultadoEnvioDto {
  private boolean exito;
  private String mensaje;
  private double tarifa;

  public ResultadoEnvioDto(boolean exito, String mensaje, double tarifa) {
    this.exito = exito;
    this.mensaje = mensaje;
    this.tarifa = tarifa;
  }

  public boolean isExito() {
    return exito;
  }

  public String getMensaje() {
    return mensaje;
  }

  public double getTarifa() {
    return tarifa;
  }
}

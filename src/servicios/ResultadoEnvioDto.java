package servicios;

public class ResultadoEnvioDto {
  private boolean exito;
  private String mensaje;
  private String tarifa;

  public ResultadoEnvioDto(boolean exito, String mensaje, String tarifa) {
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

  public String getTarifa() {
    return tarifa;
  }
}

package servicios;

import envio.Envio;
import gestion.Logistica;
import java.util.List;

public class EnvioServicio {
  private Logistica logistica;

  public EnvioServicio(Logistica logistica) {
    this.logistica = logistica;
  }

  public ResultadoEnvioDto agregarEnvio(Envio envio){
    if (existeCodigo(envio.getCodigo())){
      return new ResultadoEnvioDto(false, "El código ya existe", 0);
    }
    logistica.agregarEnvio(envio);
    return new ResultadoEnvioDto(true, "Envío agregado exitosamente", envio.calcularTarifa());
}

  public ResultadoEnvioDto eliminarEnvio(String codigo){
    boolean eliminado = logistica.retirarEnvio(codigo);
    if(eliminado){
      return new ResultadoEnvioDto(true, "Envío eliminado correctamente", 0);
    } else {
      return new ResultadoEnvioDto(false, "No se encontró el envío para eliminar", 0);
    }
  }


  public List<Envio> listarEnvios() {
    return logistica.getEnvios();
  }

  public boolean existeCodigo(String codigo) {
    for (Envio e : logistica.getEnvios()) {
      if (e.getCodigo().equals(codigo)) {
        return true;
      }
    }
    return false;
  }
}

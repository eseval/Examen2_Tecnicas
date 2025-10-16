package servicios;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class MascaraDecimal extends DocumentFilter {
  @Override
  public void replace(
      FilterBypass filtro, int offset, int length, String caracterDigitado, AttributeSet atributos)
      throws BadLocationException {
    Document documento = filtro.getDocument();
    String textoActual = documento.getText(0, documento.getLength());
    String nuevoTexto =
        textoActual.substring(0, offset)
            + (caracterDigitado == null ? "" : caracterDigitado)
            + textoActual.substring(offset + length);

    if (nuevoTexto.isEmpty() || nuevoTexto.matches("[0-9]+[.]?[0-9]*")) {
      super.replace(filtro, offset, length, caracterDigitado, atributos);
    }
  }

  @Override
  public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
    super.remove(fb, offset, length);
  }
}

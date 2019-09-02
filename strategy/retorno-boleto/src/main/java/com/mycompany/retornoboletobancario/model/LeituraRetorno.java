
package com.mycompany.retornoboletobancario.model;

//import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Matheus Cardozo
 */
public interface LeituraRetorno {
    public List<Boleto> lerArquivo(String nomeArquivo);
    //public LocalDateTime converterDatas(String data);
}

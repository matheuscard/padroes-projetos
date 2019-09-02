
package com.mycompany.retornoboletobancario.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Cardozo
 */
public class LeituraRetornoBancoBrasil implements LeituraRetorno{

    @Override
    public List<Boleto> lerArquivo(String nomeArquivo) {
        List<Boleto> boletos = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(nomeArquivo));
            String linha;
            while((linha = reader.readLine()) != null){
                String[] vetor = linha.split(";"); 
                Boleto boleto = new Boleto();
                boleto.setId(Integer.valueOf( vetor[0]) );
                boleto.setCodBanco(Integer.valueOf(vetor[1]));
                boleto.setDataVencimento(converterDatas(vetor[2]));
                boleto.setDataPagamento(converterDatas(vetor[3]));
                boleto.setCpf(vetor[4]);
                boleto.setValor(Double.parseDouble(vetor[5]));
                boleto.setMulta(Double.parseDouble(vetor[6]));
                boleto.setJuros(Double.parseDouble(vetor[7]));
                boleto.setAgencia(vetor[8]);
                boleto.setContaBancaria(vetor[9]);
                boletos.add(boleto);
            }
        } catch (IOException ex) {
            Logger.getLogger(LeituraRetornoBancoBrasil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return boletos;
    }
    
    public LocalDateTime converterDatas(String data) {
        //Divide as informações da data. Ex.: De 03/02/2019, 03 sendo a posição zero.
        String vetorData[] = data.split("/");
        //Inverte as posições para se adequar ao padrão do LocalDateTime. Ex.: De 03/02/2019 para 2019-02-03
        data=vetorData[2].concat("-").concat(vetorData[1])
                                      .concat("-").concat(vetorData[0]);
        data = data.concat(" ").replaceAll(" ", "T").concat("00:00:00.000");
        LocalDateTime localDateTime = LocalDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME);
        return localDateTime;
    }
}

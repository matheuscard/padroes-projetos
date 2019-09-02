
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
public class LeituraRetornoBradesco implements LeituraRetorno{

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
                boleto.setAgencia(vetor[2]);
                boleto.setContaBancaria(vetor[3]);
                
                boleto.setDataVencimento(converterDatas(vetor[4]));
                boleto.setDataPagamento(converterDatas(vetor[5]));
                boleto.setCpf(vetor[6]);
                boleto.setValor(Double.parseDouble(vetor[7]));
                boleto.setMulta(Double.parseDouble(vetor[8]));
                boleto.setJuros(Double.parseDouble(vetor[9]));
                
                
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
        LocalDateTime localDateTime;
        //Verifica se na posicão do ano possui horas.
        if(vetorData[2].length() > 4){
            String[] split = data.replaceAll(" ", "/").split("/");
            data = split[2].concat("-").concat(split[1]).concat("-").concat(split[0]);
            data = data.concat(" ").replaceAll(" ", "T").concat(split[3]).concat(".000");
            localDateTime = LocalDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME);
        }else{
             data=vetorData[2].concat("-").concat(vetorData[1])
                                      .concat("-").concat(vetorData[0]);
             //Uma vez que aqui não possui concatena as horas para se adequar ao LocalDateTime
             data = data.concat(" ").replaceAll(" ", "T").concat("00:00:00.000");
             localDateTime = LocalDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME);
        }          
        return localDateTime;
    }

}

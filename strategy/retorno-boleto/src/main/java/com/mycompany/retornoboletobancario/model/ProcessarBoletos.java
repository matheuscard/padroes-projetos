
package com.mycompany.retornoboletobancario.model;

import java.util.List;

/**
 *
 * @author Matheus Cardozo
 */
public class ProcessarBoletos {
    private LeituraRetorno leitura;

    public LeituraRetorno getLeitura() {
        return leitura;
    }

    public void setLeitura(LeituraRetorno leitura) {
        this.leitura = leitura;
    }

    public ProcessarBoletos(LeituraRetorno leitura) {
        this.leitura = leitura;
    }
    
    
    public void processar(String nomeArquivo){
        
        System.out.println("Boletos:");
        System.out.println("----------------------------------------------------------------------------------");
        List<Boleto> boletos = leitura.lerArquivo(nomeArquivo);
        for (Boleto boleto : boletos) {
            System.out.println(
                  "Id do boleto: "+boleto.getId()
                  +", Data de vencimento: "+boleto.getDataVencimento()
                  +", Data de pagamento: "+ boleto.getDataPagamento()
                  +", CPF: " + boleto.getCpf()
                  +", Valor: "+boleto.getValor()
                  +", Multa: "+boleto.getMulta()
                  +", Juros: "+boleto.getJuros()
                  +", Agência: "+boleto.getAgencia()
                  +", Conta bancária: "+boleto.getContaBancaria());
        }
    }
}

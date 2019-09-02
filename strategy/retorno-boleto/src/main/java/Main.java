
import com.mycompany.retornoboletobancario.model.LeituraRetornoBancoBrasil;
import com.mycompany.retornoboletobancario.model.LeituraRetornoBradesco;
import com.mycompany.retornoboletobancario.model.ProcessarBoletos;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;



/**
 *
 * @author Matheus Cardozo
 */
public class Main {
    public static void main(String[] args) throws URISyntaxException {
        ProcessarBoletos processar = new ProcessarBoletos(new LeituraRetornoBradesco());
        //String nomeArquivo = Principal.class.getResource("arquivobanco.csv").getPath();
        //Tranforma a resource em URI - Foi necessário para lê-los(Boletos)
        URI nomeArquivo = Main.class.getResource("arquivobancobradesco.csv").toURI();
        // Peega o path da URI
        String nomeArq = Paths.get(nomeArquivo).toString();
         
        System.out.println("Lendo o arquivo: "+nomeArq);


        processar.processar(nomeArq);

    }
}

package visao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import projeto_lp2.Chebychev;
import projeto_lp2.Euclidiana;
import projeto_lp2.Imagem;
import projeto_lp2.Knn;
import projeto_lp2.Leitura;
import projeto_lp2.Manhattan;
import projeto_lp2.TratamentoImagem;

public class TelaPrincipalControle {

    @FXML
    private Button botao1;

    @FXML
    private Label lbl1;
    
    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;
    
    @FXML
    private Label lbl4;
    
    @FXML
    private Text text1;

    @FXML
    private TextField txtK;

    @FXML
    private void acaoBotao1(ActionEvent event) throws FileNotFoundException, IOException {
    	int k;
    	try{
    		lbl4.setText("");
    		k =  Integer.parseInt(txtK.getText());
    		TratamentoImagem tratamento = new TratamentoImagem();
	    	String caminho = "C:\\Users\\marti\\OneDrive\\Documentos\\LP2\\knn\\person\\positive-000109-mirror.png";
	    	Leitura leitura = new Leitura("C:\\Users\\marti\\OneDrive\\Documentos\\LP2\\knn\\dataset_2019_1.csv");
			ArrayList<Imagem> imagens = new ArrayList<Imagem>();
			while(leitura.lerLinhas() != 1) {
				Imagem imagem = new Imagem(leitura.getLista(), leitura.getClasse());
				imagens.add(imagem);
			}
	    	Knn a = new Euclidiana();
			Knn b = new Manhattan();
			Knn c = new Chebychev();
			lbl1.setText(a.KnnFunction(k,imagens, tratamento.TratamentodaImagem(caminho)));
			lbl2.setText(b.KnnFunction(k,imagens, tratamento.TratamentodaImagem(caminho)));
			lbl3.setText(c.KnnFunction(k,imagens, tratamento.TratamentodaImagem(caminho)));
    	}catch(NumberFormatException n) {
    		lbl4.setText("Digite um valor inteiro!!!");
    		lbl1.setText("");
    		lbl2.setText("");
    		lbl3.setText("");
    	}
    }
}


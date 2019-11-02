package controle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.opencv.core.CvException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import modelo.Chebychev;
import modelo.Euclidiana;
import modelo.Manhattan;

public class TelaPrincipalControle implements Initializable{

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
    private Label lbl5;
    
    @FXML
    private Label lbl6;
    
    
    @FXML
    private Text text1;

    @FXML
    private TextField txtK;
    
    @FXML
    private Text text2;

    @FXML
    private ChoiceBox<String> choice;

    private ObservableList<String> lista; 
    
    @FXML
    private Text textDataset;

    @FXML
    private TextField txtDataset;

    @FXML
    private Button pesquisar;

    @FXML
    private Text textImagem;

    @FXML
    private TextField txtImagem;

    @FXML
    private Button pesquisar2;

    
    @FXML
    private void acaoBotao1(ActionEvent event) throws FileNotFoundException, IOException {
    	String metrica = choice.getSelectionModel().getSelectedItem();
    	int k;
    	try{
    		lbl4.setText("");
    		lbl5.setText("");
    		lbl6.setText("");
    		k =  Integer.parseInt(txtK.getText());
    		TratamentoImagem tratamento = new TratamentoImagem();
	    	String caminhoImagem = txtImagem.getText();
	    	String caminhoLeitura = txtDataset.getText();
	    	Leitura leitura = new Leitura(caminhoLeitura);
			ArrayList<Imagem> imagens = new ArrayList<Imagem>();
			while(leitura.lerLinhas() != 1) {
				Imagem imagem = new Imagem(leitura.getLista(), leitura.getClasse());
				imagens.add(imagem);
			}
	    	Knn a = new Euclidiana();
			Knn b = new Manhattan();
			Knn c = new Chebychev();
			switch(metrica) {
				case "Euclidiana":  lbl1.setText("Métrica utilizada: Euclidiana");
									lbl2.setText(a.KnnFunction(k,imagens, tratamento.TratamentodaImagem(caminhoImagem)));
									break;
				case "Manhattan": 	lbl1.setText("Métrica utilizada: Manhattan");
									lbl2.setText(b.KnnFunction(k,imagens, tratamento.TratamentodaImagem(caminhoImagem)));
									break;
				case "Chebychev":   lbl1.setText("Métrica utilizada: Chebychev");
									lbl2.setText(c.KnnFunction(k,imagens, tratamento.TratamentodaImagem(caminhoImagem)));
									break;
				default: lbl3.setText("Selecione uma métrica!!!");
			}
    	}catch(NumberFormatException n) {
    		lbl4.setText("Digite um valor inteiro!!!");
    		lbl1.setText("");
    		lbl2.setText("");
    		lbl3.setText("");
    		lbl5.setText("");
    		lbl6.setText("");
    	}catch(FileNotFoundException f) {
    		lbl5.setText("Arquivo não encontrado!!!");
    		lbl1.setText("");
    		lbl2.setText("");
    		lbl3.setText("");
    		lbl4.setText("");
    		lbl6.setText("");
    	}catch(NullPointerException n) {
    		lbl1.setText("Selecione a métrica!!!");
    		lbl5.setText("");
    		lbl2.setText("");
    		lbl3.setText("");
    		lbl4.setText("");
    		lbl6.setText("");
    	}catch(CvException c) {
    		lbl6.setText("Imagem não encontrada!!!");
    		lbl1.setText("");
    		lbl2.setText("");
    		lbl3.setText("");
    		lbl4.setText("");
    		lbl5.setText("");
    	}
    }

    @FXML
    public void selecionarDataset(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Arquivos CSV", "*.csv"));
    	File f = fc.showOpenDialog(null);
    	txtDataset.setText(f.getAbsolutePath());
    }

    @FXML
    public void selecionarImagem(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Arquivos PNG", "*.png"));
    	File f = fc.showOpenDialog(null);
    	txtImagem.setText(f.getAbsolutePath());
    }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		lista = FXCollections.observableArrayList("Euclidiana", "Chebychev", "Manhattan");
		choice.setItems(lista);
	}
}


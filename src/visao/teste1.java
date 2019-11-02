package visao;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class teste1 extends Application{
	public static void main(String[] args) {
			launch(args);		
		}
	
	
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane root = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

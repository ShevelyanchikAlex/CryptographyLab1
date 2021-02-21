package encryption_decryption_methods;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CryptographyLab1");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
//        System.out.println(VigenereEncrypt.encryption("КРИПТОг к","Код"));
//        System.out.println(VigenereEncrypt.decryption("ХЯМЪГЧТЭ","Код"));
//        ColumnMethod.encryption("Постоянно занятой человек", "букварь");
//        ColumnMethod.decryption("ОНЛ.ПНОЕТАЕ.СЗЧ.ЯЯО.ООЙКНТВ.", "букварь");

        System.out.println(PlayfairEncrypt.encryption("itISverys"));
        System.out.println(PlayfairEncrypt.decryption("KPKQRMYPQZ"));
    }


}

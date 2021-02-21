package encryption_decryption_methods;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button openFileBtn;

    @FXML
    private RadioButton columnEncryptRadioBtn;

    @FXML
    private RadioButton vigenereEncryptRadioBtn;

    @FXML
    private RadioButton plaifairEncryptRadioBtn;

    @FXML
    private TextArea plainTextArea;

    @FXML
    private TextArea cipherTextArea;

    @FXML
    private TextField keywordInputTextField;

    @FXML
    private Button encryptBtn;

    @FXML
    private Button decryptBtn;

    @FXML
    private ToggleGroup selectedEncryptionMethod;

    @FXML
    void initialize() {
    }

    @FXML
    void decryption() {
        switch (getEncryptionMethod()) {
            case 1 -> plainTextArea.setText(ColumnMethodEncryption.decryption(cipherTextArea.getText(), keywordInputTextField.getText()));
            case 2 -> plainTextArea.setText(VigenereEncrypt.decryption(cipherTextArea.getText(), keywordInputTextField.getText()));
            case 3 -> plainTextArea.setText(PlayfairEncrypt.decryption(cipherTextArea.getText()));
            default -> System.out.println("Decryption method not selected");
        };
    }

    @FXML
    void encryption(){
        switch (getEncryptionMethod()) {
            case 1 -> cipherTextArea.setText(ColumnMethodEncryption.encryption(plainTextArea.getText(), keywordInputTextField.getText()));
            case 2 -> cipherTextArea.setText(VigenereEncrypt.encryption(plainTextArea.getText(), keywordInputTextField.getText()));
            case 3 -> cipherTextArea.setText(PlayfairEncrypt.encryption(plainTextArea.getText()));
            default -> System.out.println("Decryption method not selected");
        };
    }


    private int getEncryptionMethod() {
        int selectedEncryptionMethod = 0;
        if (columnEncryptRadioBtn.isSelected()) selectedEncryptionMethod = 1;
        else if (vigenereEncryptRadioBtn.isSelected()) selectedEncryptionMethod = 2;
        else if (plaifairEncryptRadioBtn.isSelected()) selectedEncryptionMethod = 3;
        return selectedEncryptionMethod;
    }
}

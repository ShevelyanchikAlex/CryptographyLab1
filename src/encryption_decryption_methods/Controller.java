package encryption_decryption_methods;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button openFileBtn;

    @FXML
    private MenuItem openToEncryptMenuItem;

    @FXML
    private MenuItem openToDecryptMenuItem;

    @FXML
    private MenuItem savePlainTextMenuItem;

    @FXML
    private MenuItem saveCipherTextMenuItem;


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
        String filterKeyword = getOnlyRussianCharacters(keywordInputTextField.getText());
        switch (getEncryptionMethod()) {
            case 1:
                switch (checkKeywordInputText(filterKeyword)) {
                    case 1:
                        if(ColumnMethodEncryption.getOnlyRussianCharacters(cipherTextArea.getText()).equals("")){
                            showAlertMessage("Incorrect text in cipherTextArea", "The cipherText does not contain any character of the Russian alphabet!", "For the Column method enter a cipherText that containing Russian characters.");
                        }else{
                            plainTextArea.setText(ColumnMethodEncryption.decryption(cipherTextArea.getText(), filterKeyword));
                            saveToFile(plainTextArea.getText(), "columnMethodPlainText#Key{" + filterKeyword + "}#.txt");
                        }
                        break;
                    case 0:
                        showAlertMessage("Incorrect keyword", "The keyword does not contain any character of the Russian alphabet!", "For the Column method enter a keyword that containing Russian characters.");
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (checkKeywordInputText(filterKeyword)) {
                    case 1:
                        if(VigenereEncrypt.getOnlyRussianCharacters(cipherTextArea.getText()).equals("")){
                            showAlertMessage("Incorrect text in cipherTextArea", "The cipherText does not contain any character of the Russian alphabet!", "For the Vigenere method enter a cipherText that containing Russian characters.");
                        }else {
                            plainTextArea.setText(VigenereEncrypt.decryption(cipherTextArea.getText(), filterKeyword));
                            saveToFile(plainTextArea.getText(), "vigenereMethodPlainText#Key{" + filterKeyword + "}#.txt");
                        }
                        break;
                    case 0:
                        showAlertMessage("Incorrect keyword", "The keyword does not contain any character of the Russian alphabet!", "For the Vigenere method enter a keyword that containing Russian characters.");
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                if (keywordInputTextField.getText() == null || keywordInputTextField.getText().equals("")) {
                    if(PlayfairEncrypt.getOnlyEnglishCharacters(cipherTextArea.getText()).equals("")){
                        showAlertMessage("Incorrect text in cipherTextArea", "The cipherText does not contain any character of the English alphabet!", "For the Playfair method enter a cipherText that containing English characters.");
                    }else{
                        plainTextArea.setText(PlayfairEncrypt.decryption(cipherTextArea.getText()));
                        saveToFile(plainTextArea.getText(), "playfairMethodPlainText#.txt");
                    }
                } else {
                    showAlertMessage("Error", "You entered a keyword!", "For the Playfair method you don't have to enter the key.");
                }
                break;
            default:
                showAlertMessage("Method selection error", "You have not chosen an encryption/decryption method!", "To select a method, click on the radioButton with the desired encryption/decryption method.");
                break;
        }
    }

    @FXML
    void encryption() {
        String filterKeyword = getOnlyRussianCharacters(keywordInputTextField.getText());
        switch (getEncryptionMethod()) {
            case 1:
                switch (checkKeywordInputText(filterKeyword)) {
                    case 1:
                        if(ColumnMethodEncryption.getOnlyRussianCharacters(plainTextArea.getText()).equals("")){
                            showAlertMessage("Incorrect text in plainTextArea", "The plainText does not contain any character of the Russian alphabet!", "For the Column method enter a plainText that containing Russian characters.");
                        }else {
                            cipherTextArea.setText(ColumnMethodEncryption.encryption(plainTextArea.getText(), filterKeyword));
                            saveToFile(cipherTextArea.getText(), "columnMethodCipherText#Key{" + filterKeyword + "}#.txt");
                        }
                        break;
                    case 0:
                        showAlertMessage("Incorrect keyword", "The keyword does not contain any character of the Russian alphabet!", "For the Column method enter a keyword that containing Russian characters.");
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (checkKeywordInputText(filterKeyword)) {
                    case 1:
                        if(VigenereEncrypt.getOnlyRussianCharacters(plainTextArea.getText()).equals("")){
                            showAlertMessage("Incorrect text in plainTextArea", "The plainText does not contain any character of the Russian alphabet!", "For the Vigenere method enter a plainText that containing Russian characters.");
                        }else {
                            cipherTextArea.setText(VigenereEncrypt.encryption(plainTextArea.getText(), filterKeyword));
                            saveToFile(cipherTextArea.getText(), "vigenereMethodCipherText#Key{" + filterKeyword + "}#.txt");
                        }
                        break;
                    case 0:
                        showAlertMessage("Incorrect keyword", "The keyword does not contain any character of the Russian alphabet!", "For the Vigenere method enter a keyword that containing Russian characters.");
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                if (keywordInputTextField.getText() == null || keywordInputTextField.getText().equals("")) {
                    if(PlayfairEncrypt.getOnlyEnglishCharacters(plainTextArea.getText()).equals("")){
                        showAlertMessage("Incorrect text in plainTextArea", "The plainText does not contain any character of the English alphabet!", "For the Playfair method enter a plainText that containing English characters.");
                    }else {
                        cipherTextArea.setText(PlayfairEncrypt.encryption(plainTextArea.getText()));
                        saveToFile(cipherTextArea.getText(), "playfairMethodCipherText#.txt");
                    }
                } else {
                    showAlertMessage("Error", "You entered a keyword!", "For the Playfair method you don't have to enter the key.");
                }
                break;
            default:
                showAlertMessage("Method selection error", "You have not chosen an encryption/decryption method!", "To select a method, click on the radioButton with the desired encryption/decryption method.");
                break;
        }
        ;
    }

    static int checkKeywordInputText(String keywordInputText) {
        if (keywordInputText.equals("")) {
            return 0;
        } else {
            return 1;
        }
    }

    static String getOnlyRussianCharacters(String inputKeyword) {
        StringBuilder rusInputKeyword = new StringBuilder();
        for (int i = 0; i < inputKeyword.length(); i++) {
            if ((inputKeyword.charAt(i) >= 'а' && inputKeyword.charAt(i) <= 'я')
                    || (inputKeyword.charAt(i) >= 'А' && inputKeyword.charAt(i) <= 'Я')
                    || inputKeyword.charAt(i) == 'Ё' || inputKeyword.charAt(i) >= 'ё') {
                rusInputKeyword.append(inputKeyword.charAt(i));
            }
        }
        return rusInputKeyword.toString();
    }



    private int getEncryptionMethod() {
        int selectedEncryptionMethod = 0;
        if (columnEncryptRadioBtn.isSelected()) selectedEncryptionMethod = 1;
        else if (vigenereEncryptRadioBtn.isSelected()) selectedEncryptionMethod = 2;
        else if (plaifairEncryptRadioBtn.isSelected()) selectedEncryptionMethod = 3;
        return selectedEncryptionMethod;
    }

    void showAlertMessage(String titleOfAlert, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleOfAlert);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


    private void getFileContent(int textArea) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder outStr = new StringBuilder();
            String lineStr;
            while ((lineStr = reader.readLine()) != null) {
                outStr.append(lineStr);
            }
            switch (textArea) {
                case 1 -> plainTextArea.setText(outStr.toString());
                case 2 -> cipherTextArea.setText(outStr.toString());
                default -> System.out.println("Error selected textArea!");
            }
            reader.close();
        }
    }

    private void setContentToFile(int textArea) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            switch (textArea) {
                case 1 -> saveToFile(plainTextArea.getText(), file.getName());
                case 2 -> saveToFile(cipherTextArea.getText(), file.getName());
                default -> System.out.println("Error selected textArea!");
            }
        }
    }

    @FXML
    void chooseSavePlainTextAreaToFile() {
        setContentToFile(1);
    }

    @FXML
    void chooseSaveCipherTextAreaToFile() {
        setContentToFile(2);
    }


    @FXML
    void chooseOpenFileToPlainTextArea() {
        try {
            clearTextAreas();
            getFileContent(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void chooseOpenFileToCipherTextArea() {
        try {
            clearTextAreas();
            getFileContent(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearTextAreas() {
        plainTextArea.clear();
        cipherTextArea.clear();
    }


    private void saveToFile(String textMessage, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));) {
            writer.write(textMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

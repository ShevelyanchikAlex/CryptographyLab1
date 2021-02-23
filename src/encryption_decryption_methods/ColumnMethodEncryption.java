package encryption_decryption_methods;

import java.util.Arrays;

public class ColumnMethodEncryption {

    static String encryption(String inputPlainText, String inputKeyword) {

        String rusInputPlainText = getOnlyRussianCharacters(inputPlainText);
        StringBuilder plainTextMessage = new StringBuilder(rusInputPlainText.toUpperCase().replace(" ", ""));

        //check keyword
        String keyword = inputKeyword.toUpperCase();
        int[] keywordNumberArr = transformKeywordToKeywordNumberArr(keyword);

        printKeyWordWithHisKeywordNumberArr(keyword, keywordNumberArr);

        addComplementaryCharacters(plainTextMessage, keyword);

        int numberOfRows = plainTextMessage.length() / keyword.length();

        char[][] plainTextMessageArr = transformPlainMessageToPlainMessageArr(numberOfRows, keyword, plainTextMessage);


        printPlainTextMessageArr(numberOfRows, keyword, plainTextMessageArr);

        int[] numberOrderArr = getColumnOrder(keyword, keywordNumberArr);
        //
        System.out.println("Location of numbers: " + Arrays.toString(numberOrderArr));
        System.out.println();
        //

        StringBuilder cipherTextMessage = transformPlainTextMessageArrToCipherTextMessage(keyword, numberOrderArr, numberOfRows, plainTextMessageArr);
        System.out.println("Cipher Text: " + cipherTextMessage);

        return cipherTextMessage.toString();

    }

    static String getOnlyRussianCharacters(String inputPlainText) {
        StringBuilder rusInputPlainText = new StringBuilder();
        for (int i = 0; i < inputPlainText.length(); i++) {
            if ((inputPlainText.charAt(i) >= 'а' && inputPlainText.charAt(i) <= 'я')
                    || (inputPlainText.charAt(i) >= 'А' && inputPlainText.charAt(i) <= 'Я')
                    || inputPlainText.charAt(i) == 'Ё' || inputPlainText.charAt(i) >= 'ё') {
                rusInputPlainText.append(inputPlainText.charAt(i));
            }
        }
        return rusInputPlainText.toString();
    }


    static void printPlainTextMessageArr(int numberOfRows, String keyword, char[][] plainTextMessageArr) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                System.out.print(plainTextMessageArr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    static void printKeyWordWithHisKeywordNumberArr(String keyword, int[] keywordNumberArr) {
        for (int i = 0, j = 1; i < keyword.length(); i++, j++) {
            System.out.print(keyword.substring(i, j) + " ");
        }
        System.out.println();

        for (int i : keywordNumberArr) {
            System.out.print(i + " ");
        }

        System.out.println();
    }

    static void addComplementaryCharacters(StringBuilder plainTextMessage, String keyword) {
        int complementaryCharacters = plainTextMessage.length() % keyword.length();
        int countOfComplementaryCharacters = keyword.length() - complementaryCharacters;

        if (complementaryCharacters != 0) {
            plainTextMessage.append(".".repeat(Math.max(0, countOfComplementaryCharacters)));
        }
    }

    static char[][] transformPlainMessageToPlainMessageArr(int numberOfRows, String keyword, StringBuilder plainTextMessage) {
        char[][] plainTextMessageArr = new char[numberOfRows][keyword.length()];

        int currentCharValue = 0;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                plainTextMessageArr[i][j] = plainTextMessage.charAt(currentCharValue++);
            }
        }
        return plainTextMessageArr;
    }


    private static int[] getColumnOrder(String keyword, int[] keywordNumberArr) {
        int[] columnOrderArr = new int[keyword.length()];
        int currentIndex = 0;
        for (int i = 1; i < keyword.length() + 1; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                if (keywordNumberArr[j] == i) {
                    columnOrderArr[currentIndex++] = j;
                }
            }
        }
        return columnOrderArr;
    }

    static int[] transformKeywordToKeywordNumberArr(String keyword) {
        String alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        int[] keywordNumberArr = new int[keyword.length()];
        int currentNumber = 1;

        for (int i = 0; i < alphabet.length(); i++) {
            for (int j = 0; j < keyword.length(); j++) {
                if (alphabet.charAt(i) == keyword.charAt(j)) {
                    keywordNumberArr[j] = currentNumber++;
                }
            }
        }
        return keywordNumberArr;
    }

    static StringBuilder transformPlainTextMessageArrToCipherTextMessage(String keyword, int[] numberOrderArr, int numberOfRows, char[][] plainTextMessageArr) {
        StringBuilder cipherTextMessage = new StringBuilder();
        for (int i = 0, k = 0; i < keyword.length(); i++, k++) {
            int d;
            if (k == keyword.length()) {
                break;
            } else {
                d = numberOrderArr[k];
            }
            for (int j = 0; j < numberOfRows; j++) {
                cipherTextMessage.append(plainTextMessageArr[j][d]);
            }
        }
        return cipherTextMessage;
    }


    static String decryption(String inputCipherTextMessage, String inputKeyword) {
        String rusInputCipherText = getOnlyRussianCharacters(inputCipherTextMessage);
        String cipherTextMessage = rusInputCipherText.toUpperCase().replace(" ", "");
        String keyword = inputKeyword.toUpperCase();

        int numberOfRows = cipherTextMessage.length() / keyword.length();

        int[] keywordNumberArr = transformKeywordToKeywordNumberArr(keyword);
        int[] columnOrderOfKeyword = getColumnOrder(keyword, keywordNumberArr);

        System.out.println(Arrays.toString(columnOrderOfKeyword));

        char[][] cipherTextMessageMatrix = transformCipherTextMessageToCipherTextMessageMatrix(numberOfRows, keyword, cipherTextMessage, columnOrderOfKeyword);


        StringBuilder plainTextMessage = transformCipherTextMessageMatrixToPlainTextMessage(numberOfRows, keyword, cipherTextMessageMatrix);
        System.out.println("Plain Text: " + plainTextMessage);

        return plainTextMessage.toString();
    }


    static char[][] transformCipherTextMessageToCipherTextMessageMatrix(int numberOfRows, String keyword, String cipherTextMessage, int[] columnOrderOfKeyword) {
        char[][] cipherTextMessageMatrix = new char[numberOfRows][keyword.length()];
        for (int currentCharIndex = 0, k = 0; currentCharIndex < cipherTextMessage.length(); k++) {
            int currentColumn = 0;
            if (k == keyword.length()) {
                k = 0;
            } else {
                currentColumn = columnOrderOfKeyword[k];
            }
            for (int j = 0; j < numberOfRows; j++) {
                cipherTextMessageMatrix[j][currentColumn] = cipherTextMessage.charAt(currentCharIndex++);
            }
        }
        return cipherTextMessageMatrix;
    }

    static StringBuilder transformCipherTextMessageMatrixToPlainTextMessage(int numberOfRows, String keyword, char[][] cipherTextMessageMatrix) {
        StringBuilder plainTextMessage = new StringBuilder();
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                plainTextMessage.append(cipherTextMessageMatrix[i][j]);
            }
        }
        return plainTextMessage;
    }

}

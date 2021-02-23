package encryption_decryption_methods;

public class VigenereEncrypt {
    static final String RUS_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    static final int POWER_OF_RUS_ALPHABET = RUS_ALPHABET.length();


    static String encryption(String plainTextMessage, String keyword) {
        String upperPlainTextMessage = getOnlyRussianCharacters(plainTextMessage).toUpperCase();
        String keywordWithPlainTextMessage = transformKeywordToKeywordWithPlainTextMessage(upperPlainTextMessage, keyword);
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < upperPlainTextMessage.length(); i++) {
            encryptedText.append(RUS_ALPHABET.charAt((RUS_ALPHABET.indexOf(upperPlainTextMessage.charAt(i)) +
                    RUS_ALPHABET.indexOf(keywordWithPlainTextMessage.charAt(i))) % POWER_OF_RUS_ALPHABET));
        }
        return encryptedText.toString();
    }

    static String decryption(String encryptedText, String keyword) {
        String upperCipherTextMessage = getOnlyRussianCharacters(encryptedText).toUpperCase();
        StringBuilder decryptedText = new StringBuilder();
        StringBuilder dynamicKeyword = new StringBuilder();
        dynamicKeyword.append(keyword.toUpperCase());

        for (int i = 0; i < upperCipherTextMessage.length(); i++) {
            decryptedText.append(RUS_ALPHABET.charAt((RUS_ALPHABET.indexOf(upperCipherTextMessage.charAt(i)) + POWER_OF_RUS_ALPHABET -
                    RUS_ALPHABET.indexOf(dynamicKeyword.charAt(i))) % POWER_OF_RUS_ALPHABET));
            dynamicKeyword.append(decryptedText.charAt(i));
        }
        return decryptedText.toString();
    }


    static String transformKeywordToKeywordWithPlainTextMessage(String plainTextMessage, String keyword) {
        String upperKeyword = keyword.toUpperCase();
        StringBuilder keywordWithPlainTextMessage = new StringBuilder();
        int currentKeywordIndex = 0;
        int currentPlainTextMessageIndex = 0;

        for (int i = 0; i < plainTextMessage.length(); i++) {
            if (currentKeywordIndex != upperKeyword.length()) {
                keywordWithPlainTextMessage.append(upperKeyword.charAt(currentKeywordIndex++));
            } else {
                keywordWithPlainTextMessage.append(plainTextMessage.charAt(currentPlainTextMessageIndex++));
            }
        }

//        System.out.println(keywordWithPlainTextMessage);
        return keywordWithPlainTextMessage.toString();
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



}

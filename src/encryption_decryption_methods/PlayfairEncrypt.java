package encryption_decryption_methods;

public class PlayfairEncrypt {

    private static final char[][] cipherTable = {
            {'C', 'R', 'Y', 'P', 'T'},
            {'O', 'G', 'A', 'H', 'B'},
            {'D', 'E', 'F', 'I', 'K'},
            {'L', 'M', 'N', 'Q', 'S'},
            {'U', 'V', 'W', 'X', 'Z'}
    };


    public static String encryption(String inputPlainText) {
        String engInputPlainText = getOnlyEnglishCharacters(inputPlainText);
        StringBuilder toEncrypt = transformInputStrToCorrectStr(engInputPlainText);
        for (int i = 0; i < toEncrypt.length() - 1; i += 2) {
            int[] firstCharPos = getPosition(toEncrypt.charAt(i));
            int[] secondCharPos = getPosition(toEncrypt.charAt(i + 1));
            char toReplaceFirst;
            char toReplaceSecond;
            if (firstCharPos[0] == secondCharPos[0]) {
                toReplaceFirst = cipherTable[firstCharPos[0]][(firstCharPos[1] + 1) % cipherTable.length];
                toReplaceSecond = cipherTable[secondCharPos[0]][(secondCharPos[1] + 1) % cipherTable.length];
            } else if (firstCharPos[1] == secondCharPos[1]) {
                toReplaceFirst = cipherTable[(firstCharPos[0] + 1) % cipherTable.length][firstCharPos[1]];
                toReplaceSecond = cipherTable[(secondCharPos[0] + 1) % cipherTable.length][secondCharPos[1]];
            } else {
                toReplaceFirst = cipherTable[firstCharPos[0]][secondCharPos[1]];
                toReplaceSecond = cipherTable[secondCharPos[0]][firstCharPos[1]];
            }
            toEncrypt.replace(i, i + 2, String.valueOf(toReplaceFirst) + toReplaceSecond);
        }
        return toEncrypt.toString();
    }

    private static StringBuilder transformInputStrToCorrectStr(String inputPlainText) {
        StringBuilder correctStr = new StringBuilder(inputPlainText.toUpperCase());
        int currentIndex = 0;
        while (currentIndex < correctStr.length() - 1) {
            if (correctStr.charAt(currentIndex) == 'J') {
                correctStr.insert(currentIndex + 1, 'I');
                correctStr.deleteCharAt(currentIndex);
            }
            if (correctStr.charAt(currentIndex) == correctStr.charAt(currentIndex + 1)) {
                correctStr.insert(currentIndex + 1, 'X');
                currentIndex++;
            }
            currentIndex++;
        }
        if (correctStr.length() % 2 != 0) {
            correctStr.append('X');
        }
        return correctStr;
    }


    public static String decryption(String encryptedText) {
        String engInputEncryptedText = getOnlyEnglishCharacters(encryptedText);
        StringBuilder toDecrypt = transformInputStrToCorrectStr(engInputEncryptedText);
        for (int i = 0; i < toDecrypt.length() - 1; i += 2) {
            int[] firstCharPos = getPosition(toDecrypt.charAt(i));
            int[] secondCharPos = getPosition(toDecrypt.charAt(i + 1));
            char toReplaceFirst;
            char toReplaceSecond;
            if (firstCharPos[0] == secondCharPos[0]) {
                toReplaceFirst = cipherTable[firstCharPos[0]][(firstCharPos[1] == 0 ? cipherTable.length - 1 : firstCharPos[1] - 1)];
                toReplaceSecond = cipherTable[secondCharPos[0]][(secondCharPos[1] == 0 ? cipherTable.length - 1 : secondCharPos[1] - 1)];
            } else if (firstCharPos[1] == secondCharPos[1]) {
                toReplaceFirst = cipherTable[(firstCharPos[0] == 0 ? cipherTable[0].length - 1 : firstCharPos[0] - 1)][firstCharPos[1]];
                toReplaceSecond = cipherTable[(secondCharPos[0] == 0 ? cipherTable[0].length - 1 : secondCharPos[0] - 1)][secondCharPos[1]];
            } else {
                toReplaceFirst = cipherTable[firstCharPos[0]][secondCharPos[1]];
                toReplaceSecond = cipherTable[secondCharPos[0]][firstCharPos[1]];
            }
            toDecrypt.replace(i, i + 2, String.valueOf(toReplaceFirst) + toReplaceSecond);
        }
        return toDecrypt.toString();
    }

    private static int[] getPosition(char ch) {
        for (int i = 0; i < cipherTable.length; i++) {
            for (int j = 0; j < cipherTable[i].length; j++) {
                if (cipherTable[i][j] == ch)
                    return new int[]{i, j};
            }
        }
        return new int[]{0, 0};
    }

    static String getOnlyEnglishCharacters(String inputPlainText) {
        StringBuilder engInputPlainText = new StringBuilder();
        for (int i = 0; i < inputPlainText.length(); i++) {
            if ((inputPlainText.charAt(i) >= 'a' && inputPlainText.charAt(i) <= 'z')
                    || (inputPlainText.charAt(i) >= 'A' && inputPlainText.charAt(i) <= 'Z')) {
                engInputPlainText.append(inputPlainText.charAt(i));
            }
        }
        return engInputPlainText.toString();
    }

}

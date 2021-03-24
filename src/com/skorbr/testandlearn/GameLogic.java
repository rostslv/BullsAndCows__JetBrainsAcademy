package com.skorbr.testandlearn;

import java.util.Random;
import java.util.Scanner;

class GameLogic {

    private int secretCodeLength;
    private int secretCodeSymbolsCount;
    private StringBuilder secretCode = new StringBuilder();
    public boolean isGameStart = false;

    GameLogic(int secretCodeLength, int secretCodeSymbolsCount) {
        this.secretCodeLength = secretCodeLength;
        this.secretCodeSymbolsCount = secretCodeSymbolsCount;
        generateSecretCode(secretCodeLength, secretCodeSymbolsCount);
    }

    private void generateSecretCode(int secretCodeLength, int secretCodeSymbolsCount) {
        String possibleSymbolsAll = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder possibleSymbolsCode = new StringBuilder();
        Random random = new Random();

        if (secretCodeLength <= secretCodeSymbolsCount) {
            for (int i = 0; i < secretCodeSymbolsCount; i++) { // набираем нужное кол-во символов
                possibleSymbolsCode.append(possibleSymbolsAll.charAt(i));
            }

            if (secretCodeSymbolsCount <= 10) { // вывод сообщения о готовности начать игру
                System.out.printf("The secret is prepared: ");
                for (int i = 0; i < secretCodeLength; i++) {
                    System.out.print("*");
                }
                System.out.printf(" (0-%s).", possibleSymbolsCode.charAt(possibleSymbolsCode.length() - 1));
            } else {
                System.out.printf("The secret is prepared: ");
                for (int i = 0; i < secretCodeLength; i++) {
                    System.out.print("*");
                }
                System.out.printf(" (0-9), (a-%s).", possibleSymbolsCode.charAt(possibleSymbolsCode.length() - 1));
            }

            for (int i = 0; i < secretCodeLength; i++) { // создаем секретный код
                int rnd = random.nextInt(possibleSymbolsCode.length());
                secretCode.append(possibleSymbolsCode.charAt(rnd));
                possibleSymbolsCode.deleteCharAt(rnd);
            }
            isGameStart = true;
        } else {
            System.out.printf("Error: it's not possible to generate " +
                    "a code with a length of %d with %d unique symbols.", secretCodeLength, secretCodeSymbolsCount);
        }
    }

    void checkGameState() {
        isGameStart = true;
        char[] secretCodeInt = new char[secretCode.length()];
        for (int i = 0; i < secretCodeInt.length; i++) {
            secretCodeInt[i] = secretCode.charAt(i);
        }
        Scanner scanner = new Scanner(System.in);
        int countBulls = 0;
        int countCows = 0;
        String codePlayer = scanner.next();
        for (int i = 0; i < codePlayer.length(); i++) {
            char temp = codePlayer.charAt(i);
            try {
                if (temp == secretCodeInt[i]) { // проверка на быков
                    countBulls++;
                    continue;
                }
                for (int j = 0; j < secretCodeInt.length; j++) { // проверка на коров
                    if (temp == secretCodeInt[j]) {
                        countCows++;
                    }
                }
            } catch (Exception e) {
                System.out.printf("Error: %s isn't a valid number.", codePlayer);
            }
        }
        if (countBulls == 0 && countCows == 0) {
            System.out.print("Grade: None. ");
        } else if (countBulls > 0 && countCows == 0) {
            System.out.printf("Grade: %d bull(s). ", countBulls);
            if (countBulls == secretCodeInt.length) {
                System.out.println("\nCongratulations! You guessed the secret code.");
                isGameStart = false;
            }
        } else if (countBulls == 0 && countCows > 0) {
            System.out.printf("Grade: %d cow(s). ", countCows);
        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s). ", countBulls, countCows);
        }
    }
}

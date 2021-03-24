package com.skorbr.testandlearn;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String targetLength = scanner.nextLine();
        try {
            int temp = Integer.parseInt(targetLength);
            if (temp > 36 || temp < 1) {
                System.out.println("Error: maximum length is 36, minimum length is 1");
            } else {
                try {
                    System.out.println("Input the number of possible symbols in the code:");
                    String targetCountSymbols = scanner.nextLine();
                    int tempS = Integer.parseInt(targetCountSymbols);
                    if (tempS > 36 || tempS < 1) {
                        System.out.println("Error: maximum number of possible symbols in the code is 36, minimum is 1 (0-9, a-z).");
                    } else {
                        GameLogic gameLogic = new GameLogic(Integer.parseInt(targetLength), Integer.parseInt(targetCountSymbols));
                        if (gameLogic.isGameStart) {
                            System.out.print("\nOkay, let's start a game!");
                            int turnCounter = 1;
                            while (gameLogic.isGameStart) {
                                System.out.printf("%nTurn %d:%n", turnCounter);
                                gameLogic.checkGameState();
                                turnCounter++;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.printf("Error: %s isn't a valid number.", targetLength);
                }
            }
        } catch (Exception e) {
            System.out.printf("Error: %s isn't a valid number.", targetLength);
        }



    }
}

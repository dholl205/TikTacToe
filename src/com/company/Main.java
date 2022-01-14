package com.company;

import java.util.*;

public class Main {
    static ArrayList<Integer> playerPossitions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPossitions = new ArrayList<Integer>();
    static Boolean a = true;


    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };
        while (a) {
            printBoard(gameBoard);
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter place (1-9) > ");
            Integer pos = scan.nextInt();
            while (playerPossitions.contains(pos) || cpuPossitions.contains(pos)) {
                System.out.println("There is already a piece there.");
                System.out.println("Enter place (1-9) > ");
                pos = scan.nextInt();
            }
            checkWinn();
            placement(gameBoard, pos, "player");
            if (a) {
                Random rand = new Random();
                int cpuPos = rand.nextInt(9) + 1;
                while (playerPossitions.contains(cpuPos) || cpuPossitions.contains(cpuPos)) {
                    rand = new Random();
                    cpuPos = rand.nextInt(9) + 1;
                }
                placement(gameBoard, cpuPos, "cpu");
                checkWinn();
            }
        }
    }

    public static void placement(char[][] gameBoard, Integer pos, String user) {
        char symb = '?';
        if (user.equals("player")) {
            symb = 'X';
            playerPossitions.add(pos);
        } else if (user.equals("cpu")) {
            symb = '0';
            cpuPossitions.add(pos);
        }
        switch (pos) {
            case 1:
                gameBoard[0][0] = symb;
                break;
            case 2:
                gameBoard[0][2] = symb;
                break;
            case 3:
                gameBoard[0][4] = symb;
                break;
            case 4:
                gameBoard[2][0] = symb;
                break;
            case 5:
                gameBoard[2][2] = symb;
                break;
            case 6:
                gameBoard[2][4] = symb;
                break;
            case 7:
                gameBoard[4][0] = symb;
                break;
            case 8:
                gameBoard[4][2] = symb;
                break;
            case 9:
                gameBoard[4][4] = symb;
                break;
            default:
                break;
        }
    }

    public static void checkWinn() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List diag1 = Arrays.asList(1, 5, 9);
        List diag2 = Arrays.asList(7, 5, 3);
        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(diag1);
        winningConditions.add(diag2);

        for (List l : winningConditions) {
            if (playerPossitions.containsAll(l)) {
                a = false;
                System.out.println("Congrats! You won!");
            } else if (cpuPossitions.containsAll(l)) {
                a = false;
                System.out.println("Mission Failed! We'll get 'im next time.");
            } else if (playerPossitions.size() + cpuPossitions.size() == 9) {
                a = false;
                System.out.println("You are just as smart as this program;>");
            }
        }
    }

    public static void printBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}




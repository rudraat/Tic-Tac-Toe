package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static  ArrayList<Integer> p1Log = new ArrayList<>();
    private static  ArrayList<Integer> p2Log = new ArrayList<>();

    public static void main(String[] args) {

        char[] board = getEmptyBoard();
        printBoard(board);
        while (true) {
            int p1Place = readCoordinates();
            while (p1Log.contains(p1Place) || p2Log.contains(p1Place)) {
                System.out.println("This cell is occupied! Choose another one!");
                p1Place = readCoordinates();
            }
            placePiece(board, p1Place, "player1");
            printBoard(board);

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            int p2Place = readCoordinates();
            while (p1Log.contains(p2Place) || p2Log.contains(p2Place)) {
                System.out.println("This cell is occupied! Choose another one!");
                p2Place = readCoordinates();
            }
            placePiece(board, p2Place, "player2");
            printBoard(board);
            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }


        }
    }


    private static char[] getEmptyBoard() {
        char[] result = new char[9];
        for (int i = 0; i < 9; i++) result[i] = ' ';
        return result;
    }

    private static char[] readBoard() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter cells: ");
        return sc.nextLine().toCharArray();
    }

    static int readCoordinates() {
        Scanner sc = new Scanner(System.in);
        int[] place = new int[2];
        int pos = -1;
        while (true) {
            System.out.println("Enter the coordinates: ");
            String col = sc.next();
            String row = sc.next();

            try {

                place[0] = Integer.parseInt(col);
                place[1] = Integer.parseInt(row);
                if (place[0] < 0 || place[0] > 3 || place[1] < 0 || place[1] > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }

        if (place[0] == 1 && place[1] == 3) {
            pos = 0;
        } else if (place[0] == 2 && place[1] == 3) {
            pos = 1;
        } else if (place[0] == 3 && place[1] == 3) {
            pos = 2;
        } else if (place[0] == 1 && place[1] == 2) {
            pos = 3;
        } else if (place[0] == 2 && place[1] == 2) {
            pos = 4;
        } else if (place[0] == 3 && place[1] == 2) {
            pos = 5;
        } else if (place[0] == 1 && place[1] == 1) {
            pos = 6;
        } else if (place[0] == 2 && place[1] == 1) {
            pos = 7;
        } else if (place[0] == 3 && place[1] == 1) {
            pos = 8;
        }

        return pos;

    }


    private static void placePiece(char[] board, int pos, String user) {

        char symbol = ' ';
        if (user.equals("player1")) {
            symbol = 'X';
            p1Log.add(pos);
        } else if (user.equals("player2")) {
            symbol = 'O';
            p2Log.add(pos);
        }

        board[pos] = symbol;


    }


    private static void printBoard(char[] arr) {

        for (int i = 0; i < 9; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.println(String.format("| %c %c %c |", arr[i], arr[i + 1], arr[i + 2]));
        }
        for (int i = 0; i < 9; i++) {
            System.out.print("-");
        }
        System.out.println();
    }


    public static String checkWinner() {

        List<Integer> topRow = Arrays.asList(0, 1, 2);
        List<Integer> midRow = Arrays.asList(3, 4, 5);
        List<Integer> botRow = Arrays.asList(6, 7, 8);
        List<Integer> leftCol = Arrays.asList(0, 3, 6);
        List<Integer> midCol = Arrays.asList(1, 4, 7);
        List<Integer> rightCol = Arrays.asList(2, 5, 8);
        List<Integer> diag1 = Arrays.asList(0, 4, 8);
        List<Integer> diag2 = Arrays.asList(2, 4, 6);

        List<List<Integer>> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(diag1);
        winning.add(diag2);

        //TODO - impossible route - game not finished;
        for (List<Integer> l : winning) {
            if (p1Log.containsAll(l)) {
                return "X wins";
            } else if (p2Log.containsAll(l)) {
                return "O wins";
            }
        }

         if (p1Log.size() + p2Log.size() == 9) {
            return "Draw";
        }

        return "";
    }
}


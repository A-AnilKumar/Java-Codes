
/*
 *  input the position values as row and col 
 *  row and column values from 0 to 2 
 *  
 */

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] Board = new char[3][3];
        int spaceLeft = 9;
        for (char[] board : Board)
            for (int i = 0; i < board.length; i++)
                board[i] = ' ';

        char player = 'X';
        boolean gameOver = false;

        Scanner scan = new Scanner(System.in);

        while (!gameOver) {

            printBoard(Board);

            // String rowString;
            // String colString;

            int row;
            int col;
            while (true) {
                System.out.println("Player " + player + " enter : ");
                
                row = scan.nextInt();
                col = scan.nextInt();
                
                // rowString = scan.next();
                // colString = scan.next();
                // row = Integer.valueOf(rowString);
                // col = Integer.valueOf(colString);
                
                // try {
                //     // if( )    
                // } catch (Exception e) {
                //   
                // }
                
                
                if (row < 0 || row >= Board.length || col < 0 || col >= Board[0].length) {
                    System.out.println("Input values for row and col between (0 - 2)");
                    continue;
                }
                break;
            }

            if (Board[row][col] == ' ') {
                spaceLeft--;
                Board[row][col] = player;
                gameOver = haveWon(Board, player);
                if (gameOver) {
                    System.out.println("Player " + player + " won");
                }
                player = player == 'X' ? 'O' : 'X';
                if (spaceLeft == 0) {
                    System.out.println("     DRAW MATCH  !!");
                    System.out.println("Both the players (X,O) played well");
                    break;
                } 
            }
            else{
                System.out.println("INVALID MOVE,CHOOSE OTHER POSITION");
            }
        }
        printBoard(Board);
        scan.close();
    }

    private static boolean haveWon(char[][] board, char player) {

        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player)
                return true;
        }

        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player)
                return true;
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true;

        return false;
    }

    private static void printBoard(char[][] Board) {
        for (int row = 0; row < Board.length; row++) {

            for (int col = 0; col < Board[row].length; col++) {
                System.out.print(Board[row][col] + " | ");
            }
            System.out.println();
        }
    }
}



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    
    static ArrayList<Integer> humanPositions = new ArrayList<>();
    static ArrayList<Integer> systemPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] Board = {{' ','|',' ','|',' '},
                          {'-','-','-','-','-'},
                          {' ','|',' ','|',' '},
                          {'-','-','-','-','-'},
                          {' ','|',' ','|',' '}};

        printBoard(Board);

        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Enter position number (1-9) to place ");
            int pos = scan.nextInt();
            // try{
            //     if(pos )
            // }
            // catch(Exception e ){

            // }
    
            if(pos < 10 && pos > 0 ){

                while (humanPositions.contains(pos) || systemPositions.contains(pos)) {
                    System.out.println("INVALID POSITION , CHOOSE ANOTHER");
                    pos = scan.nextInt();
                }
                placePlayer(Board, pos, "Human");
                printBoard(Board);       
            }
            if(pos > 9 || pos < 1)
                continue;

            String result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int sysPos = rand.nextInt(9)+1;
            while (humanPositions.contains(sysPos) || systemPositions.contains(sysPos)) {
                // System.out.println("INVALID MOVE , CHOOSE ANOTHER");
                sysPos = rand.nextInt(9)+1;
            }
            placePlayer(Board, sysPos, "System");
            printBoard(Board);
            result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
            // System.out.println(checkWinner());
        }
        scan.close();
    }

    public static void printBoard(char[][] Board){
        System.out.println("Game Board");
        for(char[] ch : Board){
            for(char c : ch)
                System.out.print(c);
            System.out.println();
        }
        System.out.println();
    }

    public static void placePlayer(char[][] Board,int pos,String player){
        // char player = (user.equals("Human")) ? 'X' : 'O' ; 

        char symbol = ' ';
        if(player.equals("Human")){
            symbol ='X';
            humanPositions.add(pos);
        }
        else if(player.equals("System")){
            symbol='O';
            systemPositions.add(pos);
        }

        switch (pos) {
            case 1:Board[0][0] = symbol;
                    break;
            case 2:Board[0][2] = symbol;
                    break;
            case 3:Board[0][4] = symbol;
                    break;
            case 4:Board[2][0] = symbol;
                    break;
            case 5:Board[2][2] = symbol;
                    break;
            case 6:Board[2][4] = symbol;
                    break;
            case 7:Board[4][0] = symbol;
                    break;
            case 8:Board[4][2] = symbol;
                    break;
            case 9:Board[4][4] = symbol;
                    break;
            default:
                System.out.println("Input position in range(1-9)");
                break;
        }

    }

    public static String checkWinner(){
       
        List<Integer> Row1 = Arrays.asList(1,2,3);
        List<Integer> Row2 = Arrays.asList(4,5,6);
        List<Integer> Row3 = Arrays.asList(7,8,9);
        List<Integer> Col1 = Arrays.asList(1,4,7);
        List<Integer> Col2 = Arrays.asList(2,5,8);
        List<Integer> Col3 = Arrays.asList(3,6,9);
        List<Integer> diagonal1 = Arrays.asList(1,5,9);
        List<Integer> diagonal2 = Arrays.asList(3,5,7);
       
        
        List<List> winningPos = new ArrayList<List>();
        winningPos.add(Row1);
        winningPos.add(Row2);
        winningPos.add(Row3);
        winningPos.add(Col1);
        winningPos.add(Col2);
        winningPos.add(Col3);
        winningPos.add(diagonal1);
        winningPos.add(diagonal2);
        
        for(List list : winningPos){
            if(humanPositions.containsAll(list)){
                return "Congratulation ..! You Won ..!!";
            }
            else if(systemPositions.containsAll(list)){
                return "System won ..! Try Again !";
            }
            
        }

     if(humanPositions.size() + systemPositions.size() == 9){
                return "Wow !! its a Tie..!!";
    }
    return "";
    }

}

import java.util.Scanner;
public class Tic_Tac_Toe{

	//constant variable declaration that reperesent the cell contents
	public static final int EMPTY=0;
	public static final int TOKEN_X=1;
	public static final int TOKEN_O=2;
     
     //constant variable declaration that represent the state of the game 
	public static final int PLAYING=0;
	public static final int DRAW=1;
	public static final int TOKEN_X_WON=2;
	public static final int TOKEN_O_WON=3;

	public static final int ROW=3,COLUMN=3;//the number of row and column
	public static int board[][] =new int[ROW][COLUMN];//an array that initialise the row and column
    
    public static int currentState;//current states ,PLAYING OR DRAW OR TOKEN_X OR TOKEN_O

    public static int currentPlayer;//current player Token X or Token O
    public static int currentRow,currentColumn;//current cell row or column
    
    public static Scanner input =new Scanner(System.in);//input scanner
    //methods that initial the game
    public static void initializeGame()
    {
      for(int row=0;row<ROW;row++)
      	for(int column=0;column<COLUMN;column++)
      	{
      		board[row][column]=EMPTY;//all cells empty
      	}

        currentState=PLAYING;
        currentPlayer=TOKEN_X;
    }
    /*the player with the token makes one move,update validation*/
    public static void movePlayer(int token)
    {
      boolean validInput=false;

       int prow;
        int pcol;
      do
      {
        if(token ==TOKEN_X)
        {
          System.out.print("Player 'X',enter a row (0,1 or 2):");
          prow =input.nextInt();
          System.out.print("Player 'X',enter a column (0,1 or 2):");
          pcol=input.nextInt();
        }
        else
        {
          System.out.print("Player 'O',enter a row (0,1 or 2):");
          prow =input.nextInt();
          System.out.print("Player 'O',enter a column (0,1 or 2):");
          pcol=input.nextInt();
        }
        
        if(prow >=0 && prow < ROW && pcol >=0 &&pcol <COLUMN && board[prow][pcol]==EMPTY)
        {
          currentRow=prow;
          currentColumn=pcol;
          board[currentRow][currentColumn]=token;//update game borad content
          validInput=true;//input ok,exit loop
        }
        else
        {
          System.out.println("incorrect move");
        }
       
      }while(!validInput);//repeat until input is valid

    }
    //the methods that test if the payer has won and return the tre or false
    public static boolean won(int token,int currentRow,int currentColumn)
    {
      return((board[currentRow][0]==token //3 in the row
                 && board[currentRow][1]==token
                 && board[currentRow][2] ==token)
            ||(board[0][currentColumn]==token //3 in the column
                && board[1][currentColumn] ==token
                && board[2][currentColumn] ==token)
            || ((currentRow == currentColumn) //3 in a diagonal
                && board[0][0]==token
                && board[1][1]==token
                && board[2][2]==token)
            || (currentRow + currentColumn ==2 //3 in the oppoosite diagonal
                && board[2][0]==token
                && board[1][1] ==token
                && board[0][2]==token));   
    }
    /*this method return true if it is a draw and false if its not */
    public static boolean draw()
    {
      for(int row =0;row <ROW;++row)
      {
        for(int column =0;column<COLUMN;++column)
        {
          if(board[row][column] == EMPTY)
          {
            return false;//no draw ,exit
          }
        }
      }
      return true;//its a draw
    }
  
    //method for update the game
    public static void updateGame(int token,int currentRow,int currentColumn)
    {
      if(won(token,currentRow,currentColumn))//check if the winning move
      {
        currentState=(token==TOKEN_X) ? TOKEN_X_WON:TOKEN_O_WON;
      }
      else if(draw())//check for draw
      {
        currentState=DRAW;
      }
    }
   
    //method to print the cell inside the board
    public static void printCells(int content)
    {
      switch(content)
      {
       case EMPTY:
         System.out.print("  ");
         break;
       case TOKEN_O:
         System.out.print(" O "); 
         break;
      case TOKEN_X:
        System.out.print(" X ");
        break;
      }
    }

    //method to print the board
    public static void printBoard()
    {

      for(int row=0;row < ROW;++row)
      {
        for(int column=0;column <COLUMN;++column)
        {
           printCells(board[row][column]);
          
           if(column != COLUMN -1)
           {
             System.out.print(" | ");//print the vertical partition
           }
        } 
         System.out.println();
          if(row != ROW -1)
           {
             System.out.println("-----------------");//print horizontal partition
           }      
      }
       System.out.println(); 

    }
  //the methods to run the program 
	public static void main(String[] list)
	{
      initializeGame();//initialising the game

      do
      {
        movePlayer(currentPlayer);//update current row and current column
        updateGame(currentPlayer,currentRow,currentColumn);//update the current game/state
        printBoard();//print the board
        if(currentState == TOKEN_X_WON)
        {
          System.out.println("Player 'X' has won!" );
        }
        else if(currentState == TOKEN_O_WON)
        {
         System.out.println("Player 'O' has won!");
        }
        else if(currentState == DRAW)
        {
         System.out.println("its a draw");
        }
        //switch players
        currentPlayer =(currentPlayer==TOKEN_X) ? TOKEN_O:TOKEN_X;
      }while(currentState==PLAYING);
      
	}
}

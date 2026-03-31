import java.util.Objects;
import java.util.Scanner;

public class Main
{

    // Imported ANSI escape codes to change the text color of the console
    static Scanner scanner = new Scanner(System.in);
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    public static void main(String[] args)
    {

        // Name of members and ids:
        // Ricardo Alvear | 101536306
        // Sayuni Wimaladharma | 101563313
        // Kartik Madarapu | 101564894
        // Noela Bilonda Kabundi | 101485935

        System.out.println(BLUE + "Hello! Welcome To The TicTacToe Game" + RESET);
        System.out.print(GREEN + "Enter 1 for single player or 2 for two players: " + RESET);
        String choice = scanner.nextLine();

        // While the user input does not equal to "1" or "2",
        // Continue asking them for the valid input
        while(!Objects.equals(choice, "1") && !Objects.equals(choice, "2"))
        {
            System.out.println(RED + "\nSorry, invalid input." + RESET);
            System.out.println("Please enter 1 for single player or 2 for two players: ");
            choice = scanner.nextLine();
        }

        // Selects the correct method depending on the user input
        switch (choice)
        {
            case "1":
                System.out.println(YELLOW + "\nNow loading single player game..." + RESET);
                OnePlayer();
                break;
            case "2":
                System.out.println(YELLOW + "\nNow loading two player game..." + RESET);
                TwoPlayer();
        }

    }

    public static void OnePlayer()
    {
          System.out.println(GREEN + "Starting the game!" + RESET);

          char[][] board = new char[3][3];

          for(int i = 0; i < 3; i ++)
          {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = '-';
            }
          }

          System.out.print("Please enter your name: ");
          String player = scanner.nextLine();

          System.out.print("Choose your symbol (X or O): ");
          char playerSymbol = scanner.nextLine().toUpperCase().charAt(0);

          while(playerSymbol != 'X' && playerSymbol != 'O')
          {
            System.out.print("Invalid. Please choose X or O: ");
            playerSymbol = scanner.nextLine().toUpperCase().charAt(0);
          }

          char computerSymbol = (playerSymbol == 'X') ? 'O' : 'X';

          boolean playerTurn = (playerSymbol == 'X');
          boolean gameEnded = false;

          // Main game loop - continues until game ends (win/draw)
          while (!gameEnded) {
              // Display current board state
              printBoard(board);
              
              if (playerTurn) {
                  // Player's turn - get user input for move
                  System.out.println(YELLOW + player + "'s turn (" + playerSymbol + ")" + RESET);
                  
                  int row, col;
                  do {
                      // Prompt for row and column with validation
                      System.out.print("Enter row (0-2): ");
                      row = scanner.nextInt();
                      System.out.print("Enter column (0-2): ");
                      col = scanner.nextInt();
                      scanner.nextLine(); // Consume newline
                      
                      // Validate input range and position availability
                      if (row < 0 || row > 2 || col < 0 || col > 2) {
                          System.out.println(RED + "Invalid position! Please enter values between 0 and 2." + RESET);
                      } else if (board[row][col] != '-') {
                          System.out.println(RED + "Position already taken! Please choose another position." + RESET);
                      } else {
                          break;
                      }
                  } while (true);

                  board[row][col] = playerSymbol;
              } else {
                  // Computer's turn - make random move
                  System.out.println(YELLOW + "Computer's turn (" + computerSymbol + ")" + RESET);
                  
                  int row, col;
                  do {
                      // Generate random position until finding empty spot
                      row = (int)(Math.random() * 3);
                      col = (int)(Math.random() * 3);
                  } while (board[row][col] != '-');

                  board[row][col] = computerSymbol;
                  System.out.println("Computer placed " + computerSymbol + " at row " + row + ", column " + col);
              }
              
              // Check for win condition after current move
              if (checkWin(board, playerTurn ? playerSymbol : computerSymbol)) {
                  printBoard(board);
                  if (playerTurn) {
                      System.out.println(GREEN + "Congratulations " + player + "! You won!" + RESET);
                  } else {
                      System.out.println(RED + "Computer wins! Better luck next time." + RESET);
                  }
                  gameEnded = true;
              } else if (isFull(board)) {
                  // Check for draw condition (board full, no winner)
                  printBoard(board);
                  System.out.println(YELLOW + "It's a draw!" + RESET);
                  gameEnded = true;
              } else {
                  // Switch turns for next round
                  playerTurn = !playerTurn;
              }
          }
    }

    public static void TwoPlayer()
    {

        System.out.println(GREEN + "Starting the game!\n" + RESET);
        String winner = "";
        String p1Symbol;
        String p2Symbol = "";
        boolean draw = false;

        System.out.print("Player 1 | Please enter your name: ");
        String p1 = scanner.nextLine();

        nameCheckTwoPlayerGame(p1);

        System.out.print("Please enter your symbol: ");
        p1Symbol = scanner.nextLine().toLowerCase();

        symbolCheckTwoPlayerGame(p1Symbol, p2Symbol);

        System.out.print("Player 2 | Please enter your name: ");
        String p2 = scanner.nextLine();

        nameCheckTwoPlayerGame(p2);

        System.out.print("Please enter your symbol: ");
        p2Symbol = scanner.nextLine().toLowerCase();

        symbolCheckTwoPlayerGame(p1Symbol, p2Symbol);

        // While the winner is not either player one or player two or a draw has not been reached,
        // Continue the game
        while (!Objects.equals(winner, p1) || !Objects.equals(winner, p2) || !Objects.equals(draw, true))
        {

            // Make an array of integers that has three columns and rows
            int[][] array = new int[3][3];

            System.out.println("Player 1 | Enter the column and row (comma separated):");

            columnsRowsCheckTwoPlayerGame(winner, draw, p1Symbol, array);

            System.out.println("Player 2 | Enter the column and row (comma separated):");

            columnsRowsCheckTwoPlayerGame(winner, draw, p2Symbol, array);


            // Check if the result of the method matches one of the conditions,
            // If true, break out of the while loop
            if (columnsRowsCheckTwoPlayerGame(winner, draw, p1Symbol, array).equals("Player 1"))
            {
                winner = p1;
            }else if(columnsRowsCheckTwoPlayerGame(winner, draw, p2Symbol, array).equals("Player 2"))
            {
                winner = p2;
            }else if (columnsRowsCheckTwoPlayerGame(winner, draw, p1Symbol, array).equals("draw") && columnsRowsCheckTwoPlayerGame(winner, draw, p2Symbol, array).equals("draw"))
            {
                draw = true;
            }

        }

        if (!Objects.equals(winner, ""))
        {
            System.out.println(GREEN + "CONGRATULATIONS! " + winner + " has won the game!" + RESET);
        }
        else if (draw)
        {
            System.out.println(GREEN + "DRAW! " + p1 + " and " + p2 + " have tied" + RESET);
        }


    }

    public static String columnsRowsCheckTwoPlayerGame(String winner, boolean draw, String curPlayer, int[][] array)
    {
        while (true)
        {

            String[] data;
            try
            {

                data = scanner.nextLine().split(",");

                // Try to parse the columns and rows
                int p2Column = Integer.parseInt(data[0].trim());
                int p2Row = Integer.parseInt(data[1].trim());

                // If the player has the symbol 'x', pass that symbol which uniquely identifies that player
                // Otherwise, pass the symbol that uniquely identifies the second player
                // Regardless, the array with the indicated column and row will be passed
                if (Objects.equals(curPlayer, "x")){
                    return tablePrintAndCheckResult("x", array[p2Column][p2Row]);
                }else{
                    return tablePrintAndCheckResult("o", array[p2Column][p2Row]);
                }
            } catch (Exception e)
            {
                System.out.println(RED + "\nSorry, invalid input" + RESET);
                System.out.println("\nPlease, enter a valid input");
                columnsRowsCheckTwoPlayerGame(winner, draw, curPlayer, array);
            }

        }
    }

    public static String tablePrintAndCheckResult(String symbol, int array)
    {

        String result = "";

        // Print the table with the current placements of all the symbols
        System.out.println(
                BLUE + "   Table\n" + RESET +
                "   |   " +    "|\n" +
                "---" + "|---" + "|" + "---\n" +
                "---" + "|---" + "|" + "---\n" +
                "   |   " + "|"
        );

        // Check if the result is one of the players or a draw
        if (Objects.equals(result, "Player 1"))
        {
            return result;
        }else if (Objects.equals(result, "Player 2"))
        {
            return result;
        }else if (result.equals("draw"))
        {
            return result;
        }

        return "Continuing";
    }

    public static void nameCheckTwoPlayerGame(String name)
    {

        // While the input does not contain only letters,
        // Continue asking for the valid input
        while (!name.matches("^[a-zA-Z]+$"))
        {
            System.out.println(RED + "\nSorry, invalid input." + RESET);
            System.out.print("Please enter your name: ");
            name = scanner.nextLine();
        }

    }

    public static void symbolCheckTwoPlayerGame(String p1Symbol, String p2Symbol)
    {

        // While the symbols are the same,
        // continue asking for the valid input
        while (Objects.equals(p1Symbol, p2Symbol))
        {
            System.out.println(RED + "\nSorry, invalid input." + RESET);
            System.out.print("Cannot be the same. enter your symbol: ");
            p2Symbol = scanner.nextLine().toLowerCase();
        }

        // While symbols do not contain either 'x' or 'o',
        // continue asking for the valid input
        while (!Objects.equals(p1Symbol, "o")  && !Objects.equals(p1Symbol, "x") && !Objects.equals(p2Symbol, "o")  && !Objects.equals(p2Symbol, "x"))
        {
            System.out.println(RED + "\nSorry, invalid input." + RESET);
            System.out.print("Please enter your symbol: ");
            p2Symbol = scanner.nextLine().toLowerCase();
        }

    }

    public static void printBoard(char[][] board)
    {
        System.out.println("\nBoard:");
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean checkWin(char[][] board, char symbol)
    {
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0] == symbol &&
                    board[i][1] == symbol &&
                    board[i][2] == symbol)
                return true;
        }

        for (int j = 0; j < 3; j++)
        {
            if (board[0][j] == symbol &&
                    board[1][j] == symbol &&
                    board[2][j] == symbol)
                return true;
        }

        if (board[0][0] == symbol &&
                board[1][1] == symbol &&
                board[2][2] == symbol)
            return true;

        if (board[0][2] == symbol &&
                board[1][1] == symbol &&
                board[2][0] == symbol)
            return true;

        return false;
    }

    // Minimax AI implementation for optimal computer moves
    public static int[] findBestMove(char[][] board, char computerSymbol, char playerSymbol) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = computerSymbol;
                    int score = minimax(board, 0, false, computerSymbol, playerSymbol);
                    board[i][j] = '-';
                    
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        
        return bestMove;
    }
    
    public static int minimax(char[][] board, int depth, boolean isMaximizing, char computerSymbol, char playerSymbol) {
        // Check for terminal states
        if (checkWin(board, computerSymbol)) {
            return 10 - depth;
        }
        if (checkWin(board, playerSymbol)) {
            return depth - 10;
        }
        if (isFull(board)) {
            return 0;
        }
        
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = computerSymbol;
                        int score = minimax(board, depth + 1, false, computerSymbol, playerSymbol);
                        board[i][j] = '-';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = playerSymbol;
                        int score = minimax(board, depth + 1, true, computerSymbol, playerSymbol);
                        board[i][j] = '-';
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    public static boolean isFull(char[][] board)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }
}
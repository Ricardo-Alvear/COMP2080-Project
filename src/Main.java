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
        // Noela Bilonda Kabundi |

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
//        Sayuni will do this:
//        2) If the user selects 1 player (versus Minimax AI)
//        a. The Human player should be asked their name
//        b. The player must be prompted to choose their symbol (‘X’ or O’)
//        c. The player with symbol ‘X’ should go first (computer or human player).
//        d. The present board state will be displayed and:
//        1. If the computer is to play, it will now show the board updated with the
//        computer’s symbol.
//        2. If it is the player’s turn, the player will be prompted for a row and column
//        location to play.
//        e. The board will then be checked for a winning state or draw and if it is not in a winning
//        state or draw(tie), go to step d otherwise display the winner or declare a draw (tie).

    }



    public static void TwoPlayer() {
        System.out.println(GREEN + "Starting the game!\n" + RESET);

        // Get player names and symbols
        System.out.print("Player 1 | Enter your name: ");
        String p1Name = scanner.nextLine();
        nameCheckTwoPlayerGame(p1Name);
        System.out.print("Player 1 | Choose your symbol (X or O): ");
        char p1Symbol = getSymbolInput();

        System.out.print("Player 2 | Enter your name: ");
        String p2Name = scanner.nextLine();
        nameCheckTwoPlayerGame(p2Name);
        char p2Symbol = (p1Symbol == 'X') ? 'O' : 'X';
        System.out.println("Player 2 | Your symbol is " + p2Symbol);

        // Create empty board
        char[][] board = new char[3][3];
        initBoard(board);

        // Determine who goes first (X always starts)
        String currentPlayer;
        char currentSymbol;
        if (p1Symbol == 'X') {
            currentPlayer = p1Name;
            currentSymbol = p1Symbol;
        } else {
            currentPlayer = p2Name;
            currentSymbol = p2Symbol;
        }

        boolean gameOver = false;
        String winner = null;

        while (!gameOver) {
            displayBoard(board);

            System.out.print(currentPlayer + " (" + currentSymbol + "), enter row and column (1‑3, space separated): ");
            int[] move = getPlayerMove(board);
            int row = move[0];
            int col = move[1];

            // Place the symbol
            board[row][col] = currentSymbol;

            // Check win
            if (checkWin(board, currentSymbol)) {
                gameOver = true;
                winner = currentPlayer;
            } else if (isDraw(board)) {
                gameOver = true;
            } else {
                // Switch player
                if (currentPlayer.equals(p1Name)) {
                    currentPlayer = p2Name;
                    currentSymbol = p2Symbol;
                } else {
                    currentPlayer = p1Name;
                    currentSymbol = p1Symbol;
                }
            }
        }

        displayBoard(board);
        if (winner != null) {
            System.out.println(GREEN + "CONGRATULATIONS! " + winner + " has won the game!" + RESET);
        } else {
            System.out.println(GREEN + "DRAW! " + p1Name + " and " + p2Name + " have tied." + RESET);
        }
    }

// -------------------------------------------------------------------------
// Helper methods for TwoPlayer
// -------------------------------------------------------------------------

    // Initialize the board with empty spaces
    private static void initBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Display the board with row and column numbers
    private static void displayBoard(char[][] board) {
        System.out.println("\n   1   2   3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  ---+---+---");
        }
        System.out.println();
    }

    // Get a valid move from the player (returns 0‑based row and column)
    private static int[] getPlayerMove(char[][] board) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                String[] parts = input.split("\\s+");
                if (parts.length != 2) {
                    System.out.print("Please enter two numbers (row and column): ");
                    continue;
                }
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.print("Row and column must be between 1 and 3. Try again: ");
                } else if (board[row][col] != ' ') {
                    System.out.print("That cell is already taken. Choose another: ");
                } else {
                    return new int[]{row, col};
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter numbers (e.g., 1 2): ");
            }
        }
    }

    // Check if the current player has won
    private static boolean checkWin(char[][] board, char symbol) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol)
                return true;
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol)
                return true;
        }
        // Check diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
            return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
            return true;
        return false;
    }

    // Check if the board is full (draw)
    private static boolean isDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        // If full and no winner, it's a draw
        return true;
    }

    // Helper to get a valid symbol (X or O) from the user
    private static char getSymbolInput() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("X") || input.equals("O")) {
                return input.charAt(0);
            }
            System.out.print("Invalid symbol. Choose X or O: ");
        }
    }

    // Existing name check function (unchanged, but we'll keep it)
    public static void nameCheckTwoPlayerGame(String name) {
        while (!name.matches("^[a-zA-Z]+$")) {
            System.out.println(RED + "\nSorry, invalid input." + RESET);
            System.out.print("Please enter your name: ");
            name = scanner.nextLine();
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
}
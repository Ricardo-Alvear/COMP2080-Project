// Name of members and ids:
// Ricardo Alvear | 101536306
// Sayuni Wimaladharma | 101563313
// Karthik Madarapu | 101564894
// Noela Bilonda Kabundi | 101485935


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

        System.out.println("Testing....");
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

    public static void TwoPlayer() {
        System.out.println(GREEN + "Starting the game!\n" + RESET);

        // Get player names and symbols
        System.out.print("Player 1 | Enter your name: ");
        String p1Name = validateName();
        System.out.print("Player 1 | Choose your symbol (X or O): ");
        char p1Symbol = validateSymbol();

        System.out.print("Player 2 | Enter your name: ");
        String p2Name = validateName();
        char p2Symbol = (p1Symbol == 'X') ? 'O' : 'X';
        System.out.println("Player 2 | Your symbol is " + p2Symbol);

        // Create empty board (use '-' for empty)
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
            printBoard(board); // uses existing printBoard method
            System.out.print(currentPlayer + " (" + currentSymbol + "), enter row and column (0‑2, space separated): ");
            int[] move = getPlayerMove(board);
            int row = move[0];
            int col = move[1];

            // Place the symbol
            board[row][col] = currentSymbol;

            // Check win
            if (checkWin(board, currentSymbol)) {
                gameOver = true;
                winner = currentPlayer;
            } else if (isFull(board)) {
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

        printBoard(board);
        if (winner != null) {
            System.out.println(GREEN + "CONGRATULATIONS! " + winner + " has won the game!" + RESET);
        } else {
            System.out.println(GREEN + "DRAW! " + p1Name + " and " + p2Name + " have tied." + RESET);
        }
    }

    // Helper: validates a name (only letters)
    private static String validateName() {
        while (true) {
            String name = scanner.nextLine().trim();
            if (name.matches("^[a-zA-Z]+$")) {
                return name;
            }
            System.out.print(RED + "Invalid name. Use only letters. Try again: " + RESET);
        }
    }

    // Helper: validates a symbol (X or O)
    private static char validateSymbol() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("X") || input.equals("O")) {
                return input.charAt(0);
            }
            System.out.print(RED + "Invalid symbol. Choose X or O: " + RESET);
        }
    }

    // Helper: initializes board with '-'
    private static void initBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Helper: gets a valid move from the player (returns 0‑based row, col)
    private static int[] getPlayerMove(char[][] board) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                String[] parts = input.split("\\s+");
                if (parts.length != 2) {
                    System.out.print("Please enter two numbers (row and column): ");
                    continue;
                }
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.print("Row and column must be between 0 and 2. Try again: ");
                } else if (board[row][col] != '-') {
                    System.out.print("That cell is already taken. Choose another: ");
                } else {
                    return new int[]{row, col};
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter numbers (e.g., 0 1): ");
            }
        }
    }    public static void printBoard(char[][] board)
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
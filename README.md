# COMP2080-Project

Tic-Tac-Toe (Classic) Game:
Tic-Tac-Toe is a classic two-player strategy game usually played on a 3×3 grid. Each player is
assigned a symbol—traditionally X or O—and takes turns placing their symbol in an empty
square on the grid. The objective is to be the first player to arrange three of their symbols in a
continuous line—horizontally, vertically, or diagonally. If all squares on the board are filled and
neither player has formed a line of three, the game ends in a draw. This project will use a
standard 3 × 3 grid.

A detailed overview of the Tic Tac Toe (classic) game can be found here:
https://www.youtube.com/watch?v=3qzcAMShotQ

❗️ - Your group must create a fully working intelligent Tic-Tac-Toe command line game in Java using only basic arrays (e.g., char[][] or int[][]). Higher-level data structures beyond simple arrays will result in a maximum achievable mark of 50% for this assignment. The game must allow the user to select the mode of the game. The two modes are:

- 2 player (2 human players)
- 1 player (Human vs Intelligent AI)
  
Intelligent AI:
This must be implemented using the Minimax algorithm. The computer must examine the game
state at each point and deduce the optimal (best) play that can be made.
A detailed overview of the Minimax algorithm can be found here:

https://www.youtube.com/watch?v=zp3VMe0Jpf8
ignore all the information above a nd us e rand om sel ection instead of the mini max algorithm. Also do 4 in a row a nd use a 4x4 board .

The Game should operate as follows:

1) The user should be asked if it is a 2 player OR 1 player game (versus Intelligent AI)
2) If the user selects 1 player (versus minimax AI)
a. The Human player should be asked their name
b. The player must be prompted to choose their symbol (‘X’ or ‘O’)
c. The player with symbol ‘X’ should go first (computer or human player).
d. The present board state will be displayed and:
1. If the computer is to play, it will now show the board updated with the
computer’s symbol.

3. If it is the player’s turn, the player will be prompted for a row and column
location to play.
e. The board will then be checked for a winning state or draw and if it is not in a winning
state or draw(tie), go to step d otherwise display the winner or declare a draw (tie).
3) If the user selects 2 player (2 Human players)
a. Each human player must be prompted to choose their name and symbol (‘X’ or ‘O’)
b. An empty board will then be displayed.
c. The next player to move will be prompted for a row and column location to play.
d. The present board state will be displayed showing their updated play.
e. The board will then be checked for a winning state or draw and if it is not in a winning
state or draw, go to step c otherwise display the winner or declare a draw.
At the end of a game, the result should be displayed

❗️ - YOU MUST ONLY USE BASIC ARRAYS TO MODEL THE BOARD STATE – if this is not done, the maximum mark that can be attained is 7.5/15.
NOTE:
The non-AI part (2 human players) is worth 10 of the marks
The recursive minimax version (1 human vs AI) is worth 5 of the marks

❗️ - This assignment loses 30% each day (based on the date) after the 3rd April.
❗️ - This means if it is submitted 12:01 AM (1 minute late) on the 4th, the maximum mark that can be got is 10.5/15.
❗️ - This means if it is submitted 12:01 AM on the 5th, the maximum mark that can be got is 6/15.
It is therefore better to submit a non-working AI version on time than an AI version late.

This is a group assignment, and one report and code will be submitted per group.
Your group must submit two Items:

1) The completed submission document (Given with this project) with ALL the group
member’s names inserted at the top and code pasted neatly into it.

2) The completed Java source classes (zipped) with all student’s names and numbers
commented at the top of the code [not the whole project, just all the java classes you
created]. To be clear, do NOT upload a whole solution just the classes.

Please note:

❗️ - 1) 2 marks will be automatically deducted if the submission formats are not followed.

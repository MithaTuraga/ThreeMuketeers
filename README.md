INTRODUCTION
----------------------------------------------------------------

This game is an encoded version of the Three Musketeers Board game. The board game is an abstract game, that uses the principle of unequal forces; 
the two players neither use the same types of pieces nor the same rules, and their victory conditions are different. Feel free to play against an AI to gain some intuition
about this game. http://www.onlinesologames.com/three-musketeers

The goal of this project is inversion of control and to create a GUI version of the Three Musketeers game. 


TOOLS 
----------------------------------------------------------------
Here are the following tools I have used to build this project; 

* Eclipse
* Java
* JavaFX and JavaFX SDX
* Maven Library
* SOLID and OOP Design Principles 


FEATURES 
-----------------------------------------------------------------
1) Human Vs. Human
2) Human Vs. Greedy Agent 
3) Human Vs. Random Agent 


FILES
------------------------------------------------------------------

- ThreeMusketeer.java; The main move controller containing move selection and undo move selection on the board for the chosen agent. 
  
- Board.java; The logic controller of the game, keeps track of the board and all moves made on the board. 

- BoardEvaluatorImpl.java; The logic controller for the Greedy agent. This file includes a method called evaluate board that returns a score. A higher
score means the Musketeer side is winning and a lower score means the Guard side is winning. Uses heuristics to generate a score given a board. The GreedyAgent will go through possible
moves and pick the best move for the current side based on this score

- Guard.java; Class represent player Guard, contains a method that evaluates if guard is eligble to move to a chosen spot. 

- Musketeer.java; Class to represent player Musketeer, contains a method that evaluates if musketeer is eligble to move to a chosen spot. 

- HumanAgent.java; Represents the HumanAgent, contains function that asks the human for a move to be done. This function needs to validate the human input and
make sure the move is valid for the piece type that is moving.

- RandomAgent.java; Represents the RandomAgent, contains a funciton that gets a valid random move that can be done on the board.

- ModeInputPanel.java; The main menu view containing game mode selection and board loading options

- SideInputPanel.java; A side selector menu to choose which side a Human agent wants to play against a Computer agent

- BoardPanel.java; The game view containing the game board and the game controls

- View.java; A view + controller that manages what is visible in the GUI and handles model updates


GETTING STARTED
---------------------------------------------------------------

**Project Link:** https://github.com/MithaTuraga/ThreeMusketeers.git

**Important:** The main function in App.java runs the game and starts the GUI. Boards are located in the boards directory
and boards are saved as .txt files with the current piece turn type on the first line followed by the 5 by 5
board just like in Assignment 1 (”X” = Musketeer, ”O” = Guard, ” ” = Empty cell)



NOTES 
--------------------------------------------------------------
There were three aspects to this project,

1) Version 1 - This implementation focuses on using OOP principles to develop and understand basic logic. 
2) Version 2 - Once game logic was implemented, a GUI version was implemented to have the game up and running
3) Version 3 - This implementation focused on maintaining and developing the code given user stories and project requirements, the focus of this version was to add in new features using SOLID design principles under an agile/scrum development process. 

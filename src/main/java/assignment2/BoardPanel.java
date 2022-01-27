package assignment2;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class BoardPanel extends GridPane implements EventHandler<ActionEvent> {

    private final View view;
    private final Board board;
    private Cell selectedpiece;
    private Cell destination;

    /**
     * Constructs a new GridPane that contains a Cell for each position in the board
     *
     * Contains default alignment and styles which can be modified
     * @param view
     * @param board
     */
    public BoardPanel(View view, Board board) {
	this.view = view;
	this.board = board;

	// Can modify styling
	this.setAlignment(Pos.CENTER);
	this.setStyle("-fx-background-color: #181a1b;");
	int size = 550;
	this.setPrefSize(size, size);
	this.setMinSize(size, size);
	this.setMaxSize(size, size);
	 
	setupBoard();
	updateCells();
    }


    /**
     * Setup the BoardPanel with Cells
     */
    private void setupBoard(){ 
	List <Cell> cells = this.board.getAllCells();
	for(Cell c: cells) {
	    this.add(c, c.getCoordinate().col, c.getCoordinate().row);

	    c.setOnAction(this);

	}// end of for loop

    }//end of setupBoard method 


    /**
     * Disables cells that are not applicable for the player
     */
    public void DisableCells() {

	for(Node c: this.getChildren()) {

	    c.setDisable(true);

	}    

    }//end of DisableCells Method 

    /**
     * Disables game controls when the computer is playing
     */
    public void DisableControls() {

	if(this.view.undoButton != null) {
	    view.undoButton.setDisable(true);

	}
	if(this.view.restartButton != null) {
	    view.restartButton.setDisable(true);
	}
	if(this.view.saveButton != null) {
	    view.saveButton.setDisable(true);
	}

    }

    /**
     * Enables valid pieces for the player to move
     */
    public void EnableValidPieces() {
	for(Node c: this.getChildren()) {

	    for(Cell cell: this.board.getPossibleCells()) {

		if(c.equals(cell)) {

		    c.setDisable(false);

		}
	    }
	}
    }

    /**
     * Enables valid moves for the player 
     */
    public void EnableValidMoves() {
	for(Node c: this.getChildren()) {

	    for(Cell cell: this.board.getPossibleDestinations(this.selectedpiece)) {

		if(c.equals(cell)) {

		    cell.setDisable(false);

		}
		cell.setOptionsColor();
	    }
	}
    }

    /**
     * Set settings back to original 
     */
    public void SetDefault() {

	this.view.setMessageLabel(String.format("[%s turn] Select your Player", this.board.getTurn().getType()));

	for(Cell cell: this.board.getAllCells()){

	    cell.setDefaultColor();
	}

    }

    /**
     * Updates the BoardPanel to represent the board with the latest information
     *
     * If it's a computer move: disable all cells and disable all game controls in view
     *
     * If it's a human player turn and they are picking a piece to move:
     *      - disable all cells
     *      - enable cells containing valid pieces that the player can move
     *      
     * If it's a human player turn and they have picked a piece to move:
     *      - disable all cells
     *      - enable cells containing other valid pieces the player can move
     *      - enable cells containing the possible destinations for the currently selected piece
     *
     * If the game is over:
     *      - update view.messageLabel with the winner ('MUSKETEER' or 'GUARD')
     *      - disable all cells
     */
    protected void updateCells(){ // TODO

	Boolean HumanTurn = this.view.model.isHumanTurn();

	Boolean decidingplayer = HumanTurn && this.selectedpiece == null;

	Boolean decidingmove = HumanTurn && this.selectedpiece != null && this.destination == null;


	if(!HumanTurn) {
		DisableCells();
		this.view.runMove();
		SetDefault();
		updateCells();
	}

	if(HumanTurn && decidingplayer) {
	    DisableCells();
	    EnableValidPieces();


	}

	if(HumanTurn && decidingmove) {
	    DisableCells();
	    EnableValidPieces();
	    EnableValidMoves();

	}

	if(this.board.isGameOver()) {
	    this.view.setMessageLabel(String.format("[%s  WON]", this.board.getWinner()));
	    //this.view.messageLabel.setDisable(true);
	    DisableCells();

	}


    }


    /**
     * Handles Cell clicks and updates the board accordingly
     * When a Cell gets clicked the following must be handled:
     *  - If it's a valid piece that the player can move, select the piece and update the board
     *  - If it's a destination for a selected piece to move, perform the move and update the board
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) { // TODO
	
	Cell c = (Cell) actionEvent.getSource();

	SetDefault();


	List<Cell> validpieces = this.board.getPossibleCells();

	if(validpieces.contains(c)) {
	    this.selectedpiece = this.board.getCell(c.getCoordinate());
	    this.selectedpiece.setAgentFromColor();
	    this.view.setMessageLabel(String.format("[%s turn] Piece Selected", this.board.getTurn().getType()));
	    //System.out.println(this.selectedpiece);
	    updateCells();
	}

	if(this.selectedpiece != null){
	    this.view.setMessageLabel(String.format("[%s turn] Make your Move", this.board.getTurn().getType()));
	    List<Cell> destinations = this.board.getPossibleDestinations(this.selectedpiece);
	    if(destinations.contains(c)) {
		this.destination = this.board.getCell(c.getCoordinate());
		this.destination.setAgentToColor();
		this.view.setMessageLabel(String.format("[%s turn] Done", this.board.getTurn().getType()));
		this.view.model.move(new Move(this.selectedpiece, this.destination));
		SetDefault();
		this.view.setUndoButton();
		this.selectedpiece = null;
		this.destination = null;

	    }
	    updateCells();
	}

    }//end of handle 

}	   

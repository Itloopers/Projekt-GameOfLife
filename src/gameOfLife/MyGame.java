package gameOfLife;

import java.util.Arrays;

class Board{
    private Cell[][] board;

    Board(int size, Cell ... injectedLivedCells){
        this.board = new Cell[size][size];
        for(Cell cell : injectedLivedCells){
            board[cell.getRow()][cell.getColumn()] = cell;
        }
    }

    @Override//toString() have empty field initialization
    public String toString(){
        StringBuilder printString = new StringBuilder();
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == null){
                    board[i][j] = new Cell(i,j);
                    board[i][j].setState(State.DEAD);
                }
                printString.append(board[i][j].getStateName());
            }
            printString.append(System.lineSeparator());
        }
        return printString.toString();
    }

    public void nextGen() {

        Arrays.stream(board).flatMap(board1 -> Arrays.stream(board1))
                .forEach(boardCell -> boardCell.getPair(board));

        //game rules 23/3
       Arrays.stream(board).flatMap(board1 -> Arrays.stream(board1)).forEach(boardCell -> {
           boardCell.changeState();
       });
    }

    public void changesOnBoard(){
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == null){
                    board[i][j] = new Cell(i,j);
                    board[i][j].setState(State.DEAD);
                }
                sb.append(board[i][j].stateChanges);
                sb.append("  ");
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
    }
}

class Cell{
    private State state;
    private int row, column;
    int pair = 0;
    int stateChanges = 0;

    Cell(int row, int column){
        this.state = State.LIVE;
        this.row = row;
        this.column = column;
    }

    static Cell live(int row, int column){
        return new Cell(row,column);
    }

    public String getStateName() {
        return state.getRepresentation();
    }

    public void setState(State state){
        this.state = state;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void changeState(){
        switch(this.pair){
            case 0:
                if(!this.state.equals(State.DEAD)) addStateChange();
                this.state=State.DEAD;
                break;
            case 1:
                if(!this.state.equals(State.DEAD)) addStateChange();
                this.state=State.DEAD;
                break;
            case 2: // stay LIVE
                break;
            case 3:
                if(!this.state.equals(State.LIVE)) addStateChange();
                this.state=State.LIVE;
                break;
            case 4:
                if(!this.state.equals(State.DEAD)) addStateChange();
                this.state=State.DEAD;
                break;
        }
    }

    private void addStateChange(){
        stateChanges++;
    }

    public void getPair(Cell[][] cells){
        pair = 0;
        for(int i=this.row-1; i<=this.row+1; i++) {
            if(i<0) i+=1;
            else if(i>cells.length-1) continue;
            for (int j =this.column-1; j<=this.column+1; j++) {
                if(j<0) j+=1;
                else if(j>cells.length-1) continue;
                if ((cells[i][j].state).equals(State.LIVE)){
                    this.pair++;
                    if(cells[i][j] == this) this.pair-=1;
                }
            }
        }
    }
}

enum State{
    LIVE("x"),
    DEAD(" ");

    private String representation;

    State(String representation){
        this.representation = representation.concat(" ");
    }

    public String getRepresentation(){
        return representation;
    }
}

public class MyGame {
    public static void main(String[] args) throws Exception {

        final int DELAY_TIME = 10;
        final int MOVE_TIME = 40;
        final int BOARD_SIZE = 10;

        Board board = new Board(BOARD_SIZE,Cell.live(2,0),Cell.live(2,1),Cell.live(2,2),Cell.live(1,2),Cell.live(0,1));


        for(int i=0; i<MOVE_TIME; i++){ //print and clear
            System.out.print(board);
            board.nextGen();
            Thread.sleep(DELAY_TIME);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }

        board.changesOnBoard();
    }
}

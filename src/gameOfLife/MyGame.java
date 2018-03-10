package gameOfLife;


import java.io.IOException;

class Board{
    private Cell[][] board;

    Board(int size, Cell ... cell){
        this.board = new Cell[size][size];
        for(Cell cell1 : cell){
            board[cell1.getRow()][cell1.getColumn()] = cell1;
        }
    }

    @Override
    public String toString(){
        String printString = "";
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == null){
                    board[i][j] = new Cell(i,j);
                    board[i][j].setState(State.DEAD);
                }
                printString = printString.concat(board[i][j].getStateName());
            }
            printString = printString.concat(System.lineSeparator());
        }
        return printString;
    }

    void nextGen() {
        //toString() have empty field initialization
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].getPair(board);
                //System.out.print(board[i][j].pair);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                switch(board[i][j].pair) {
                    case 0: board[i][j].setState(State.DEAD);
                    break;
                    case 1: board[i][j].setState(State.DEAD);
                    break;
                    case 2: //board[i][j].setState(State.LIVE);
                    break;
                    case 3: board[i][j].setState(State.LIVE);
                    break;
                    case 4: board[i][j].setState(State.DEAD);
                    break;

                }
            }
        }
    }
}

class Cell{
    private State state;
    private int row, column;
     int pair = 0;

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
    LIVE("x "),
    DEAD("  ");

    private String representation;

    State(String representation){
        this.representation = representation;
    }

    public String getRepresentation(){
        return representation;
    }
}

public class MyGame {
    public static void main(String[] args) throws Exception {
        Board board = new Board(30,Cell.live(2,0),Cell.live(2,1),Cell.live(2,2),Cell.live(1,2),Cell.live(0,1));




        for(int i=0; i<100; i++){
            System.out.print(board);
            board.nextGen();
            Thread.sleep(1000);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
    }
}

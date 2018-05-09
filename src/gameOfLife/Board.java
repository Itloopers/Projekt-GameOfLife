package gameOfLife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;

class Board{
    private Cell[][] board;

    Board(int size, Set<Cell> injectedLivedCells){
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

    public ArrayList returnListOfChanges(){
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(board).flatMap(innerboard -> Arrays.stream(innerboard)).forEach(boarCell -> {
            if(boarCell.stateChanges!=0)list.add(boarCell.stateChanges);
        });
        return list;
    }
}

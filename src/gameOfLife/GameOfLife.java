package gameOfLife;

public class GameOfLife {
    public static void main(String[] args) throws Exception {

        final int DELAY_TIME = 10;
        //final int MOVE_TIME = 100;
        final int BOARD_SIZE = 45;

        //final Cell[] GLIDER = {Cell.live(2,0),Cell.live(2,1),Cell.live(2,2),Cell.live(1,2),Cell.live(0,1)};
        System.out.println("Czas działania;");
        int time = GameUtils.getTimeFromUser();
        System.out.println("Komorki max(30,30) -> start");
        Board board = new Board(BOARD_SIZE, GameUtils.getCellsFromUser());

        for(int i=0; i<time; i++){ //print and clear
            System.out.print(board);
            board.nextGen();
            Thread.sleep(DELAY_TIME);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        Thread.sleep(DELAY_TIME*5);
        board.changesOnBoard();
        System.out.println("suma: "+GameUtils.suma(board.returnListOfChanges())+
                "\nsrednia: "+GameUtils.srednia(board.returnListOfChanges())+ "\nmoda: "
                +GameUtils.moda(board.returnListOfChanges())+"\nmediana: "+GameUtils.mediana(board.returnListOfChanges()));
    }
}

package gameOfLife;

abstract class Oscilators {
    public static Cell[] blinker() {
        return new Cell[]{Cell.live(1, 1), Cell.live(1, 2), Cell.live(1, 3)};
    }
}
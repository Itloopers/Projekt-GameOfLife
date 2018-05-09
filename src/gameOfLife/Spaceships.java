package gameOfLife;

abstract class Spaceships{

    public static Cell[] glider(){
        return new Cell[]{Cell.live(2,0),Cell.live(2,1),Cell.live(2,2),Cell.live(1,2),Cell.live(0,1)};
    }
}
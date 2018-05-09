package gameOfLife;

abstract class StillLifese{

    public static Cell[] block(){
        return new Cell[]{Cell.live(1,1),Cell.live(1,2),Cell.live(2,1),Cell.live(2,2)};
    }

    public static Cell[] beehive(){
        return new Cell[]{Cell.live(2,1),Cell.live(3,1),Cell.live(1,2),Cell.live(4,2),Cell.live(2,3),Cell.live(3,3)};
    }
}
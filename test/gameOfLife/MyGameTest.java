package gameOfLife;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MyGameTest {

    Board line3;
    @BeforeEach
    void setUp(){
        line3 = new Board(3,Cell.live(1,1));
    }


    @DisplayName("Dead Cell")
    @Test
    void deadCell(){
        System.out.println(line3);
        line3.nextGen();

        assertTrue(line3.toString().equals("      "+System.lineSeparator()+
                                            "      "+System.lineSeparator()+
                                            "      "+System.lineSeparator()));
    }


    @Test
    void ShouldBlockStructureStayTheSame(){
        Board board = new Board(2, Cell.live(0,0),Cell.live(0,1),Cell.live(1,0),Cell.live(1,1));
        System.out.println(board);
        board.nextGen();
        assertTrue(board.toString().equals("x x "+System.lineSeparator()+"x x "+System.lineSeparator()));
    }


    @Test
    void ShouldGliderMove(){
        Board glider = new Board(4,Cell.live(2,0),Cell.live(2,1),Cell.live(2,2),Cell.live(1,2),Cell.live(0,1));
        System.out.println(glider);
        glider.nextGen(); //Flying glider
        assertTrue(glider.toString().equals("        "+System.lineSeparator()+
                                            "x   x   "+System.lineSeparator()+
                                            "  x x   "+System.lineSeparator()+
                                            "  x     "+System.lineSeparator()));
    }

}
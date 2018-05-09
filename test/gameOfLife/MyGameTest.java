package gameOfLife;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MyGameTest {

    Board line3;
    @BeforeEach
    void setUp(){
        HashSet<Cell> line3Set = new HashSet<>();
        line3Set.add(Cell.live(1,1));
        line3 = new Board(3,line3Set);
    }


    @DisplayName("should Cell Die")
    @Test
    void DeadCell(){
        System.out.println(line3);
        line3.nextGen();

        assertTrue(line3.toString().equals("      "+System.lineSeparator()+
                                            "      "+System.lineSeparator()+
                                            "      "+System.lineSeparator()));
    }


    @Test
    void ShouldBlockStructureStayTheSame(){
        HashSet<Cell> rectangle = new HashSet<>();
        rectangle.addAll(Arrays.asList(Cell.live(0, 0), Cell.live(0, 1), Cell.live(1, 0), Cell.live(1, 1)));

        Board board = new Board(2, rectangle);
        System.out.println(board);
        board.nextGen();
        assertTrue(board.toString().equals("x x "+System.lineSeparator()+"x x "+System.lineSeparator()));
    }


    @Test
    void ShouldGliderMove(){
        HashSet<Cell> gliderCells = new HashSet<>();
        gliderCells.addAll(Arrays.asList(Cell.live(2,0),Cell.live(2,1),Cell.live(2,2),Cell.live(1,2),Cell.live(0,1)));
        Board glider = new Board(4,gliderCells);
        System.out.println(glider);
        glider.nextGen(); //Flying glider
        assertTrue(glider.toString().equals("        "+System.lineSeparator()+
                                            "x   x   "+System.lineSeparator()+
                                            "  x x   "+System.lineSeparator()+
                                            "  x     "+System.lineSeparator()));
    }

}
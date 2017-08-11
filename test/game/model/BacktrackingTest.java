/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jhonathan
 */
public class BacktrackingTest {
    
    
    
    int[][] wrong = {
        {0,4,4,  3,0,0,  0,0,6},
        {6,0,0,  1,0,9,  0,0,0},
        {7,0,0,  0,0,0,  0,4,9},
 
        {0,0,1,  0,8,5,  4,6,0},
        {0,0,6,  2,9,3,  7,0,0},
        {0,7,5,  4,1,0,  9,0,0},
             
        {4,6,0,  0,0,0,  0,0,7},
        {0,0,0,  9,0,4,  0,0,1},
        {2,0,0,  0,0,7,  8,0,0}
    };
    
    int[][] correct = {
        {0,0,4,  3,0,0,  0,0,6},
        {6,0,0,  1,0,9,  0,0,0},
        {7,0,0,  0,0,0,  0,4,9},
 
        {0,0,1,  0,8,5,  4,6,0},
        {0,0,6,  2,9,3,  7,0,0},
        {0,7,5,  4,1,0,  9,0,0},
             
        {4,6,0,  0,0,0,  0,0,7},
        {0,0,0,  9,0,4,  0,0,1},
        {2,0,0,  0,0,7,  8,0,0}
    };
    
    File patron = new File("src/sources/patrones/traditionalpattern.txt");
    SudokuParser pattern = new SudokuParser(patron);
    String str1 = pattern.getStringsList().get(0);
    
    public BacktrackingTest() {
    
    }
    
   

    /**
     * Test of solve method, of class Backtracking.
     */
    @Test
    public void testSolveFalse() {
        
        System.out.println("Solve Incorrecta");
        SudokuModel board = new SudokuModel();
        board.setup(wrong, str1);
        int row = 0;
        int col = 0;
        Backtracking instance = new Backtracking(board);
        instance.solve(row, col);
        boolean expResult = false;
        boolean result = instance.isSolved();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of solve method, of class Backtracking.
     */
    @Test
    public void testSolveTrue() {
        
        System.out.println("Solve Correcta");
        SudokuModel board = new SudokuModel();
        board.setup(correct, str1);
        int row = 0;
        int col = 0;
        Backtracking instance = new Backtracking(board);
        instance.solve(row,col);
        boolean expResult = true;
        boolean result = instance.isSolved();
        assertEquals(expResult, result);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

import java.util.Random;

/**
 *
 * @author Jhonathan
 */
public class generatorEasy extends Strategy {

    int[][] puzzle = {
        {1,2,3,  4,5,6,  7,8,9},
        {4,5,6,  7,8,9,  1,2,3},
        {7,8,9,  1,2,3,  4,5,6},
 
        {2,3,4,  5,6,7,  8,9,1},
        {5,6,7,  8,9,1,  2,3,4},
        {8,9,1,  2,3,4,  5,6,7},
             
        {3,4,5,  6,7,8,  9,1,2},
        {6,7,8,  9,1,2,  3,4,5},
        {9,1,2,  3,4,5,  6,7,8}
    };
    int[][] board = {
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
 
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
             
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0}
    };
    Random ram=new Random();

    public generatorEasy() {
        generator();
    }
    
    
    
    
    @Override
    public int[][] generator() {
        int option=0;
        int [][] puzzle = null;
        option=(int) ram.nextInt(9);
        
        switch(option){
            case 0:
                invertRow();
                invertCol();
                invertZoneCol();
                puzzle=removeNumber();
                break;
            case 1:
                invert();
                invertRow2();
                invertCol2();
                puzzle=removeNumber2();
                break;
            case 2:
                invertRow();
                invertZoneRow();
                invertCol();
                puzzle=removeNumber();
                break;
            case 3:
                invertCol();
                invertZoneCol();
                invertRow();
                puzzle=removeNumber();
                break;
            case 4:
                invertZoneCol();
                invertZoneRow();
                invertRow();
                puzzle=removeNumber();
                break;
            case 5:
                invertCol();
                invertZoneRow();
                puzzle=removeNumber();
                break;
            case 6:
                invert();
                invertZoneCol2();
                invertZoneRow2();
                invertRow();
                puzzle=removeNumber2();
                break;
            case 7:
                invert();
                invertRow2();
                invertZoneCol2();
                invertZoneRow2();
                invertCol2();
                puzzle=removeNumber2();
                break;
            case 8:
                invert();
                invertCol2();
                invertCol2();
                invertZoneRow2();
                invertZoneCol2();
                invertZoneRow2();
                puzzle=removeNumber2();
                break;
        }
        return puzzle;
    }
    
    public void invert(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[j][i]=puzzle[i][j];
            }
        }
    }
    
    public void invertRow(){
        int  zona,row1 = 0,row2 = 0,aux;
        
        zona=(int) ram.nextInt(3);
        
        switch (zona) {
            case 0:
                row1= (int) ram.nextInt(3);
                row2= (int) ram.nextInt(3);
                break;
            case 1:
                row1= 5;
                row2= 4;
                break;
            case 2:
                row1=6;
                row2= 8;
                break;
            default:
                break;
        }
        
        for (int j = 0; j < 9; j++) {
            aux=puzzle[row1][j];
            puzzle[row1][j]=puzzle[row2][j];
            puzzle[row2][j]=aux;
        }
    }
    
    public void invertRow2(){
        int  zona,row1 = 0,row2 = 0,aux;
        
        zona=(int) ram.nextInt(3);
        
        switch (zona) {
            case 0:
                row1= (int) ram.nextInt(3);
                row2= (int) ram.nextInt(3);
                break;
              case 1:
                row1= 3;
                row2= 4;
                break;
            case 2:
                row1=6;
                row2= 7;
                break;
            default:
                break;
        }
        
        for (int j = 0; j < 9; j++) {
            aux=board[row1][j];
            board[row1][j]=board[row2][j];
            board[row2][j]=aux;
        }
    }
    
    public void invertCol(){
        int  zona,col1 = 0,col2 = 0,aux;
        
        zona=(int) ram.nextInt(3);
        
        switch (zona) {
            case 0:
                col1= (int) ram.nextInt(3);
                col2= (int) ram.nextInt(3);
                break;
            case 1:
                col1= 5;
                col2= 4;
                break;
            case 2:
                col1=6;
                col2= 8;
                break;
            default:
                break;
        }
        
        for (int i = 0; i < 9; i++) {
            aux=puzzle[i][col1];
            puzzle[i][col1]=puzzle[i][col2];
            puzzle[i][col2]=aux;
        }
    }
    
        public void invertCol2(){
        int  zona,col1 = 0,col2 = 0,aux;
        
        zona=(int) ram.nextInt(3);
        
        switch (zona) {
            case 0:
                col1= (int) ram.nextInt(3);
                col2= (int) ram.nextInt(3);
                break;
             case 1:
                col1= 3;
                col2= 4;
                break;
            case 2:
                col1=7;
                col2= 8;
                break;
            default:
                break;
        }
        
        for (int i = 0; i < 9; i++) {
            aux=board[i][col1];
            board[i][col1]=board[i][col2];
            board[i][col2]=aux;
        }
    }
        
        public void invertZoneRow(){
            int zona, row1 = 0, row2 = 0, row3 = 0,row4 = 0,row5 = 0,row6 = 0, aux;
             zona=(int) ram.nextInt(3);
             
             switch(zona){
                 case 0:
                     row1=0;
                     row2=1;
                     row3=2;
                     row4=3;
                     row5=4;
                     row6=5;
                     break;
                 case 1:
                     row1=3;
                     row2=4;
                     row3=5;
                     row4=6;
                     row5=7;
                     row6=8;
                     break;
                 case 2:
                     row1=6;
                     row2=7;
                     row3=8;
                     row4=0;
                     row5=1;
                     row6=2;
                     break;
             }
           
             for (int j = 0; j < 9; j++) {
                aux=puzzle[row1][j];
                puzzle[row1][j]=puzzle[row4][j];
                puzzle[row4][j]= aux;
                
                aux=puzzle[row2][j];
                puzzle[row2][j]=puzzle[row5][j];
                puzzle[row5][j]= aux;
                
                aux=puzzle[row3][j];
                puzzle[row3][j]=puzzle[row6][j];
                puzzle[row6][j]= aux;
            }
        }
        
        public void invertZoneRow2(){
            int zona, row1 = 0, row2 = 0, row3 = 0,row4 = 0,row5 = 0,row6 = 0, aux;
             zona=(int) ram.nextInt(3);
             
             switch(zona){
                 case 0:
                     row1=0;
                     row2=1;
                     row3=2;
                     row4=3;
                     row5=4;
                     row6=5;
                     break;
                 case 1:
                     row1=3;
                     row2=4;
                     row3=5;
                     row4=6;
                     row5=7;
                     row6=8;
                     break;
                 case 2:
                     row1=6;
                     row2=7;
                     row3=8;
                     row4=0;
                     row5=1;
                     row6=2;
                     break;
             }
           
             for (int j = 0; j < 9; j++) {
                aux=board[row1][j];
                board[row1][j]=board[row4][j];
                board[row4][j]= aux;
                
                aux=board[row2][j];
                board[row2][j]=board[row5][j];
                board[row5][j]= aux;
                
                aux=board[row3][j];
                board[row3][j]=board[row6][j];
                board[row6][j]= aux;
            }
        }
        
        public void invertZoneCol(){
            int zona, col1 = 0, col2 = 0, col3 = 0,col4 = 0,col5 = 0,col6 = 0, aux;
             zona=(int) ram.nextInt(3);
             
             switch(zona){
                 case 0:
                     col1=0;
                     col2=1;
                     col3=2;
                     col4=3;
                     col5=4;
                     col6=5;
                     break;
                 case 1:
                     col1=3;
                     col2=4;
                     col3=5;
                     col4=6;
                     col5=7;
                     col6=8;
                     break;
                 case 2:
                     col1=6;
                     col2=7;
                     col3=8;
                     col4=0;
                     col5=1;
                     col6=2;
                     break;
             }
           
             for (int j = 0; j < 9; j++) {
                aux=puzzle[j][col1];
                puzzle[j][col1]=puzzle[j][col4];
                puzzle[j][col4]= aux;
                
                aux=puzzle[j][col2];
                puzzle[j][col2]=puzzle[j][col5];
                puzzle[j][col5]= aux;
                
                aux=puzzle[j][col3];
                puzzle[j][col3]=puzzle[j][col6];
                puzzle[j][col6]= aux;
            }
        }
         
        public void invertZoneCol2(){
            int zona, col1 = 0, col2 = 0, col3 = 0,col4 = 0,col5 = 0,col6 = 0, aux;
             zona=(int) ram.nextInt(3);
             
             switch(zona){
                 case 0:
                     col1=0;
                     col2=1;
                     col3=2;
                     col4=3;
                     col5=4;
                     col6=5;
                     break;
                 case 1:
                     col1=3;
                     col2=4;
                     col3=5;
                     col4=6;
                     col5=7;
                     col6=8;
                     break;
                 case 2:
                     col1=6;
                     col2=7;
                     col3=8;
                     col4=0;
                     col5=1;
                     col6=2;
                     break;
             }
           
             for (int j = 0; j < 9; j++) {
                 aux=board[j][col1];
                board[j][col1]=board[j][col4];
                board[j][col4]= aux;
                
                aux=board[j][col2];
                board[j][col2]=board[j][col5];
                board[j][col5]= aux;
                
                aux=board[j][col3];
                board[j][col3]=board[j][col6];
                board[j][col6]= aux;
            }
        }
        
        public int[][] removeNumber (){
            int space=40, row, col;
            
            space=81-space;
           
            for (int i = 0; i <= space; i++) {
                row=(int) ram.nextInt(9);
                col=(int) ram.nextInt(9);
                if(row==col){
                    row=(int) ram.nextInt(9);
                    col=(int) ram.nextInt(9);
                } 
                puzzle[row][col]=0;
            }
               return puzzle;
            
        }
        
        public int[][] removeNumber2 (){
            int space=40, row, col;
            
            space=81-space;
           
            for (int i = 0; i <= space; i++) {
                row=(int) ram.nextInt(9);
                col=(int) ram.nextInt(9);
                if(row==col){
                    row=(int) ram.nextInt(9);
                    col=(int) ram.nextInt(9);
                } 
                board[row][col]=0;
            }
               return board;
            
        }
        
       
           
           
    
}
 
         
           

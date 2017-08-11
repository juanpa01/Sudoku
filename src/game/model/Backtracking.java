/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

/**
 *
 * @author ADMIN
 */
public class Backtracking {
    
    private SudokuModel model;
    
    public Backtracking (SudokuModel model){
        this.model = model;
    }
    public boolean isSolved(){
        return model.isSolved();
       
    }
    private boolean isValid(int row, int col, int num) {
        int rowStart = (row/3)*3;
        int colStart = (col/3)*3;
        int i;
        
        for(i=0;i<9;i++){
            if(model.cells[row][i].getSolved() && (model.cells[row][i].getValue()== num )) return false;
            if(model.cells[i][col].getSolved() && (model.cells[i][col].getValue()== num )) return false;
            if(model.cells[rowStart + (i%3)][colStart + (i/3)].getSolved() && (model.cells[rowStart + (i%3)][colStart + (i/3)].getValue() == num )) return false;
        }
        
        return true;
    }
    
    public boolean solve(int row, int col){
            //solucion por backtraking
        int i;
        
        if(row < 9 && col < 9){//verifica que columna y fila esten dentro del sudoku
            if(model.cells[row][col].getSolved()){//la celda esta solucionada?
                if((col+1)<9){//si lo esta, verifico que la columna siga siendo menor a 9 y...
                    return solve(row,col+1);// paso a la siguiente celda en la columna
                }else if((row+1)<9){ //si la columna es mayor a 9, verifico la fila, si es menor a 9
                     return solve(row+1,0);//si lo esta, paso a la siguiente celda en la fila
                }else
                      return true;// ya estan todas las celdas solucionadad, devuelvo vervadero
        
            }else{
            for(i=0;i<9;i++){
                if(isValid(row,col,i+1)){//verifico que el numero a ingresar no se encuentre en la fila, ni en la columna
                    model.cells[row][col].replace(i+1); //si el numero no esta, lo agrego y dejo la celda como solucionada
                    
                    if((col+1)<9){
                        if(solve(row,col+1)){
                            return true;
                        }else{
                            model.cells[row][col]= new Cell(row, col);
                        }
                    }else if((row+1)<9){
                      if(solve(row+1,0)){
                            return true;
                        }else{
                            model.cells[row][col]= new Cell(row, col);
                        }
                    }else
                        return true;
                }
            }
        }
        return false;
    }
        else
            return true;
    
    }

}

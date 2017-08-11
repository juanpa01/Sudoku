
package game.model;

public class SudokuModel {
    
    public Cell[][] cells = new Cell[9][9];//de tipo Cell
    
     
    public SudokuModel() {
        
        // inicializa celdas
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                cells[i][j] = new Cell(i, j);
         
        
        // adiciona observadores
        //añade un observador tipo cells a cada celda 
    }
 
    // fija valores conocidos
    public void setup(int[][] puzzle, String pattern) {//recibe el sudoku
         agroup(pattern); // setea el patron
        
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                cells[i][j].addObserver(cells); //adiciona observadores
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] != 0) {//si ya hay un número para esa celda
                    cells[i][j].setValue(puzzle[i][j]);//lo pone en la celda, quita los otros posibles valores
                }
            }
        }
        
        
    }
    
    public void setup(String puzzle, String pattern) {//lee el sudoku de la forma como se lo mandamos en el archivo
        agroup(pattern); // setea el patron
        
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                cells[i][j].addObserver(cells); //adiciona observadores
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                
                if (puzzle.charAt(i * 9 + j) != '.') {//matriz aplanada
                    cells[i][j].setValue(puzzle.charAt(i * 9 + j) - '0');//lo pone en la celda
                    
                }
                
            }
        }
        
    }
    
    public void agroup(String pattern){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setGroup(pattern.charAt(i * 9 + j) - '0');
            }
        }
    }
    
    
    
    public boolean isSolved(){
        //Comprobar filas;
        int cont = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cont += cells[i][j].getValue(); 
            }
            if(cont == 45){
                cont = 0;
            }
            else{
                return false;
            }
        }
        return true;
        
    }
    
    public int getCellValue(int i, int j) {
        return cells[i][j].getValue();
    }
    
    public Cell[][] getCells(){
        return cells;
    }
     
    public String toString(){//cómo debe imprimir
        String str = "+---+---+---+---+---+---+---+---+---+\n";
 
       for (int i = 0; i < 9; i++) {
            str += "|";
            for (int j = 0; j < 9; j++) {
                str += String.format(" %d |", getCellValue(i,j));
            }
            str += "\n+---+---+---+---+---+---+---+---+---+\n";
       }
       return str;
    }
        
    
}
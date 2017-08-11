/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

import java.util.ArrayList;
import org.jacop.core.*;
import org.jacop.constraints.*;
import org.jacop.search.*;

/**
 *
 * @author ADMIN
 */
public class Restriction{
        int[][] description = {
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
        
        public Restriction(SudokuModel model){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    description[i][j] = model.cells[i][j].getValue();
                }
            }
            
        }
        
        IntVar[][] elements;
        
	public int[][] getMatriz(){
            return description;
        }
        public void setMatriz(int[][] mat){
            this.description = mat;
        }
        public void printMatriz(){
            for(int i=0; i<description.length; i++){
                for(int j=0; j<description.length; j++){
                    System.out.print(description[i][j]);
                }
                System.out.println();
            }
        }
        public void printElements(){
            for(int i=0; i<elements.length; i++){
                for(int j=0; j<elements.length; j++){
                    System.out.print(elements[i][j]);
                }
                System.out.println();
            }
        }
        
        public void solve(){  
            
            ArrayList<IntVar> vars = new ArrayList<IntVar>();
            Store store = new Store();
            this.elements = new IntVar[9][9];
            
            for (int i = 1; i<=9; i++){
		for (int j = 1; j<=9; j++)
                    if (description[i-1][j-1] == 0) {
			elements[i-1][j-1] = new IntVar(store, "f" + i + j, 1,9);
			vars.add(elements[i-1][j-1]);
			}
                    else{
			elements[i-1][j-1] = new IntVar(store, "f" + i + j,
			description[i-1][j-1], description[i-1][j-1]);
                        vars.add(elements[i-1][j-1]);
                    }
            }
            
            //creando la restriccion para las filas
            for (int i = 0; i <9; i++){
		store.impose(new Alldistinct(elements[i]));
            }

            //creando la restriccion para las columnas.
            for (int j = 0; j <9; j++) {
		IntVar[] column = new IntVar[9];
		for (int i = 0; i <9; i++){
		     column[i] = elements[i][j];
                }
                
                store.impose(new Alldistinct(column));
            }
                
            //creando las restricciones para las cajas.
            for (int i = 0; i<3; i++){
		for (int j = 0; j<3; j++) {

                    ArrayList<IntVar> block = new ArrayList<IntVar>();
                    for (int k = 0; k < 3; k++){
			for (int m = 0; m<3; m++){
                            block.add(elements[i * 3 + k][j * 3 + m]);
                        }
                        store.impose(new Alldistinct(block));
                    }
                                
                }
            }
            
            //ComparatorVariable varSelect; 
            Search<IntVar> label = new DepthFirstSearch<IntVar>(); 
            SelectChoicePoint<IntVar> select = new SimpleMatrixSelect<IntVar>(elements,null, new IndomainMin<IntVar>());
            boolean result = label.labeling(store, select);
        
            Converter();
        }
        //Convierte la matriz de variables en la matriz resultante
        public void Converter(){
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    description[i][j] = elements[i][j].value();
                }
            }
        }
        
        
}



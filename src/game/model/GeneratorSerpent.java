/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

import java.io.File;
import java.util.Random;

/**
 *
 * @author ADMIN
 */
public class GeneratorSerpent{
    
    private String sudo;
    private File fin;
    private File patron;
    Random ram = new Random();

    public  GeneratorSerpent() {
        int op;
        op = (int) ram.nextInt(7);
        
        switch(op){
            case 0:
                sudo = "sources/fondos/serpeEasy1.jpg";
                fin = new File("src/sources/configuraciones-serpent/serpeEasy1.txt");
                patron = new File("src/sources/patrones/patternEasy1.txt");
                break;
            case 1:
                sudo = "sources/fondos/serpeEasy2.jpg";
                fin = new File("src/sources/configuraciones-serpent/serpeEasy2.txt");
                patron = new File("src/sources/patrones/patternEasy2.txt");
                break;
            case 2:
                sudo = "sources/fondos/serpeEasy3.jpg";
                fin = new File("src/sources/configuraciones-serpent/serpeEasy3.txt");
                patron = new File("src/sources/patrones/patternEasy3.txt");
                break;
            case 3:
                sudo = "sources/fondos/serpeEasy4.jpg";
                fin = new File("src/sources/configuraciones-serpent/serpeEasy4.txt");
                patron = new File("src/sources/patrones/patternEasy4.txt");
                
                break;
            case 4:
                sudo= "sources/fondos/serpeEasy5.jpg";
                fin= new File("src/sources/configuraciones-serpent/serpeEasy5.txt");
                patron= new File("src/sources/patrones/patternEasy5.txt");
                break;
            case 5:
                sudo= "sources/fondos/serpeEasy6.jpg";
                fin= new File("src/sources/configuraciones-serpent/serpeEasy6.txt");
                patron= new File("src/sources/patrones/patternEasy6.txt");
                break;
            case 6:
                sudo = "sources/fondos/serpe3.jpg";
                fin = new File("src/sources/configuraciones-serpent/serpe2.txt");
                patron = new File("src/sources/patrones/pattern2.txt");
                break;
        }
    }

    public String getSudo() {
        return sudo;
    }

    public File getFin() {
        return fin;
    }

    public File getPatron() {
        return patron;
    }  

}

    
   


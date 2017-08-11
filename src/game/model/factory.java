/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

/**
 *
 * @author Jhonathan
 */
public class factory {
    protected String s;

    public factory(String s) {
        this.s = s;
    }
    
    public Strategy sudoku(){
        if(s.equalsIgnoreCase("easy")){
            return new easy().level();
        }else if(s.equalsIgnoreCase("medium")){
                return new medium().level();
             }else{
                return new hard().level();
            }
    }
}

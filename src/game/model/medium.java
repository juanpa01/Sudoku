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
public class medium extends conexion{
    Strategy estrategia;
    
    @Override
    public Strategy level() {
        return estrategia = new generatorMedium();
    }
    
}

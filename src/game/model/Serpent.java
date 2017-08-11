/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model;

import java.io.File;
/**
 *
 * @author ADMIN
 */
public class Serpent {
    
    private String Sudo;
    private File fin;
    private File patron;

    public Serpent(String Sudo, File fin, File patron) {
        this.Sudo = Sudo;
        this.fin = fin;
        this.patron = patron;
    }

    public String getSudo() {
        return Sudo;
    }

    public File getFin() {
        return fin;
    }

    public File getPatron() {
        return patron;
    }
    
}

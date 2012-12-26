/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util;

import java.util.ArrayList;

/**
 *
 * @author Carlos Melo
 */
public class Acumulador {
   private int acc;

    public Acumulador() {
    this.acc=0;
        }
    public int add(int valor){
        return this.acc=+valor;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }
   
    
}

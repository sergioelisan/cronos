/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import senai.cronos.entidades.UnidadeCurricular;

/**
 *
 * @author Carlos Melo
 */
public class FileText {
    public  List<UnidadeCurricular> importar(String arquivo) {
   List<UnidadeCurricular> uc=new ArrayList<>();
         
        
            File file=new File(arquivo);
            if(file.exists()){
            try {
                ObjectInputStream ob=new ObjectInputStream(new FileInputStream(arquivo));
                    try {
                        uc=(List<UnidadeCurricular>)ob.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FileText.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } catch (IOException ex) {
                Logger.getLogger(FileText.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
              System.out.println("aqui");
            }
            
               
               
            
         return uc;
      
  }

    public FileText() {
        
        
    }
 
  public void exportar( String nome, List<UnidadeCurricular> uc){
    
    ObjectOutputStream ob;
        try {
            ob = new ObjectOutputStream(new FileOutputStream(nome+".tur"));
            ob.writeObject(uc);
            ob.close();
        } catch (IOException ex) {
            Logger.getLogger(FileText.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  


  }
}

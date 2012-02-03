/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import senai.cronos.Main;
import senai.cronos.Splash;
import senai.cronos.gui.Update;
import java.net.HttpURLConnection;
/**
 *
 * @author Carlos Melo
 */
public class UpdateCronos {
    int baixado;
Update u=new Update();
  final Main m=new Main(); 
public  File gravaArquivoDeURL(URL url,String pathLocal, String verAntes,String verDepois) {  
    
   
    try { 
        
 String nomeArquivoLocal = "\\update.exe";  

    JDialog dia=new JDialog();
    dia.setBounds(400, 300, 450,250 );
    
    dia.setContentPane(u);
    dia.setVisible(true);
    dia.toFront();
    JButton b=new JButton();
    JProgressBar dpb = new JProgressBar(0, 100);
    dia.add(BorderLayout.CENTER, dpb);
     
    u.setAtualiza("Atualizando da versão "+verAntes+" para "+verDepois);
    System.out.print("baixando|=");
    
    
    InputStream is = url.openStream(); 
    int i=0;
        FileOutputStream fos = new FileOutputStream(pathLocal+"\\"+nomeArquivoLocal);  
        int umByte = 0;  
      
        while ((umByte = is.read()) != -1){ 
            
            fos.write(umByte);
            
            i++;
            if(i>10000){
                baixado++;
                dpb.setValue(baixado);
                System.out.print("=");
                i=0;
               if(!dia.isVisible()) break;
            u.setProgresso(baixado);
          
            }
        } 
        u.setProgresso(100);
        is.close();  
        fos.close(); 
        
        System.out.println(">download, ok!!!");
        u.setVisible(false);
      dia.dispose();
        
        return new File(pathLocal+nomeArquivoLocal);
        
        
                
        
          
    } catch (Exception e) {  
      
        e.printStackTrace();  
    } 
     
    
      
  return null;    
}



    public UpdateCronos() {
        this.baixado=0;
    }
    
}

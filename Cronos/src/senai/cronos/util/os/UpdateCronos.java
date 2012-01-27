/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util.os;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import senai.cronos.Main;
import senai.cronos.Splash;
import senai.cronos.gui.Update;

/**
 *
 * @author Carlos Melo
 */
public class UpdateCronos {
    int baixado;
Update u=new Update();
    public  File gravaArquivoDeURL(String stringUrl, String pathLocal) {  
    try {  
final Main m=new Main();
    JDialog dia=new JDialog();
    dia.setBounds(400, 300, 450,250 );
    dia.setContentPane(u);
    dia.setVisible(true);
    dia.toFront();
    JButton b=new JButton();
    JProgressBar dpb = new JProgressBar(0, 100);
    dia.add(BorderLayout.CENTER, dpb);
    URL url = new URL(stringUrl); 
    System.out.print("baixando|=");
    String nomeArquivoLocal = "\\update.exe";  
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
        
        System.out.println("download, ok!!!");
        u.setVisible(false);
      
        return new File(pathLocal+nomeArquivoLocal);
        
          
    } catch (Exception e) {  
      
        e.printStackTrace();  
    }  
         
    return null;  
}

    public UpdateCronos() {
        this.baixado=0;
    }

  

    public int getBaixado() {
        return baixado;
    }

    
}

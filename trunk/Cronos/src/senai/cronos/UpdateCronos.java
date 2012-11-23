/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import senai.cronos.gui.Update;

/**
 *
 * @author Carlos Melo
 */
public class UpdateCronos {

    private int baixado;
    private Update u = new Update();
    private boolean s = false;

    public File gravaArquivoDeURL(URL url, String pathLocal, String verAntes, String verDepois) {
        try {
            String nomeArquivoLocal = "\\update.exe";
            JDialog dia = new JDialog();
            dia.setBounds(400, 300, 450, 250);
            dia.setContentPane(u);
            dia.setVisible(true);
            dia.toFront();
            JProgressBar dpb = new JProgressBar(0, 100);
            dia.add(BorderLayout.CENTER, dpb);

            u.setAtualiza("Atualizando da versão " + verAntes + " para " + verDepois);
            System.out.print("baixando|=");
            FileOutputStream fos;
            try (InputStream is = url.openStream()) {
                int i = 0;
                fos = new FileOutputStream(pathLocal + "\\" + nomeArquivoLocal);
                int umByte;
                while ((umByte = is.read()) != -1) {

                    fos.write(umByte);

                    i++;
                    if (i > 10000) {
                        baixado++;
                        dpb.setValue(baixado);
                        System.out.print("=");
                        i = 0;
                        if (!dia.isVisible()) {
                            s = false;
                            break;
                        }
                        u.setProgresso(baixado);


                    }
                }
                u.setProgresso(100);
            }
            fos.close();
            s = true;

            System.out.println(">download, ok!!!");
            u.setVisible(false);
            dia.dispose();

            return new File(pathLocal + nomeArquivoLocal);

        } catch (Exception e) {
        }

        return null;
    }

    public boolean isS() {
        return s;
    }

    public void setS(boolean s) {
        this.s = s;
    }

    public UpdateCronos() {
        this.baixado = 0;
    }
}

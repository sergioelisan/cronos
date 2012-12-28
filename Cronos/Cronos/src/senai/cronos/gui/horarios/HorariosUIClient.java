package senai.cronos.gui.horarios;

import java.awt.Component;

/**
 *
 * interface usada como contrato para permitir que os submodulos de HorariosUI
 * possam usar os serviços providos por suas classes internas.
 * 
 * @author sergio lisan e carlos melo
 */
public interface HorariosUIClient {
    
    /**
     * Como os modulos sao JPanels, esse metodo garante uma interface para que as 
     * classes internas de HorariosUI possam usar os metodos de JPanel
     * 
     * @param cmpnt
     * @return 
     */
    Component add(Component cmpnt);
    
    /**
     * Inicia uma ação, obtendo uma ID como parametro
     * @param id 
     */
    void action(final Integer id);
    
}

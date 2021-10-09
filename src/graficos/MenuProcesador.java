
package graficos;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MenuProcesador extends JFrame {

    private Dimension screen;
    
    public MenuProcesador() {
        
        screen=Toolkit.getDefaultToolkit().getScreenSize();
        
        this.setSize(screen);
        this.setTitle("Procesador de Texto");
        this.setResizable(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        PanelProcesador panel=new PanelProcesador();
        this.add(panel);
    }
    
    
}

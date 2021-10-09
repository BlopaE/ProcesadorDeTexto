
package graficos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class PanelProcesador extends JPanel{

    private JTextPane area;
    private JMenu font,style,size;
    private Font letters;
    
    public PanelProcesador() {
        
        this.setLayout(new BorderLayout());
        //Construyendo el menú
        JPanel menu=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JMenuBar bar=new JMenuBar();
        
        font=new JMenu("Fuente");
        style=new JMenu("Estilo");
        size=new JMenu("Tamaño");
        
        String fontName[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (String font:fontName){
            this.addItemMenu(font, this.font);
        }
        
        this.addItemMenu("Negrita", style);
        this.addItemMenu("Cursiva", style);
        
        
        for (int i = 8; i <= 40; i+=2) { //El rango del for indica el rango de tamaños
            
            this.addItemMenu(String.valueOf(i), size);
        }
        
        bar.add(font);
        bar.add(style);
        bar.add(size);
        
        menu.add(bar);
        this.add(menu,BorderLayout.NORTH);
        
        //Construyendo el area de texto.
        area=new JTextPane();
        area.setFont(new Font("Arial",Font.PLAIN,14)); 
        this.add(area,BorderLayout.CENTER);
    }
    
    private void addItemMenu(String name,JMenu menu){
        
        JMenuItem item=new JMenuItem(name);
        
        item.addActionListener(new EventMenu(menu));
        menu.add(item);
    }
    
    private class EventMenu implements ActionListener{
        
        JMenu menu;

        public EventMenu(JMenu menu) {
            this.menu=menu;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            letters=area.getFont(); //Guarda los datos de la fuente actual
            
            if (menu.equals(font)){
                
                letters=new Font(e.getActionCommand(),letters.getStyle(),letters.getSize());
                
            }else if (menu.equals(style)){
                
                  int styleSelect=0;
                  
                  if (e.getActionCommand().equals("Negrita")){
                      
                      styleSelect=Font.BOLD;
                      
                  }else if (e.getActionCommand().equals("Cursiva")){
                      
                      styleSelect=Font.ITALIC;
                  }
               
                  if ((letters.getStyle()+styleSelect)>3 || letters.getStyle()==styleSelect){
                      
                      letters=letters.deriveFont(letters.getStyle()-styleSelect);
                      
                  }else{
                      
                      letters=letters.deriveFont(letters.getStyle()+styleSelect);
                  }
            }else if (menu.equals(size)) {
                
                letters=letters.deriveFont(Float.parseFloat(e.getActionCommand()));
            }
            
            area.setFont(letters);
        }
        
        
    }
    
    
    
}

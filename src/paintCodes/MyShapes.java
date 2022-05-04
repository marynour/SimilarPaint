package paintCodes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class MyShapes extends JComponent{
	
	
	JButton rectangle ,circle,droite,text;
	public static String action="Pen"; //Au début , l'outil utilisé est le crayon
	
	public MyShapes() {
		rectangle  = creerBouton("rectangle.png","Rectangle");
		circle  = creerBouton("circle.png","Circle");
		droite  = creerBouton("droite.png","Droite");
	
	
	}
	//La fonction de création du bouton avec l'icone pour éviter la répétition
	   public JButton creerBouton(String imageURL, String nomAction)
	    {
	    	JButton bouton = new JButton(
	    			new ImageIcon(((new ImageIcon(getClass().getClassLoader().getResource(imageURL))).getImage()).getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH)));
	    	bouton.setBackground(Color.decode("#F6E8E5"));
	    	bouton.setToolTipText(nomAction);
	    	bouton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					action = nomAction;
				}
			});
	            
	        
	        return bouton;  }
	    

}
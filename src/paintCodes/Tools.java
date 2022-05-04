package paintCodes;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tools  extends JPanel{
	JButton clearbutton,pencilbutton,fillbutton,eraserbutton;
	public Tools() {
		//Clear button
		clearbutton=creerBouton("Effacez tout le dessin ",
				"clear.png");
		//Pencil button
		pencilbutton=creerBouton("Crayon: Dessinez un dessin libre  ",
				"pencil.png");
		//Fill button
		fillbutton=creerBouton("Remplissage: Couvrir le tableau de dessin par une couleur définie ",
				"fill.png");
		//Eraser button
		eraserbutton=creerBouton("Gomme : Effacez une partie du dessin ",
				"eraser.png");
		
	}
	
	public JButton creerBouton (String tip,String imgURL) {
		ImageIcon img=new ImageIcon(getClass().getClassLoader().getResource(imgURL));
		JButton bouton = new JButton(img);
		bouton.setToolTipText(tip);
		bouton.setBackground(Color.decode("#F6E8E5"));
		return bouton;
		
	}

}
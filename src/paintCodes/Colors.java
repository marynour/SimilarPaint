package paintCodes;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Colors extends JComponent{
	JButton black,white,red,blue,lightblue,lightgreen,green,lightyellow,yellow,cyan,lightturquoise,orange,lightorange,lightgray,gray,violet,pink,brown,marron,purple;
	public Colors() {
		
		setLayout(new GridLayout(3,12,1,4));
		/*On definit les boutons des couleurs avec leurs background*/
		black =creerBoutonCouleur(Color.black);
		white=creerBoutonCouleur(Color.white);
		red=creerBoutonCouleur(Color.red);	
		blue=creerBoutonCouleur(Color.blue);
		lightblue=creerBoutonCouleur(Color.decode("#2E64FE"));
		green=creerBoutonCouleur(Color.decode("#04B404"));
		lightgreen=creerBoutonCouleur(Color.decode("#80FF00"));
		yellow=creerBoutonCouleur(Color.yellow);
		lightyellow=creerBoutonCouleur(Color.decode("#F3F781"));
		cyan=creerBoutonCouleur(Color.cyan);
		lightturquoise=creerBoutonCouleur(Color.decode("#81DAF5"));	
		orange=creerBoutonCouleur(Color.decode("#FF8000"));
		lightorange=creerBoutonCouleur(Color.orange);
	    gray=creerBoutonCouleur(Color.gray);
		lightgray=creerBoutonCouleur(Color.decode("#A4A4A4"));
		violet=creerBoutonCouleur(new Color(238,130,238));
		purple=creerBoutonCouleur(Color.decode("#6A0888"));
		pink=creerBoutonCouleur(Color.pink);			
		brown=creerBoutonCouleur(Color.decode("#8A4B08"));
		marron=creerBoutonCouleur(new Color(128, 0, 0));
	}
	
	
	public JButton creerBoutonCouleur(Color couleur) {
		JButton bouton = new JButton();
		bouton.setBackground(couleur);
		return bouton;
	}
}

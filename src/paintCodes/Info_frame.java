package paintCodes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
//La fenetre qui affiche les informations de notre application
@SuppressWarnings("serial")
public class Info_frame extends JFrame {
	
	public Info_frame() {
		
		this.setSize(300,200);
		
		this.setTitle("Informations sur l'application");
		this.setLocationRelativeTo(null);	
		JPanel panel = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
		
		JLabel lab1=new JLabel("<html> LP GI5 28 Janvier 2021 <br>"
				+ "Ce projet a été réalisé par : <br> Maryam Nour eddine <br>Hajar El Aimar <br>Mohamed Ait Ouahmane <br> Mohamed Amine Zibouli"
				+ "<br>Encadré par : Mme Sarra Roubi </html>" );
		lab1.setBorder(border);
		
		panel.add(lab1);
		panel.setBackground(Color.WHITE);
		this.setContentPane(panel);
		this.setVisible(true);
		
	}

}
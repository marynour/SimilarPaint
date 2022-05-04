package paintCodes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings({ "deprecation", "unchecked", "rawtypes","serial"})
public class Paint extends JFrame{
	//La taille du trait
	  public static float fB=1;
	  
	  
	// Pour passer les données à la classe drawing
	public static String input="empty";
	public static String font="empty";
	public static int size=10;
	
	//Enregistrer la hauteur et la largeur de notre fenetre afin des les utiliser dans file save
	public static int width;
	public static int height; 
	
	//Declaration des composants de notre fenetre
	 ImageIcon icon;
	 Menu menu; 
	 JPanel colors, palette,tools,Paltext,shapes;
	 static Drawing drawing = new Drawing();
	 Colors cl = new Colors();
	 Tools tool = new Tools();
	 MyShapes shape = new MyShapes();
	 //Les variables d'insertion du texte
	 JTextField x,y,txt;

		//Le bouton de validation d'insertion du texte
		JButton text  = new JButton("Valider"); 
		
		
	 //Le constructeur
	public Paint() {
		width= this.getWidth();
		height=this.getHeight();
/*****Les méthodes de notre fenetre******/
		this.setTitle("Paint");
		this.setSize(900,700);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		//Changer l'icone de notre fenetre
		icon=new ImageIcon(getClass().getClassLoader().getResource("appIcon.jpg"));
		this.setIconImage(icon.getImage());
		//Rendre la fenetre visible
		this.setVisible(true);
		//Fixer la taille de la fenetre				
		this.setResizable(false);
		//Modifier les paramètres de la fermeture de la fenetre
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choose = JOptionPane.showConfirmDialog(null,
                        "Do you want to save changes ?",
                        "Save changes", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (choose == JOptionPane.YES_OPTION) {
                
        					new SaveFile_frame();
        		
                } else {
                	e.getWindow().dispose();
                }
            }
        });
		//Ajouter le menu à notre fenetre
				menu = new Menu();
				add(menu,BorderLayout.NORTH); 
	
	
//------------------------------------------------------------	
		//Le conteneur qui contient les différents composants
		Container box=  this.getContentPane();
		//Le tableau de dessin 
		drawing = new Drawing();
		box.add(drawing, BorderLayout.CENTER);
		
		//La palette des couleurs et ses caractéristiques
		colors = new JPanel();
		colors.setBackground(Color.decode("#F5E29C"));
		colors.setBorder(BorderFactory.createRaisedBevelBorder());
		colors.setPreferredSize(new Dimension(70,140));
		colors.setLayout(new GridLayout(10,2));
		colors.setToolTipText("Palette des couleurs : Un choix divers pour dessiner ");
		
		
		//Ajouter les boutons declarés dans la classe Colors
		colors.add(cl.black);
		colors.add(cl.white);
		colors.add(cl.gray);
		colors.add(cl.lightgray);		
		colors.add(cl.marron);
		colors.add(cl.brown);		
		colors.add(cl.red);
		colors.add(cl.pink);		
		colors.add(cl.orange);
		colors.add(cl.lightorange);		
		colors.add(cl.yellow);
		colors.add(cl.lightyellow);		
		colors.add(cl.green);
		colors.add(cl.lightgreen);		
		colors.add(cl.blue);
		colors.add(cl.lightblue);			
		colors.add(cl.cyan);
		colors.add(cl.lightturquoise);		
		colors.add(cl.purple);
		colors.add(cl.violet);
		
		//Accorder des ecouteurs à nos boutons de couleurs
		cl.black.addActionListener(actionListener);
		cl.white.addActionListener(actionListener);
		cl.red.addActionListener(actionListener);
		cl.blue.addActionListener(actionListener);
		cl.lightblue.addActionListener(actionListener);
		cl.green.addActionListener(actionListener);
		cl.lightgreen.addActionListener(actionListener);
		cl.yellow.addActionListener(actionListener);
		cl.lightyellow.addActionListener(actionListener);
		cl.cyan.addActionListener(actionListener);
		cl.lightturquoise.addActionListener(actionListener);
		cl.orange.addActionListener(actionListener);
		cl.lightorange.addActionListener(actionListener);
		cl.gray.addActionListener(actionListener);
		cl.lightgray.addActionListener(actionListener);
		cl.violet.addActionListener(actionListener);
		cl.purple.addActionListener(actionListener);
		cl.pink.addActionListener(actionListener);
		cl.brown.addActionListener(actionListener);
		cl.marron.addActionListener(actionListener);
        //La palette à gauche où on va poser nos boutons(palette de couleurs,les formes...)
		palette = new JPanel();
		palette.setPreferredSize(new Dimension(120,300));
		palette.setBackground(Color.decode("#F2E4DC"));
		
		//Ajout des couleurs
		palette.add(colors);
		
		
        //La palette des outils specifiés (crayon,gomme,remplissage) et ses caractéristiques
		
		tools=new JPanel();
		
		//tools.setBorder(BorderFactory.createRaisedBevelBorder());
		tools.setPreferredSize(new Dimension(70,130));
		tools.setLayout(new GridLayout(4,1,3,4));//On spécifie le layout de nos boutons 
		tools.setBackground(Color.decode("#E2DCDC"));
		tools.setBorder(BorderFactory.createRaisedBevelBorder());
		//Ajouter les outils
		tools.add(tool.clearbutton);
		tools.add(tool.pencilbutton);
		tools.add(tool.fillbutton);
		tools.add(tool.eraserbutton);
		
		//Clear 
		tool.clearbutton.addActionListener(actionListener);
		//Remplissage 
		tool.fillbutton.addActionListener(actionListener);
		//Crayon
		tool.pencilbutton.addActionListener(actionListener);
		//Eraser
		tool.eraserbutton.addActionListener(actionListener);
		
		
		
		
		//La palette des formes
		shapes = new JPanel();
		
		shapes.setBorder(BorderFactory.createRaisedBevelBorder());
		shapes.setPreferredSize(new Dimension(70,100));
		shapes.setLayout(new GridLayout(3,12,3,4));
		shapes.setBackground(Color.decode("#E2DCDC"));
		 
		//Ajouter des boutons des formes
		shapes.add(shape.rectangle);
		shapes.add(shape.circle);
		shapes.add(shape.droite);
		
		
		
		
		//La palette d'insertion du texte
		Paltext = new JPanel();
		Paltext.setBorder(BorderFactory.createRaisedBevelBorder());
		Paltext.setPreferredSize(new Dimension(120,150));
		Paltext.setLayout(new GridLayout(5,9));
		Paltext.setBackground(Color.decode("#F6E8E5"));
		
		txt =new JTextField(5);
		
		//La liste des fonts
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilyNames = ge.getAvailableFontFamilyNames();
		JComboBox fontsBox = new JComboBox(fontFamilyNames);
        fontsBox.setSelectedItem(0);
        fontsBox.setToolTipText("Sélectionnez Le font du texte");
        
        //La liste de la taille du texte
        JComboBox cbSize = new JComboBox();
        for (int i = 5; i <= 50; i++) {
            cbSize.addItem(new Integer(i*2));
        }
        
        cbSize.setToolTipText("Choisissez la taille du texte");
		Paltext.add(new JLabel("Entrez un texte :"));
		Paltext.add(txt);
	
		Paltext.add(fontsBox);
		Paltext.add(cbSize);
		//Ajout du bouton de validation pour inserer un texte
		text.setToolTipText("Valider les choix pour inserer le texte");
		text.setBackground(Color.decode("#F6E8E5"));
		
		Paltext.add(text);
		try {
			//Ajouter un écouteur au bouton valider
			text.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					MyShapes.action="Text";
					//Le text inseré par l'utilisateur
					input = txt.getText();
				}
			});
			//Ajouter à la liste des fonts un  écouteur
			fontsBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					font = fontsBox.getSelectedItem().toString();
				}
			});
			//Ajouter à la liste des tailles un  écouteur
			cbSize.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					size = Integer.parseInt(cbSize.getSelectedItem().toString());
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
		
		
		//La liste de la taille du trait

			    JComboBox cbBrush = new JComboBox();
			    for (int i = 2; i <= 10; i++)
			      cbBrush.addItem(new Float(i*2));
			    palette.add(cbBrush);
			    cbBrush.addActionListener(new ActionListener() {
			      
			      @Override
			      public void actionPerformed(ActionEvent e) {
			        // TODO Auto-generated method stub
			        fB = Float.parseFloat(cbBrush.getSelectedItem().toString());
			      }
			    });
			    
		 cbBrush.setToolTipText("Sélectionner la taille afin de determiner la largeur du trait");
	   //On ajoute la palette specifiée des outils , des formes et celle d'insertion du texte dans la palette principale
     	palette.add(tools);
		palette.add(shapes);		 
		palette.add(Paltext);
			
		//On ajoute la palette principale dans notre fenetre
		box.add(palette, BorderLayout.WEST);
		
		
		
	}
	//Les actions associes à nos boutons 
	ActionListener actionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cl.black) {drawing.changeColor=(Color.black);}
			else if(e.getSource() == cl.white ) {drawing.changeColor=(Color.white);}
			else if(e.getSource() == cl.red) {drawing.changeColor=(Color.red);}
			else if(e.getSource() == cl.blue) { drawing.changeColor=(Color.blue); }
			else if(e.getSource() == cl.lightblue) { drawing.changeColor=(Color.decode("#2E64FE")); }
			else if(e.getSource() == cl.green) { drawing.changeColor=(Color.decode("#04B404")); }
			else if(e.getSource() == cl.lightgreen) { drawing.changeColor=(Color.decode("#80FF00")); }
			else if(e.getSource() == cl.yellow) { drawing.changeColor=(Color.yellow);}
			else if(e.getSource() == cl.lightyellow) { drawing.changeColor=(Color.decode("#F3F781")); }
			else if(e.getSource() == cl.cyan) { drawing.changeColor=(Color.cyan);}
			else if(e.getSource() == cl.lightturquoise) { drawing.changeColor=(Color.decode("#81DAF5")); }
			else if(e.getSource() == cl.orange) { drawing.changeColor=(Color.decode("#FF8000")); }
			else if(e.getSource() == cl.lightorange) { drawing.changeColor=(Color.orange); }
			else if(e.getSource() == cl.gray) { drawing.changeColor=(Color.gray); }
			else if(e.getSource() == cl.lightgray) { drawing.changeColor=(Color.decode("#A4A4A4")); }
			else if(e.getSource() == cl.violet) { drawing.changeColor=(new Color(238,130,238)); }
			else if(e.getSource() == cl.purple) { drawing.changeColor=(Color.decode("#6A0888"));}
			else if(e.getSource() == cl.pink) { drawing.changeColor=(Color.pink);}
			else if(e.getSource() == cl.brown) { drawing.changeColor=(Color.decode("#8A4B08"));}
			else if(e.getSource() == cl.marron) { drawing.changeColor=(new Color(128,0,0)); }
			
			else if(e.getSource() == tool.fillbutton) { MyShapes.action="Fill";}
			else if(e.getSource() == tool.clearbutton) { drawing.clear(); }
			else if(e.getSource()==tool.pencilbutton) { MyShapes.action="Pen";}
			else if(e.getSource()==tool.eraserbutton) { MyShapes.action="Eraser";}
		}
		
		
	};
}
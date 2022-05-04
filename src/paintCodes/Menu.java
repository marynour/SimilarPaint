package paintCodes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")

public class Menu extends JMenuBar implements ActionListener
{
	Drawing drawing;
    JMenu file;
        JMenuItem New;
        JMenuItem Save;
        JMenuItem Saveinf;
        JMenuItem quit;

    JMenu home; 
        
    JMenu view; 
    	JMenuItem zoomIn;
    	JMenuItem zoomOut;
    
    JMenu info;
    	JMenuItem myInfo;

    	//Zoom
    	
      	static boolean zoomer = false;
    	static int squareWidth = 900;
    	static int squareHeight = 900;


    Menu()
    {
            file = new JMenu("File");
            //New 
	        New = new JMenuItem("New");
	        New.addActionListener(this);
	        file.add(New);
	        
	        //Save file
	        Save = new JMenuItem("Save");
	        Save.addActionListener(this);
	        file.add(Save);
	        
	        //Save infos
	        Saveinf=new JMenuItem("Save to DataBase");
	        Saveinf.addActionListener(this);
	        file.add(Saveinf);
	        
	        //Exit
	        file.addSeparator();
	        quit=new JMenuItem("Exit");
	        quit.addActionListener(this);
	        file.add(quit);
	        
	        //Ajouter le menu à notre fenetre  
	        this.add(file);
       

        home = new JMenu("Home");
        this.add(home);
        
        view = new JMenu("View");
        
	        zoomIn = new JMenuItem("Zoom in");
	        zoomIn.addActionListener(this);
				
	        view.add(zoomIn);
	        
	        zoomOut = new JMenuItem("Zoom out");
	        view.add(zoomOut);
	        	
	    this.add(view);
	    
	    
	    info = new JMenu("Informations");
	    	myInfo = new JMenuItem("info");
	    	myInfo.addActionListener(this);
	    	
	    	info.add(myInfo);
	    	
	    this.add(info);
	    

    }

	
	
	
	//Les actions des élements menu
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Ouvrir le formulaire qui permet à l'utilisateur de saisir les données pour les enregistrer dans la BDD 
		if(e.getSource()==Saveinf) {
				new SaveToDB_frame();
			}
		
		
		//Renouvler le tableau du dessin
		else if(e.getSource()==New) {
			Paint.drawing.clear();
			}
		//Pour enregistrer le fichier dans l'ordinateur
		else if(e.getSource()==Save) {
			new SaveFile_frame();
			}
		
		else if(e.getSource()==zoomIn) {
			squareWidth += 100;
			squareHeight += 100;
			zoomer = true;
		}
		else if(e.getSource()==zoomOut) {
			squareWidth -= 100;
			squareHeight -= 100;
			zoomer = true;
		}
		
		//pour afficher la fenetre des informations de l'app :
		else if (e.getSource()==myInfo) {
		new Info_frame();
			
		}
		
		//Pour quitter l'application
		else if(e.getSource()==quit) {
			System.exit(0);
		}
		
		
		
		
		
	}





	
}
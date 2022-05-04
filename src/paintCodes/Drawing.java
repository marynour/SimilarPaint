package paintCodes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Drawing extends JPanel
 {
	Color changeColor = Color.black; //la couleur par defaut de dessin est le noir
	// l'image don't on va dessiner
	static BufferedImage image;
	private BufferedImage copyImage;
	
	  private Graphics2D graphic;
	  // les coorconées
	  private int xCourant, yCourant, xAncien, yAncien , xDroite,yDroite;
	  
	//le constructeur
	public Drawing() {	
		this.setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				//enregistrement des coordonnées de début
				xAncien = e.getX();
				yAncien = e.getY();
				copyImage = cloneImage(image);
				
				if(MyShapes.action=="Text") {
		    		Font f = new Font(Paint.font, Font.BOLD, Paint.size);
		    		Graphics2D g =image.createGraphics();	
		    		g.setColor(changeColor);
		    		g.setFont(f);
		    		g.drawString(Paint.input, xAncien, yAncien);
		    		repaint(); 
		    	}
		    	else if (MyShapes.action=="Fill") {
		    		Paint.drawing.fill();
		    	}
			
			}
			// pour enregistrer les coordonées où l'utilisateur à cliquer lorsque il est entrains de dessiner une droite 
			//car on ne peut pas utiliser les xcourant , y courant puisque ils se changent à chaque fois on fait un mouse drag
			public void mouseClicked(MouseEvent e) {
				xDroite = e.getX();
				yDroite = e.getY();
				
			}
		});

	    addMouseMotionListener(new MouseMotionAdapter() {
	    	
	    	public void mouseDragged(MouseEvent e) {	    	
	    		//enregistrement des coordonnées de fin 
	    		xCourant = e.getX();
	    		yCourant = e.getY();
	    		
	    		if((MyShapes.action =="Pen")||(MyShapes.action=="Eraser")) {
	    			
	    			Graphics2D g =image.createGraphics();	
	    			if(MyShapes.action=="Eraser") {
	    				g.setColor(Color.white);
	    				}
	    			else {
	    				g.setColor(changeColor);
	    				}
	    			g.setStroke(new BasicStroke(Paint.fB));
	    			g.drawLine(xAncien, yAncien, xCourant, yCourant);	    			    		    			    	
	    			repaint(); 
	    			
	    			xAncien = xCourant;
		    		yAncien = yCourant;
	    		}
		    		

	    	else if(MyShapes.action=="Droite") {
	    		Graphics2D g =gDesc() ;
	    			g.drawLine(xAncien, yAncien, xDroite, yDroite);
	    			repaint(); 
	    		}
	    	else if(MyShapes.action =="Rectangle") {
	    			int x =e.getX(), y=e.getY();
	    			if(xCourant>xAncien)
	    				 x = xAncien;
	    			else if (xCourant<xAncien)
	    				x= xCourant;
	    			
	    			if(yCourant>yAncien)
	    				 y = yAncien;
	    			else if (yCourant<xAncien)
	    				y= xCourant;
	    			
	    			image = cloneImage(copyImage);
	    		
	    			Graphics2D g =gDesc() ;
	    			
	    			int width = Math.abs(xCourant - xAncien);
	    			int height = Math.abs(yCourant - yAncien);
	    			
	    			
	    			
	    			g.drawRect(x, y, width, height);
		    			
	    			repaint();
		    		}
		    	else if(MyShapes.action=="Circle") {
		    		
		    		image = cloneImage(copyImage);
		    		
		    		Graphics2D g =gDesc() ;
		    		
		    		int width = Math.abs(xCourant - xAncien);
	    			int height =  Math.abs(yCourant - yAncien);
	    			
		    		g.drawOval(xAncien, yAncien, width, height);
		    		
		    		repaint();
		    	}
	    		
	    		graphic.setStroke(new BasicStroke(7));
	    		
	    		//desssin du composant	
	    		repaint();

	    		xAncien = xCourant;
	    		yAncien = yCourant;	    		
	      }
	    }); }
	    	
	
	
	 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(1,1,this.getWidth()-2,this.getHeight()-2);
		Color couleur=changeColor;
		g.setColor(couleur);
		//si c'est le premier lancement de l'application
		if (image == null) {
			image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
			
			//type ubt argb pour pouvoir afficher les couleurs
		}		
		

		if(Menu.zoomer)
			g.drawImage(image, 0, 0, Menu.squareWidth, Menu.squareHeight, null);
		else
			g.drawImage(image, 0, 0, null);

	}
	
	
	
	
	private BufferedImage cloneImage(BufferedImage img) {
		if (img == null) 
			return null;
		ColorModel colormodel = img.getColorModel();
		boolean  isAlpha = colormodel.isAlphaPremultiplied();
		WritableRaster raster = img.copyData(null);
		return new BufferedImage(colormodel,raster,isAlpha,null);
		
	}
	
	//fct pour ne pas repetrer chaque fois le code pour les caractéristique du graphic
	private Graphics2D gDesc() {
		Graphics2D g =image.createGraphics();
		g.setStroke(new BasicStroke(Paint.fB));
		g.setColor(changeColor);
		return g;
	}
	
	//Fct d'annulation 
	public void clear() {
		image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
		this.setBackground(Color.WHITE);
		repaint();
	  }
	//Fct de remplissage
	public void fill() {
		
		image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
		 this.setBackground(changeColor);
	}
	
	
}


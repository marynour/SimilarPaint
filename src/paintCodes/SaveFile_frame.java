package paintCodes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

@SuppressWarnings("serial")
//Une classe Java qui permet d'ouvrir ou sauvegarder un fichier en naviguant dans ses répertoires
public class SaveFile_frame extends JFrame {
	public SaveFile_frame(){
		JFileChooser saveFile = new JFileChooser(".");
		saveFile.addChoosableFileFilter(new PngSaveFilter());
		int sf = saveFile.showSaveDialog(saveFile);
		//Si l'utilsateur confirme la selection du fichier
		if(sf == JFileChooser.APPROVE_OPTION){
			String ext = ".png";
				 //On sélectionne le fichier
				saveFile.setSelectedFile(new File(saveFile.getSelectedFile().toString() + ext));
				try {
					//on récupère l'image principale de notre application
					BufferedImage image = Drawing.image;
					Graphics2D ig2 = (Graphics2D) image.getGraphics();
					ig2.setColor(Color.white);
					ig2.fillRect(0, 0,Paint.width,Paint.height);

					paint(ig2);
					File outputfile = saveFile.getSelectedFile();
					ImageIO.write(image, "png", outputfile);

				}catch (IOException e) {
					//AWTException
					//IOException
					e.printStackTrace();
					System.out.println("error");
				}
			
		}
	}
}
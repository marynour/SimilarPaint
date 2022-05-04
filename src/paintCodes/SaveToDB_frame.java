package paintCodes;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
//la fenetre qui permet de saisir les données à enregistrer dans la base de données
public class SaveToDB_frame extends JFrame{
	
	@SuppressWarnings("unchecked")
	public SaveToDB_frame() {
		this.setVisible(true);
		this.setSize(400,400);
		this.setTitle("Formulaire");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//Le panneau du titre 
		JPanel Titlepan = new JPanel(new GridBagLayout());
		Titlepan.setBackground(Color.decode("#f7e4be"));
		JLabel  formulaire = new JLabel("Formulaire");
		GridBagConstraints titlecon = new GridBagConstraints();
		titlecon.gridx=0;
		titlecon.gridy=0;
		titlecon.gridwidth=2;
		titlecon.gridheight=1;
		titlecon.weightx=1;
		titlecon.weighty=0.1;
		//Remplir l'espace verticalement et horizontalement 
		titlecon.fill = GridBagConstraints.BOTH;
		//Ajuster le font d'ecriture
		formulaire.setFont(new Font("Times New Roman",Font.BOLD,28));
		Titlepan.add(formulaire);
		JTextField txtusername = new JTextField(10);
		JTextField txtfilename = new JTextField(10);
		@SuppressWarnings("rawtypes")
		JComboBox txtextension = new JComboBox();//Ajouter une liste d'extension
		txtextension.addItem("png");
		txtextension.addItem("jpg");
		txtextension.setBackground(Color.decode("#ffffff"));
		//Nom d'utilisateur
		JLabel nom=new JLabel("Nom d'utilisateur:" );
		nom.setLabelFor(txtusername); 
		GridBagConstraints nomconstaraints = new GridBagConstraints();
		nomconstaraints.gridx=1;//Colonne
		nomconstaraints.gridy=1;//Ligne
		//Espace entre les elements
		nomconstaraints.weightx=0.1;
		nomconstaraints.weighty=0.1;
		//Identifier la marge interieur
		nomconstaraints.ipadx=100;//La largeur
		nomconstaraints.ipady=5;//La longeur
		//Nom du fichier
		JLabel filename=new JLabel("Nom du fichier:");
		filename.setLabelFor(txtfilename); 
		GridBagConstraints filecon = new GridBagConstraints();
		filecon.gridx=1;
		filecon.gridy=2;
		filecon.weightx=0.1;
		filecon.weighty=0.1;
		filecon.ipadx=100;
		filecon.ipady=5;
        //Extension
		JLabel extension=new JLabel("Extension du fichier:" );
		extension.setLabelFor(txtextension); 
		GridBagConstraints extcon = new GridBagConstraints();
		extcon.gridx=1;
		extcon.gridy=3;
		extcon.weightx=0.1;
		extcon.weighty=0.1;
		extcon.ipadx=160;
		extcon.ipady=5;
		GridBagConstraints gbc = new GridBagConstraints();
		//Le panneau general dont on va poser nos elements 
		JPanel p = new JPanel(new GridBagLayout());
		p.setBackground(Color.decode("#ffffff"));
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=0.1;
		gbc.anchor=GridBagConstraints.CENTER;
	    p.add(Titlepan,titlecon);
		//NOM
		gbc.gridy = 1;
		p.add(nom,gbc);
		p.add(txtusername,nomconstaraints);
		//FICHIER
		gbc.gridy=2;
		p.add(filename,gbc);
		p.add(txtfilename,filecon);
		//EXTENSION
		gbc.gridy=3;
		p.add(extension,gbc);
		p.add(txtextension,extcon);

		JButton send=new JButton( "Confirm");
		JButton reset=new JButton("Reset");
		send.setBackground(Color.decode("#f7e4be"));
		reset.setBackground(Color.decode("#f7e4be"));
		//SEND
		gbc.gridx=1;//colonnes
		gbc.gridy=4;//lignes
		gbc.weighty=0.01;
		gbc.anchor=GridBagConstraints.EAST;
		gbc.ipadx=5;
		gbc.ipady=5;
		p.add(send,gbc);
		//reset
		gbc.gridx=1;
		gbc.gridy=5;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.EAST;
		gbc.ipadx=5;
		gbc.ipady=5;
		p.add(reset,gbc);
		//ajouter ce panneau a notre fenetre
		this.getContentPane().add(p);
		//Ajouter les actions a nos boutons
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtusername.setText(null);
				txtfilename.setText(null);
				txtextension.setSelectedIndex(0);//Pour initialiser une liste
				
			}
		}
				
				);
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		
				//Inserer les données dans la BDD après le clic sur le bouton confirmer
		     try {
		    	 insert("INSERT INTO paintdata VALUES('"+txtusername.getText()+"','"+txtfilename.getText()+"','"+txtextension.getSelectedItem().toString()+"',CURRENT_TIMESTAMP)");
		    	 System.out.println("Inserted successfully");
		    	 
		     }catch(Exception ex){
		    	System.out.println("Error"); 
		     }
		     System.exit(0);
		     
		     
			}
		});
		
		
	}
	

    //Une fonction pour effectuer la connexion avec la base de données
    public static void insert(String query) {
    	 Connection connection;
          Statement statement;
          try{
        	  //Préparer la connexion de notre application Java avec la base de données MYSQL
              Class.forName("com.mysql.cj.jdbc.Driver");
              //Créer une connexion entre notre application et la BDD Paint
              connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/paint?" + "user=root&password= "+"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
              //Créer des statements pour executer SQL statements 
              statement=connection.createStatement();
              //Une methode utilisée pour specifier SQL query à executer dans ce cas un insert 
              statement.executeUpdate(query);
              System.out.println("Connected");
              
              
          }catch(Exception e){
              System.out.println(e);
          }
          
      }
}
package lockard;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.*;
public class User_alert extends JFrame  implements ActionListener {
private static  JTextArea tr;
private static JButton  alert,Aj, Qt;
private static JTextField N, Pn, Ag, tel, adr, _id;
private static JFileChooser fc;
private static JButton openButton;
public User_alert(){   
		 //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 setUndecorated(true);  
         getRootPane().setWindowDecorationStyle(JRootPane.NONE); 
		 setLayout(null); 
		 getContentPane().setPreferredSize(new Dimension(100, 100));
		 //alerte general message
		 setBounds(350, 200, 350, 300);
		 tr = new JTextArea("Attention a toutes les unites, un vehicule immatricule RCXXXX, est en infraction.Priere de l'arreter!!");
		 tr.setFont(new Font("Serif", Font.ITALIC, 16));
         tr.setLineWrap(true);
         tr.setWrapStyleWord(true);        		 
       	 alert = new JButton("Lancer");	
		 alert.setForeground(Color.white);
		 alert.setBackground(Color.blue);
		 Qt = new JButton("Quitter");
		 Qt.setForeground(Color.white);
		 Qt.setBackground(Color.blue);
		 Aj = new JButton("Ajouter");
		 Aj.setForeground(Color.white);
		 Aj.setBackground(Color.blue);
		 tr.setBounds(0,0, 350,150);
         alert.setBounds(130,210, 100,25);
         Qt.setBounds(250,210, 80,25); 
         Aj.setBounds(166,210, 80,25); 		 
		 add(tr);
		 add(alert); add(Qt); add(Aj);
		 Qt.addActionListener(this);
		 Aj.addActionListener(this);
		 alert.addActionListener(this);
		 setResizable(false);		 
		 // ajouter utilisateur
		N = new  JTextField("Nom utilisateur"); 
		Pn =  new JTextField("Prenom utilisateur"); 
		Ag =  new JTextField("Age utilisateur"); 
	    tel = new  JTextField("Telephone"); 
		adr = new  JTextField ("Addresse");
		_id  = new  JTextField ("id traceur");
		fc = new JFileChooser();
		openButton = new JButton("charger la photo",createImageIcon("images/Open.png"));
        openButton.addActionListener(this);
		N.setBounds(0,0, 350,25);
		Pn.setBounds(0,25, 350,25);
		Ag.setBounds(0,50, 350,25);
		tel.setBounds(0,75, 350,25);
		adr.setBounds(0,100, 350,25);	
		openButton.setBounds(0,210, 150, 25);
		openButton.setForeground(Color.white);
		openButton.setBackground(Color.blue);
		_id.setBounds(0,127,210,25);
		add(_id);
		add(N); add(Pn); add(Ag); add(tel); add(adr); add(openButton); 
    }
	public void hidealert(String code){
	  if (code == "a") {
	  tr.setVisible(true);
	  alert.setVisible(true);
	  Aj.setVisible(false);
	  N.setVisible(false); _id.setVisible(false);openButton.setVisible(false);
	  Pn.setVisible(false); Ag.setVisible(false); tel.setVisible(false); adr.setVisible(false);
	  } else 
	  if (code == "u"){
	    tr.setVisible(false);openButton.setVisible(true);
	   alert.setVisible(false);
	   Aj.setVisible(true);
	   N.setVisible(true); _id.setVisible(true);
	  Pn.setVisible(true); Ag.setVisible(true); tel.setVisible(true); adr.setVisible(true);
	  }
	}	
	protected  ImageIcon createImageIcon(String path) {
   // java.net.URL imgURL = User_alert.class.getResource(path);
	java.net.URL imgURL = this.getClass().getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }  
   public void actionPerformed(ActionEvent e) {
    if (e.getSource() == openButton)  new HtmlContent().start();  
    if (e.getSource() == Qt)  setVisible(false); 
    if (e.getSource() == Aj) 	{
	try {
	String nm = N.getText(); String prn = Pn.getText();
	String tf = tel.getText(); String adrs = adr.getText();
	String tid = _id.getText(); 	
	String ag =  Ag.getText();
	Servercommunication sc = new  Servercommunication();  
	sc.Adduser(nm.trim(),prn.trim(), ag.trim(), tf.trim(),adrs.trim(),tid.trim(),tid.trim());
	} catch (Exception ex) {}
	}
	if (e.getSource() == alert){
	 String status = "";
	 javax.swing.JOptionPane.showMessageDialog(null, Mainlockard.agent);
	  try { 
	  status = new Servercommunication().AlertG(tr.getText().replaceAll("\\s+","_"),Mainlockard.agent);
	  if (status.equals("ok"))javax.swing.JOptionPane.showMessageDialog(null,"Alert general lance!!!");
	     else javax.swing.JOptionPane.showMessageDialog(null,"une erreur est survenue");
	  } catch (Exception me){}
	}
  }
}

package lockard;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.nio.ByteBuffer;
import java.util.*;
import javax.sound.sampled.*;
import  sun.audio.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Mymap extends JFrame implements ActionListener {
   private static JPanel panelEvents, panelobj;
   private static	String uid;
   private static JTextArea sosAdr, Alerdisplay;
   private static JButton connect, button5, button3, button2, button4;
   private static javax.swing.JLabel Mdisplayer, Zm, mt;
   static final int FPS_MIN = 0;
   static final int FPS_MAX = 20;
   static final int FPS_INIT = 10;
   public static JComboBox maptype;
  private static String[] maptypes = { "ROADMAP", "SATELLITE", "TERRAIN","HYBRID"};
  private static JLabel nom, prenom, age, adres, telephone;
  private static JLabel photo;
  public static JSlider framesPerSecond;
  private static int counter = 0;
  private static JScrollPane sp, mp;
  public static String Bufdata = "";
  URL url;
 AudioInputStream ais;
 Clip clip;
 User_alert ual = null;
  public Mymap(){       
		 super("S.O.S SERVICE V1.0.0");
		 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 setUndecorated(true);  
         getRootPane().setWindowDecorationStyle(JRootPane.NONE); 
		 //Image im = Toolkit.getDefaultToolkit().getImage("C:/Program Files/Java/jdk1.7.0_45/bin/lockard/fav.png");
		 //setIconImage(im);
		  try {
		 String imagePath = "fav.png";
         InputStream imgStream = Mymap.class.getResourceAsStream(imagePath );
         BufferedImage myImg = ImageIO.read(imgStream);
		  setIconImage(myImg);
		  } catch (Exception ie){}
		 setLayout(null); 
		 setFont(new Font("Serif",Font.BOLD,14));
		 nom = new JLabel("Nom :"); prenom = new JLabel("Prenom:"); age = new JLabel("Age :"); 
		 adres = new JLabel("Addresse :"); telephone = new JLabel("Telephone :");
	     setResizable(false);
		 ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage("C:/Program Files/Java/jdk1.7.0_45/bin/lockard/img/01.png"));
		 photo = new   JLabel("Photo");		 
		 framesPerSecond = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);
		 framesPerSecond.setMajorTickSpacing(10);
         framesPerSecond.setMinorTickSpacing(1);
          framesPerSecond.setPaintTicks(true);
          framesPerSecond.setPaintLabels(true);
		  maptype = new JComboBox(maptypes);
		  maptype.setSelectedIndex(0);
          maptype.addActionListener(this);
		  sosAdr = new JTextArea("SOS EVENEMENT:");		  
		  sosAdr.setLineWrap(true);
          sosAdr.setWrapStyleWord(true);
		  Alerdisplay = new JTextArea("ALERT:");
		  Alerdisplay.setLineWrap(true);
          Alerdisplay.setWrapStyleWord(true);
		  Zm = new JLabel("Agrandissement:");
		  Zm.setFont(new Font("Serif", Font.BOLD, 18));
		  mt = new JLabel("Type de Carte:");
		  mt.setFont(new Font("Serif", Font.BOLD, 18));
		 Mdisplayer = new JLabel("");
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
		 getContentPane().setPreferredSize(new Dimension(700,700));
		 setBounds(0, 0, width, height);	
         setLocationRelativeTo(null);		 
	     panelobj = new JPanel(new BorderLayout()); //panel login		
		 JButton button1 = new JButton(" Control");
         button2 = new JButton("Stop Sirene");
         button2.setForeground(Color.white);
         button2.setBackground(Color.blue);		 
		 button3 = new JButton("Alerte Generale");
         button4 = new JButton("Ajouter des clients"); 
		 button5 = new JButton("Quitter");
         button1.setBounds(0, 0, 150, 25);
         button2.setBounds(810, 198, 90, 25);
		 button3.setBounds(810, 165, 170, 25);
		 button3.setForeground(Color.white);
		 button3.setBackground(Color.red);
		 button4.setBounds(810, 130, 170, 25);
		 button4.setForeground(Color.white);
		 button4.setBackground(Color.blue);
		 maptype.setBounds(810, 90, 170, 25);
		 maptype.setForeground(Color.white);
		 maptype.setBackground(Color.blue);
		 Zm.setBounds(650, 50, 170, 25);
		 Zm.setForeground(Color.white);
		 mt.setBounds(650,90,170,25);
		 mt.setForeground(Color.white);
		 framesPerSecond.setBounds(810, 20, 170, 50);
		 framesPerSecond.setForeground(Color.white);
		 framesPerSecond.setBackground(Color.blue);
		 button5.setForeground(Color.white);
		 button5.setBackground(Color.blue);
		 button5.setBounds(905, 198, 75, 25);
		 sosAdr.setBounds(0,height-122, width -382, 90);
		 sosAdr.setForeground(Color.red);
		 sosAdr.setWrapStyleWord(true);		 
		 Alerdisplay.setBounds(width-380,height-122, width -670, 90);
		 Alerdisplay.setForeground(Color.red);
		 Alerdisplay.setWrapStyleWord(true);
		 Mdisplayer.setBounds(0,-62,width, height);
		 Mdisplayer.setPreferredSize(new Dimension(900, 900));
		 panelEvents = new JPanel(){
         public void paintComponent(Graphics g)
            {
            g.setColor(Color.orange);
            g.fillRect(0,0,getWidth(), getHeight());			
           }            
        };  
        nom.setBounds(650, 320, 250,25); nom.setForeground(Color.green); nom.setFont(new Font("Serif", Font.BOLD, 18));
        prenom.setBounds(650, 350, 250,25);	prenom.setForeground(Color.green); prenom.setFont(new Font("Serif", Font.BOLD, 18));
        age.setBounds(650, 380, 120,25); age.setForeground(Color.green);age.setFont(new Font("Serif", Font.BOLD, 18));
        adres.setBounds(650, 450, 350,25); adres.setForeground(Color.green); adres.setFont(new Font("Serif", Font.BOLD, 18));
        telephone.setBounds(650, 415, 300,25); telephone.setForeground(Color.green); telephone.setFont(new Font("Serif", Font.BOLD, 18));
        photo.setBounds(860, 320, 120,120);    
        Image resizedImage = image.getImage().getScaledInstance(photo.getWidth(), photo.getHeight(), Image.SCALE_SMOOTH);
		image.setImage(resizedImage);
		photo.setIcon(image);	 
        getContentPane().setLayout(new GridLayout(0,1));
        JPanel info  = new JPanel();	
        info.setBackground(Color.gray);
		info.setBounds(645,320, 350,320);
        JPanel conf  = new JPanel();
        conf.setBackground(Color.gray);	
        conf.setBounds(645,3, 350,300); 		
		panelEvents.setLayout(null);
		sosAdr.setLineWrap(true);
		sosAdr.setAutoscrolls(true);
		sp = new JScrollPane(sosAdr);
		sp.setMaximumSize(new Dimension(width -382, 90));
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setBounds(0,height-122, width -382, 90);
		sp.setSize(new Dimension(width -382,90));
        sp.setPreferredSize(new Dimension(20, 20));	

		mp = new JScrollPane(Alerdisplay);
		mp.setMaximumSize(new Dimension(width -382, 90));
		mp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mp.setBounds(width-380,height-122, width -670, 90);
		//mp.setSize(new Dimension(width -382,90));
        //mp.setPreferredSize(new Dimension(20, 20));	
		
		sosAdr.setEditable(false);
		repaint(); 			
		//panelEvents.add(button1);		
		panelEvents.add(button2);
		panelEvents.add(button3);
		panelEvents.add(photo);
		panelEvents.add(prenom);
		 panelEvents.add(nom);
		 panelEvents.add(age);
		 panelEvents.add(adres);
		 panelEvents.add(telephone);
		panelEvents.add(button4);
		panelEvents.add(Zm);
		panelEvents.add(mt);
		panelEvents.add(framesPerSecond);
		panelEvents.add(maptype);
		panelEvents.add(button5);
		panelEvents.add(Mdisplayer);
		panelEvents.add(mp);
        getContentPane().add(panelEvents); 
		panelEvents.add(sp,BorderLayout.CENTER);
		panelEvents.add(info);
		panelEvents.add(conf);
        button5.addActionListener(this);  
        button3.addActionListener(this); 
        button4.addActionListener(this);     	
        maptype.addActionListener(this);
		button2.addActionListener(this);
        framesPerSecond.addChangeListener(new ChangeListener() {
          public void stateChanged(ChangeEvent event) {
          int currV = ((JSlider)event.getSource()).getValue();
          try{
		  int sv = (int)framesPerSecond.getValue();
		  String mtype = (String)maptype.getSelectedItem();
		  DisplayMap(currV, mtype.toLowerCase(), Mainlockard.CurC);
		  } catch (Exception es){}
          }
         });		
	    }
		public void actionPerformed(ActionEvent e) {                
		if(e.getSource() == button5) {   //quitter        		
		   setVisible(false); 
           new Mainlockard().setVisible(true);
           Mainlockard.prava = 0;
           Mainlockard.con = 0;	
           Mainlockard.agent  = "";		   
		}
		if (e.getSource() == button3){ //alerte message
		ual = new User_alert();
		ual.setVisible(true);
		ual.setTitle("Lancer une alerte generale");
		ual.hidealert("a");
		}
		 if (e.getSource() == button4) { //ajouter des clients
		 if(Mainlockard.prava == 1) {
		  ual = new User_alert();
		  ual.setVisible(true);
		  ual.setTitle("Ajouter des clients");
		  ual.hidealert("u");
		  } else
		  javax.swing.JOptionPane.showMessageDialog(null,"Desole, vous n'avez pas le droit d'ajouter d'utilisateurs");
		 }
		 if (e.getSource() == maptype ){//type de carte
		    int sv = (int)framesPerSecond.getValue();
		    try{ 
			    repaint();
                maptype = (JComboBox)e.getSource();
                String mtype = (String)maptype.getSelectedItem();
				DisplayMap(sv, mtype.toLowerCase(),Mainlockard.CurC);
			    } catch (Exception ee){}
		 }
		if (e.getSource() == framesPerSecond ) { //agrandissement
		  try{
		  repaint();
		  int sv = (int)framesPerSecond.getValue();
		  String mtype = (String)maptype.getSelectedItem();		  
		  DisplayMap(sv, mtype.toLowerCase(), Mainlockard.CurC);
		  } catch (Exception es){}
		} 
		if (e.getSource() == button2){ //stop siren
		 Sound sd = new Sound();
		  sd.aret();
		}
	}	
	public static void DisplayMap(int z, String tp, String p) throws Exception {
                Mdisplayer.setIcon(new ImageIcon(createImageMap(z,tp, p)));
				//sosAdr.setText(sosAdr.getText() +"\n" + counter);
				counter++;
        }
	public static Image createImageMap(int z, String type, String par) throws MalformedURLException,IOException {
                String params = "";  
                if (!par.equals("")) params += "|" + par; 
String url = "http://maps.googleapis.com/maps/api/staticmap?zoom="+z+"&size=10240x9680&maptype="+type.toLowerCase()+"&sensor=true&markers=color:0xF76541";
                URLConnection con = new URL(url + params).openConnection();
                InputStream is = con.getInputStream();
                ByteBuffer bb = ByteBuffer.allocate(con.getContentLength());
                int readed = 0;
                byte[] b = new byte[1];
                while (readed < bb.capacity()) {
                        readed += is.read(b);
                        bb.put(b);
                }
                System.out.println(readed);
                is.close();
                Toolkit tk = Toolkit.getDefaultToolkit();
                Image map = tk.createImage(bb.array());
                tk.prepareImage(map, 800, 800, null);
                return map;
        }		
	public static synchronized void demarrer() 
    {            	  
        new Thread(new Runnable() { 
            public void run() {			  
                try {
				   String Adiff = ""; String _Sos = "";
				   while (Mainlockard.con == 1){
                    String tp = (String)maptype.getSelectedItem();
		            int sv = (int)framesPerSecond.getValue();
					_Sos = new Servercommunication().loadmap();                    							            	
					if (!_Sos.equals("")){
					 Mainlockard.CurC = getCoord(_Sos);
					 DisplayMap(sv, tp, Mainlockard.CurC);
					 Sound sd = new Sound();
		             sd.play("C:/Program Files/Java/jdk1.7.0_45/bin/lockard/sounds/trevoga.wav");
					 Bufdata = _Sos;					 
					 sosAdr.setText(sosAdr.getText() +"\n" + _Sos);
					 GetInfo(_Sos);
					} else {DisplayMap(sv, tp, Mainlockard.defautCur);}
                    Adiff = new Servercommunication().AlertGdifuse(); 
					 if(!Adiff.equals("")) {
					 Sound sd = new Sound();
		             sd.play("C:/Program Files/Java/jdk1.7.0_45/bin/lockard/sounds/trevoga.wav");
					 Alerdisplay.setText(Alerdisplay.getText() +"\n" + Adiff);
					 }					
                    new Thread().sleep(5000); 
					}
                } catch (Exception e) {                    
                }
            }
        }).start();		
    }
	public static String getCoord(String st){
	 int res =  st.indexOf("|");
	 return st.substring(0, res);
	}
	public static void GetInfo(String st){
	 int res =  st.indexOf("|");
	 String str = st.substring(res+1, st.length());
	 String[] split = str.split(",");
	 nom.setText(nom.getText()+split[0]); 
	 prenom.setText(prenom.getText()+ split[1]); 
	 age.setText(age.getText() + split[2]); adres.setText(adres.getText()+ split[4]); telephone.setText(telephone.getText()+split[3]);
	}
}

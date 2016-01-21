package lockard;
import java.io.*;
import java.net.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class Servercommunication   {  
  public static String AlertG(String message,String sender){
  if (!sender.equals("")) {
   String urls = "url?"+sender+"|"+ message;
   try{
     URL url = new URL(urls);
	 URLConnection c = url.openConnection();
	 BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream())); 
	 String serresponse, bufres = ""; 
	 while ((serresponse = in.readLine()) != null){
	   bufres += serresponse;
	 }
	 javax.swing.JOptionPane.showMessageDialog(null,bufres);
	 if (!bufres.equals("")) return bufres;
    } catch (UnsupportedEncodingException e){
     e.printStackTrace();
   } catch (MalformedURLException e) {e.printStackTrace();} 
   catch (IOException e){e.printStackTrace();} 
   } else {
    javax.swing.JOptionPane.showMessageDialog(null,"Utilisateur inconnu");
   }   
   return "" ;
}  
 public static String loadmap(){
   String urls = "http://lockard2.com/Vsos/loadmap.php";
   try{
     URL url = new URL(urls);
	 URLConnection c = url.openConnection();
	 BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream())); 
	 String serresponse, bufres = ""; 
	 while ((serresponse = in.readLine()) != null){
	   bufres += serresponse;	   
	 }
	 if (!bufres.equals("")) return bufres;
    } catch (UnsupportedEncodingException e){
     e.printStackTrace();
   } catch (MalformedURLException e) {e.printStackTrace();} 
   catch (IOException e){e.printStackTrace();} 
     
   return "" ;
}  
 public static String AlertGdifuse(){
   String urls = "http://lockard2.com/Vsos/alertdifuse.php?";
   try{
     URL url = new URL(urls);
	 URLConnection c = url.openConnection();
	 BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream())); 
	 String serresponse, bufres = ""; 
	 while ((serresponse = in.readLine()) != null){
	   bufres += serresponse;	   
	 }
	 if (!bufres.equals("")) return bufres;
    } catch (UnsupportedEncodingException e){
     e.printStackTrace();
   } catch (MalformedURLException e) {e.printStackTrace();} 
   catch (IOException e){e.printStackTrace();} 
     
   return "" ;
}  
  public static void Adduser(String nom, String pn, String ag, String telephone, String adress, String id, String ilink){
     if ((nom == "") || (pn == "") || (ag == "") || (telephone == "")|| (adress == "") || (id=="")){
	 javax.swing.JOptionPane.showMessageDialog(null,"Verifier que tous les champs ont ete correctement remplis.");
	 } else {
	 String param =nom.trim() +"|"+pn.trim()+"|"+ag.trim()+"|"+telephone.trim()+"|"+adress.trim()+"|"+id.trim()+"|"+ilink;
	 String urls = "http://lockard2.com/Vsos/useradd.php?"+ param.trim();
   try{
     URL url = new URL(urls);
	 URLConnection c = url.openConnection();
	 BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream())); 
	 String serresponse, bufres = ""; 
	 while ((serresponse = in.readLine()) != null){
	   bufres += serresponse;
	 }
	 javax.swing.JOptionPane.showMessageDialog(null,bufres);
    } catch (UnsupportedEncodingException e){
     e.printStackTrace();
    } catch (MalformedURLException e) {e.printStackTrace();} 
     catch (IOException e){e.printStackTrace();}
	 }  
  } 
  public static void loadUser(String id, String telephone){
    if ((id == "") ||(telephone == "")){
	 javax.swing.JOptionPane.showMessageDialog(null,"Verifier que le numero d'identification est correct.");
	} else {
	
	
	}
  }
  
 /* public static void getLinks(String xhtmlUrl) throws Exception{
    if ((id == "") ||(telephone == "")){
	 javax.swing.JOptionPane.showMessageDialog(null,"Verifier que le numero d'identification est correct.");
	} else  {
	InputStream stream = null;
	try{
		DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
		fabrique.setValidating(true);		
		DocumentBuilder constructeur = fabrique.newDocumentBuilder();		
		URL url = new URL(xhtmlUrl);
		stream = url.openStream();
		Document document = constructeur.parse(stream);	
        document.getDocumentElement().normalize();		
		Element racine = document.getDocumentElement();
		String tag = "user";
		NodeList liste = racine.getElementsByTagName(tag);		
		for(int i=0; i<liste.getLength(); i++){
		 NodeList nl = liste.item(i); 
         if (nl.getNodeType() == NodeList.ELEMENT_NODE)	{
		 Element eElement = (Element) nl;
         String id =  eElement.getAttribute("id");
		 String nom = eElement.getAttribute("nom");
			
		 }	 
		}
	}catch(Exception e){
		throw e;
	}finally{
		try{stream.close();}catch(Exception e){}		
	 }
	}
  }*/
  
  public static void readCoord(String p){
     String url = "http://lockard2.com/Vsos/Sos.php?s="+p;   
        try
        {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(url);
            doc.getDocumentElement().normalize();
            NodeList items = doc.getElementsByTagName("user");
            for (int i = 0; i < items.getLength(); i++)
            {
                Node n = items.item(i);
                if (n.getNodeType() != Node.ELEMENT_NODE) continue;
                 Element e = (Element) n;
                 String nom = e.getAttribute("nom");
				 String pn  = e.getAttribute("pn");
				 String ag  = e.getAttribute("age");
				 String te =  e.getAttribute("telephone");
				// String agg =  e.getAttribute("age"); 
				 String adresse =  e.getAttribute("addresse");
				 String lat = e.getAttribute("lat");
				 String lng = e.getAttribute("lng");
				 String idStrack = e.getAttribute("id");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
	//ready
   public static int uLogin(String l, String p, String c){
   String urls = "http://lockard2.com/Vsos/adminlog.php?"+l+","+p+","+c;
    int res = 0;
   try{
     URL url = new URL(urls);
	 URLConnection lc = url.openConnection();
	 BufferedReader in = new BufferedReader(new InputStreamReader(lc.getInputStream())); 
	 String serresponse, bufres = ""; 
	 while ((serresponse = in.readLine()) != null){
	   bufres += serresponse;
	 }
	if (bufres.equals("2001")){Mainlockard.con = 1; Mainlockard.prava = 1; res = 1;}
	else if (bufres.equals("2000")){ Mainlockard.con = 1; Mainlockard.prava =0; res = 1; } 
    } catch (UnsupportedEncodingException e){
     e.printStackTrace();
    } catch (MalformedURLException e) {e.printStackTrace();} 
    catch (IOException e){e.printStackTrace();}
	return res;
	}
   
}

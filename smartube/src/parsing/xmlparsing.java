package parsing;
import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException; 

public class xmlparsing{
    
	public static void metod()throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException
    {
    	int[] cc = new int[500] ;
    	int[][] vc=new int[10][40];
    	int j;
    	int i;
    	String[] lang=new String[20];
    	String[] gen=new String[20];
    	String[] art=new String[20];
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://vhost0057.site1.compute.ihost.com:3306/dataentry";
        String user = "root";
        String password = "root";

    try{ 
    		Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement("SELECT language,genre,artist FROM query WHERE groupname IN(SELECT  groupname FROM query GROUP BY groupname HAVING COUNT(*) > 2) ");
            rs = pst.executeQuery();
          
            	j=0;
            	while (rs.next())
            	{
                lang[j]=rs.getString(1);
                gen[j]=rs.getString(2);
                art[j]=rs.getString(3);
                j++;
            	}
            	//System.out.println(language[1]);
            	  // pst=con.prepareStatement("Truncate table query");
                  // pst.executeQuery();	
                  	DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
    			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();	
        for(int k=0;k<j;k++)
        	{		
        		
    			String crl="https://gdata.youtube.com/feeds/api/videos/-/language/genre/artist?language="+ lang[k] +"&genre="+ gen[k] +"&artist="+ art[k]+"&max-results=40&category=Entertainment";
    			System.out.println(crl);
        		Document doc=docBuilder.parse(new URL(crl).openStream());   		
        		doc.normalize();
        		System.out.println("root:"+ doc.getDocumentElement().getNodeName());
        		NodeList entrylist=doc.getElementsByTagName("entry");
        		NodeList yttlist= doc.getElementsByTagName("yt:statistics");
        		for(i=0;i<entrylist.getLength();i++)
        			{
        				Node ytlist=yttlist.item(i);
        				Element ylist=(Element)ytlist;
        				String viewcount=ylist.getAttribute("viewCount");
        				vc[k][i]= Integer.parseInt(viewcount);
        			//	System.out.println("viewcount :"+vc[k][i]);
        				
        				
        			}
        		
        	}
        		
        			int m=0;
        			cc[m]=0;
        			for(int k=0;k<j;k++)
        			{
        				for(int n=0;n<vc[k].length;n++)
        				{	cc[m]=vc[k][n]+cc[m];
        					m++;
        				}
        			}
        	   	int h=0;
        	   	while(cc[h]!=0)
        	   	{
        	   		
        			System.out.println(cc[h]);
        			h++;
        	   	}
        			Arrays.sort(cc);
        			for( int l = 0; l < cc.length/2; ++l ) 
					{ 
	  					int temp = cc[l]; 
	  					cc[l] = cc[cc.length - l - 1]; 
	  					cc[cc.length - l - 1] = temp; 
					} 
        			
        	}
        catch (SQLException ex) 
    		{
        		Logger lgr = Logger.getLogger(xmlparsing.class.getName());
        		lgr.log(Level.SEVERE, ex.getMessage(), ex);
    		}
	
    }
    
}
    
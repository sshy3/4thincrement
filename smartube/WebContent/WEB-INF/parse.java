package parsing;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

@Path("generic")
public class parse {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public parse() {
        // TODO Auto-generated constructor stub
    	
    }

    /**
     * Retrieves representation of an instance of parsing
     * @return an instance of String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        // TODO return proper representation object
        throw new UnsupportedOperationException();
        
        
    }

    /**
     * PUT method for updating or creating an instance of parsing
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     * @throws ClassNotFoundException 
     * @throws ParserConfigurationException 
     * @throws IOException 
     * @throws SAXException 
     */
   /* @PUT
    @Consumes("application/xml")
    public void putXml(String content) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException {
    	xmlparsing.metod();
    }*/


@GET
@Produces("text/plain")
@Path("/parse")
public void parsexml(){
	try {
		xmlparsing.metod();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

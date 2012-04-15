package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        String studentNr = "12345678";
        if ( args.length>0) {
            studentNr = args[0];
        }
 
        String url = "http://ohtustats.herokuapp.com/opiskelija/"+studentNr+".json";
 
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        try {
            client.executeMethod(method);
        } catch (HttpException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        InputStream stream =  method.getResponseBodyAsStream();
 
        String bodyText = IOUtils.toString(stream);
 
        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );
 
        Gson mapper = new Gson();
        Palautukset palautukset = mapper.fromJson(bodyText, Palautukset.class);
 
        System.out.println("Opiskelijan tiedot");
        for (Palautus palautus : palautukset.getPalautukset()) {
            System.out.println( palautus );
        }
 
    }
}

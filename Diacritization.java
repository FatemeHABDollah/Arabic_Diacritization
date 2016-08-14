/*
 * Using this class u can diacritize Arabic Text with noorsoft site
 */
package aratest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author Fatemeh Abdollah
 */
public class Diacritization {
    private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

	}

	public String addDiacritics(String currenttext) throws Exception{
            String url = "http://textmining.noorsoft.org/Lab/HttpHandler/Query.ashx";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String matn = URLEncoder.encode(currenttext , "UTF-8");
            String urlParameters = "EnterTextBox="+ matn +"&ProcessName=Diacritization";


            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            in.close();
            
            //Returns the http response 
            return response.toString();
        }
}

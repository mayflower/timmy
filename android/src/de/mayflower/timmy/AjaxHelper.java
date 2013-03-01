package de.mayflower.timmy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.util.Log;

public class AjaxHelper {

	public static class MakeRequestTask extends AsyncTask<URL, Integer, Long> {
	    
		String url = null;
		String postData = null;
		
		MakeRequestTask(String m_url, String m_postData) {
			
			url = m_url;
			postData = m_postData;
			
		}
		
		// Do the long-running work in here
	    protected Long doInBackground(URL... urls) {
	        AjaxHelper.makeAjaxRequest(url, postData);
	        return (long)0;
	    }

	    // This is called each time you call publishProgress()
	    protected void onProgressUpdate(Integer... progress) {
	    	Log.i("request task", "status update");
	    }

	    // This is called when doInBackground() is finished
	    protected void onPostExecute(Long result) {
	    	Log.i("request task", "request completed");
	    }
	}
	
	private static void makeAjaxRequest(String urlString, String postData) {
		
		Log.i("makeAjaxRequest", "BEGIN");
		
		StringBuilder contents = new StringBuilder(2048);
        BufferedReader br = null;
        InputStream istream = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection)url.openConnection();

            conn.setRequestProperty("User-Agent", "Opera/9.80 (Windows NT 6.1; WOW64; U; de) Presto/2.10.289 Version/12.02");
            conn.setRequestProperty("Accept", "text/html, application/xml;q=0.9, application/xhtml+xml, image/png, image/webp, image/jpeg, image/gif, image/x-xbitmap, */*;q=0.1");
            conn.setRequestProperty("Accept-Language", "de-DE,de;q=0.9,en;q=0.8");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Connection", "Keep-Alive");
            
            conn.setRequestMethod("POST");
            
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(postData);
            wr.flush();
            wr.close();
            
            istream = conn.getInputStream();

            br = new BufferedReader(new InputStreamReader(istream));
            String line = "";
            while (line != null) {
                line = br.readLine();
                contents.append(line);
            }
            br.close();
            istream.close();
            
        } catch (Throwable t) {
        	Log.i("makeAjaxRequest: exception", t.getMessage());
        }
       
        Log.i("makeAjaxRequest", contents.toString());
        
        Log.i("makeAjaxRequest", "END");

        //return contents.toString();
				
	}
	
}

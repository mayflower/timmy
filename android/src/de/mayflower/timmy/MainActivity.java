package de.mayflower.timmy;

import org.codehaus.jackson.map.ObjectMapper;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

public class MainActivity
	extends Activity
	implements OnClickListener
	{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button mySubmitButton = (Button) this.findViewById(R.id.button1);
        mySubmitButton.setOnClickListener(this);
    }
    
    @Override public void onClick(View v) {
    	
    	Log.i("OnClick", "BEGIN");
    
    	EditText textFrom = (EditText) this.findViewById(R.id.editText1);
    	EditText textTo = (EditText) this.findViewById(R.id.editText2);
    	EditText textDescription = (EditText) this.findViewById(R.id.editText3);
    	
    	String fromString = textFrom.getText().toString();
    	String toString = textTo.getText().toString();
    	String descriptionString = textDescription.getText().toString();
    	
    	Log.i("TextFrom", fromString);
    	Log.i("TextTo", toString);
    	Log.i("TextDescription", descriptionString);
    	
    	// Check parameters:
    	if (fromString.equals("") || toString.equals("") || descriptionString.equals("")) {
    		Toast.makeText(this, "Arbeitszeit konnte nicht gebucht werden...", Toast.LENGTH_SHORT).show();
    	} else {
    	
	    	// Call REST Service
	    	
	    	String url = "http://172.19.1.12:8000/tracks";
	    	
	    	Request req = new Request();
	    	req.setDescription(descriptionString);
	    	req.setStartTime(fromString);
	    	req.setEndTime(toString);
	    	
	    	// JSon conversion:
	    	ObjectMapper mapper = new ObjectMapper();
	    	String postData = null;   	
	    	try {
	    		postData = mapper.writeValueAsString (req.format());
	    	} catch(Exception e) {
	    		Log.e("OnClick", "JSon Mapping failed!");
	    	}
	    	
	    	new AjaxHelper.MakeRequestTask(url, postData, this).execute();
    	
    	}
    	
    	Log.i("OnClick", "END");
    
    }
    
    public void postAjaxResponse() {
    	
    	Log.i("postAjaxResponse",AjaxHelper.response);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	try {
    		Response req = (Response) mapper.readValue(AjaxHelper.response, Response.class);
    		
    		EditText textFrom = (EditText) this.findViewById(R.id.editText1);
	    	EditText textTo = (EditText) this.findViewById(R.id.editText2);
	    	// Print OK message and clear form....
	    	textFrom.setText("");
	    	textTo.setText("");
	    	
	    	Toast.makeText(this, "Arbeitszeit wurde gebucht f�r Task: " + req.track.description, Toast.LENGTH_SHORT).show();
    		
    	} catch(Exception e) {
    		Toast.makeText(this, "Arbeitszeit konnte nicht gebucht werden (keine Verbindung zum Server!)...", Toast.LENGTH_SHORT).show();
    		Log.e("postAjaxResponse",AjaxHelper.response);
    	}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}

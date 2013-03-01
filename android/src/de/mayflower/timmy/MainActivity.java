package de.mayflower.timmy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

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
	    	
	    	String url = "http://cat510.muc.mayflower.de:8000/tracks";
	    	Calendar calendar = Calendar.getInstance();
	    	
	    	// Set parameters:
	    	calendar.set(2013,3,1,11,11,0);
	    	//Date from = calendar.getTime();
	    	String from = "2013-03-01T" + fromString + ":00.123Z";
	    	
	    	calendar.set(2013,3,1,11,11,0);
	    	//Date to = calendar.getTime();
	    	String to = "2013-03-01T" + toString + ":00.123Z";
	    
	    	// Build parameter list:
	    	HashMap<String,Object> params = new HashMap();
	    	params.put("description", descriptionString);
	    	params.put("start",from);
	    	params.put("end", to);
	    	
	    	// JSon conversion:
	    	ObjectMapper mapper = new ObjectMapper();
	    	String postData = null;   	
	    	try {
	    		postData = mapper.writeValueAsString (params);
	    	} catch(Exception e) {
	    		Log.e("OnClick", "JSon Mapping failed!");
	    	}
	    	
	    	new AjaxHelper.MakeRequestTask(url, postData).execute();
	    	// ...
	    	
	    	// Print OK message and clear form....
	    	textFrom.setText("");
	    	textTo.setText("");
	    	Toast.makeText(this, "Arbeitszeit wurde gebucht...", Toast.LENGTH_SHORT).show();  
    	}
    	
    	Log.i("OnClick", "END");
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}

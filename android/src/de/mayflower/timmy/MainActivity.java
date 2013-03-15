package de.mayflower.timmy;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

public class MainActivity
	extends Activity
	implements OnClickListener
	{
	
	public static final String FIELD_MESSAGE = "de.mayflower.timmy.FIELD_MESSAGE";
	
	public static final String FIELD_VALUE_VON = "von";
	public static final String FIELD_VALUE_BIS = "bis";
	
	static String from = "";
	static String to = "";
	static String description = "";
	
	public void saveValues() {
		
		EditText textFrom = (EditText) this.findViewById(R.id.editText1);
		from = textFrom.getText().toString();
		
		EditText textTo = (EditText) this.findViewById(R.id.editText2);
		to = textTo.getText().toString();
		
		EditText textDescription = (EditText) this.findViewById(R.id.editText3);
		description = textDescription.getText().toString();
		
	}
	
	public void setValues() {
		
		EditText textFrom = (EditText) this.findViewById(R.id.editText1);
		textFrom.setText(from);
		
		EditText textTo = (EditText) this.findViewById(R.id.editText2);
		textTo.setText(to);
		
		EditText textDescription = (EditText) this.findViewById(R.id.editText3);
		textDescription.setText(description);
		
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setValues();
        
        Intent intent = getIntent();
        
        if (intent != null) {
        
	        Date message = (Date)intent.getSerializableExtra(DateTimePickerActivity.DATETIME_MESSAGE);
	        
	        if (message != null) {
	        	
	        	String field = intent.getStringExtra(FIELD_MESSAGE);
	        	
	        	if (field.equals(FIELD_VALUE_VON)) {
	        		EditText textFrom = (EditText) this.findViewById(R.id.editText1);
		        	textFrom.setText(Integer.toString(message.getHours()) + ":" + Integer.toString(message.getMinutes()));
	        	} else {
	        		EditText textTo = (EditText) this.findViewById(R.id.editText2);
	        		textTo.setText(Integer.toString(message.getHours()) + ":" + Integer.toString(message.getMinutes()));
	        	}
	        	
	        	
	        }
        
        }
        
        //Button mySubmitButton = (Button) this.findViewById(R.id.button1);
        //mySubmitButton.setOnClickListener(this);
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
	    	
	    	Toast.makeText(this, "Arbeitszeit wurde gebucht für Task: " + req.track.description, Toast.LENGTH_SHORT).show();
    		
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
    
    public void showDateTimePickerVon(View view) {
    	
    	saveValues();
    	
    	Intent intent = new Intent(this, DateTimePickerActivity.class);

    	intent.putExtra(FIELD_MESSAGE, FIELD_VALUE_VON);
    	
    	startActivity(intent);
    	
    }
    
 public void showDateTimePickerBis(View view) {
    	
	 	saveValues();
	 
    	Intent intent = new Intent(this, DateTimePickerActivity.class);
    	
    	intent.putExtra(FIELD_MESSAGE, FIELD_VALUE_BIS);
    	
    	startActivity(intent);
    	
    }
    
}

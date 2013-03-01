package de.mayflower.timmy;

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
    	
    	Log.i("TextFrom", textFrom.getText().toString());
    	Log.i("TextTo", textTo.getText().toString());
    	
    	// Call REST Service
    	
    	// ...
    	
    	// Print OK message and clear form....
    	textFrom.setText("");
    	textTo.setText("");
    	Toast.makeText(this, "Arbeitszeit wurde gebucht...", Toast.LENGTH_SHORT).show();  
    	
    	Log.i("OnClick", "END");
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}

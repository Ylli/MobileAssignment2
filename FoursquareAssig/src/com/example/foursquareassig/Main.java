package com.example.foursquareassig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Main extends Activity {

	public static final String CLIENT_ID = "4JB2U43SBO1DGX04C4GO2NRCRR505SICPUBSK1TEP3DAE5S3";
	public static final String CLIENT_SECRET = "T5FAM3GXNRWYGNUMGEECYINAP0BRTFTTJIY33AOO5SEGHAHC";
	public static final String API_URL = "https://api.foursquare.com/v2";
	public static String urlString;
	public static JSONRequest jr = new JSONRequest();
	static final int check = 1111;
	
	public static String[] from = { "txt","pl"};
	// Id-s of views in listview_layout
	public static int[] to = { R.id.txt,R.id.pl};
	public CheckIntGps checkintgps;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);	
        FillSpinner(R.id.spinner1, R.array.search);
        FillSpinner(R.id.Spinner2, R.array.distance);
      
        double lat = 60.7943;  //Convert to String
        double lon = 10.6889;	 //Convert to String
        int radius = 500;  //Convert to String
        String categoryId = "4d4b7105d754a06374d81259";
        String slat = Double.toString(lat);
        String slon = Double.toString(lon);
        String sradius = Integer.toString(radius);
        
        urlString = API_URL + "/venues/search?ll=" + slat + "," + slon + "&intent=browse&radius=" + sradius + "&categoryId=" + categoryId + "&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v=20111218";
       // urlString = "https://api.foursquare.com/v2/venues/search?ll=60.7943,10.6889&intent=browse&radius=100000&categoryId=4bf58dd8d48988d1d6941735&client_id=4JB2U43SBO1DGX04C4GO2NRCRR505SICPUBSK1TEP3DAE5S3&client_secret=T5FAM3GXNRWYGNUMGEECYINAP0BRTFTTJIY33AOO5SEGHAHC&v=20111218";
        
    /*    
       listView.setOnItemClickListener(new OnItemClickListener() {
            
  			public void onItemClick(AdapterView<?> arg0, View view, int position,
				long id) {
			// TODO Auto-generated method stub
  			// selected item
                String restaurant = ((TextView)view.findViewById(R.id.txt)).getText().toString();
                String dis=((TextView)view.findViewById(R.id.pl)).getText().toString();
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), Newintent.class);
                // sending data to new activity
                i.putExtra("restaurant", restaurant);
                startActivity(i);
              
		}
    	}); */
    }
    //MICROPHONE
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==check && resultCode==RESULT_OK){
			ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			//lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results));
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
    
	public void Search(View v)
    {
        Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        String category = s1.getSelectedItem().toString();
       	List<HashMap<String,String>> list;
       	jr = new JSONRequest();
       	list = jr.getJSONfromURL(urlString);
       	ListAdapter adapter = new SimpleAdapter(getBaseContext(), list, R.layout.listview_layout, from,to);
        	
       	// Getting a reference to listview of main.xml layout file
       	ListView listView = ( ListView ) findViewById(R.id.listview);
        	
       	// Setting the adapter to the listView
       	listView.setAdapter(adapter);
    }
   
    public void VoiceSearch(View v)
    {
		Intent i= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak UP BITCH!");
		startActivityForResult(i,check);
    }
    public void FillSpinner(int i, int j)
    {
        Spinner spinner = (Spinner) findViewById(i);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, j, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}

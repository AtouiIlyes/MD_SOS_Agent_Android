package pfe.ilyes.mdsosagent;




import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Edit_fichesante extends Activity{

	EditText name;
    EditText typesang;
    EditText nassurance;
    ToggleButton toggle;
    EditText typassurence;
    EditText pathologie1;
    EditText pathologie2;
    EditText allergie1;
    EditText allergie2;
    EditText traitement;
    EditText antfamil1;
    EditText antfamil2;
    ToggleButton certif;
    
    String sname;
    String sdonorgane;
    String dsdonorgane;
    String stypeassurence;
    String snassurance;
    String spathologie1;
    String spathologie2;
    String sallergie1;
    String sallergie2;
    String stypesang;
    String santfamil1;
    String santfamil2;
    String straitement;
    String smedecin;
    String certification;
    String dcertification;
    
    String fsname;
    String fsdonorgane;
    String fstypeassurence;
    String fsnassurance;
    String fspathologie1;
    String fspathologie2;
    String fsallergie1;
    String fsallergie2;
    String fstypesang;
    String fsantfamil1;
    String fsantfamil2;
    String fstraitement;
    String fsmedecin;
    String fcertification;
    
    InputStream is=null;
	String result=null;
	String line=null;
	
	String email;
	Button mButton;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_fichedesante);
		
		
		Intent i = getIntent();
		email =i.getStringExtra("email");
		 fsname =i.getStringExtra("sname");
		 fsnassurance =i.getStringExtra("snassurance");
		 fsdonorgane =i.getStringExtra("sdonorgane");
		 fstypesang =i.getStringExtra("stypesang");
		 fstypeassurence =i.getStringExtra("stypeassurence");
		 fspathologie1 =i.getStringExtra("spathologie1");
		 fspathologie2 =i.getStringExtra("spathologie2");
		 fsallergie1 =i.getStringExtra("sallergie1");
		 fsallergie2 =i.getStringExtra("sallergie2");
		 fsantfamil1 =i.getStringExtra("santfamil1");
		 fsantfamil2 =i.getStringExtra("santfamil2");
		 fstraitement =i.getStringExtra("straitement");
		
		
		name=(EditText)findViewById(R.id.et1);
       
        typesang=(EditText) findViewById(R.id.spinner);
        typassurence=(EditText) findViewById(R.id.tv5);
        nassurance=(EditText)findViewById(R.id.et3);
		  toggle = (ToggleButton)findViewById(R.id.tb1);
		  certif = (ToggleButton)findViewById(R.id.tb2);
		  typassurence=(EditText)findViewById(R.id.tv5);
		
		  pathologie1=(EditText)findViewById(R.id.et4);
		  pathologie2=(EditText)findViewById(R.id.et8);
		
		  allergie1=(EditText)findViewById(R.id.et10);
		  allergie2=(EditText)findViewById(R.id.et11);
		
		  traitement=(EditText)findViewById(R.id.et7);
       
		  antfamil1=(EditText)findViewById(R.id.et14);
		  antfamil2=(EditText)findViewById(R.id.et12);
		  
		  	name.setText(fsname);
			 nassurance.setText(fsnassurance);
			 typesang.setText(fstypesang);
			 typassurence.setText(fstypeassurence);
			 pathologie1.setText(fspathologie1);
			 pathologie2.setText(fspathologie2);
			 allergie1.setText(fsallergie1);
			 allergie2.setText(fsallergie2);
			 traitement.setText(fstraitement);
			 antfamil1.setText(fsantfamil1);
			 antfamil2.setText(fsantfamil2);
			 if(fsdonorgane.equals("Oui")){
				 toggle.setChecked(true);
			 }
			 else{
				 toggle.setChecked(false);

			 }
			 
			 
			 mButton=(Button)findViewById(R.id.b1);
	         mButton.setOnClickListener(new OnClickListener() {
	        	 InputStream is = null;
	 			@Override
				public void onClick(View v) {
	 				StrictMode.ThreadPolicy policy = 
	 		 				new StrictMode.ThreadPolicy.Builder().permitAll().build();
	 		 		StrictMode.setThreadPolicy(policy);
	 		 		
	 		 		if (toggle.isChecked()==(true)) {
	 			 		 dsdonorgane="Oui";
	 			 		}
	 			 		if (toggle.isChecked()==(false)) {
	 			 			 dsdonorgane="Non";
	 			 		}
	 			 		
	 		    	 		if (certif.isChecked()==(true)) {
	 		       	 		 dcertification="certifiee";
	 		       	 		}
	 		       	 		if (certif.isChecked()==(false)) {
	 		       	 			 dcertification="non certifiee";
	 		       	 		}
	 		 		
	 				update( dsdonorgane,dcertification);
	 		Toast.makeText(getApplicationContext(), "Certification effectuee", Toast.LENGTH_SHORT).show();
	 		
	 			}
	 			
	 		});
			 
			 }
    
    
    
    void update(String dsdonorgane,String dcertification) {
    	
    	
    	
    		sname=name.getText().toString();
    		snassurance=nassurance.getText().toString();
    		spathologie1=pathologie1.getText().toString();
    		spathologie2=pathologie2.getText().toString();
    		sallergie1=allergie1.getText().toString();
    		sallergie2=allergie2.getText().toString();
    		santfamil1=antfamil1.getText().toString();
    		santfamil2=antfamil2.getText().toString();
    		stypeassurence=typassurence.getText().toString();
    		straitement=traitement.getText().toString();
    		stypesang = typesang.getText().toString();
    		
    		
    	
    	
    	
    	
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	
    		nameValuePairs.add(new BasicNameValuePair("email", email));
    		nameValuePairs.add(new BasicNameValuePair("sname", sname));
    		nameValuePairs.add(new BasicNameValuePair("snassurance", snassurance));
    		nameValuePairs.add(new BasicNameValuePair("sdonorgane", dsdonorgane));
    		nameValuePairs.add(new BasicNameValuePair("stypesang", stypesang));
    		nameValuePairs.add(new BasicNameValuePair("stypeassurence", stypeassurence));
    		nameValuePairs.add(new BasicNameValuePair("spathologie1", spathologie1));
    		nameValuePairs.add(new BasicNameValuePair("spathologie2", spathologie2));
    		nameValuePairs.add(new BasicNameValuePair("sallergie1", sallergie1));
    		nameValuePairs.add(new BasicNameValuePair("sallergie2", sallergie2));
    		nameValuePairs.add(new BasicNameValuePair("santfamil1", santfamil1));
    		nameValuePairs.add(new BasicNameValuePair("santfamil2", santfamil2));
    		nameValuePairs.add(new BasicNameValuePair("straitement", straitement));
    		nameValuePairs.add(new BasicNameValuePair("certification", dcertification));
    	    
    		try{
    			HttpClient httpclient = new DefaultHttpClient();
    			HttpPost httpPost = new HttpPost("http://192.168.100.1/update_fichesante.php");
    			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    			HttpResponse response = httpclient.execute(httpPost);
    			HttpEntity entity = response.getEntity();
    			is = entity.getContent();
    			
    			
    		 
    		} catch (ClientProtocolException e) {
    			Log.e("ClientProtocol","log_tag");
    			e.printStackTrace();
    		}
    		catch(Exception e){
    			Log.e("log_tag", "Error in http connection " + e.toString());
    		}
        	
    } 
    
    
}


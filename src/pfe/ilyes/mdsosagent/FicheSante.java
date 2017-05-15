package pfe.ilyes.mdsosagent;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FicheSante extends Activity{

	ImageButton b;
	ImageView certif;
	TextView Nom;
	TextView date;
	TextView NumAss;
	TextView groupeSang;
	TextView DonneurOrg;
	TextView Assurence;
	TextView Path1;
	TextView Path2;
	TextView Aller1;
	TextView Aller2;
	TextView Trait1;
	TextView Trait2;
	TextView Ant1;
	TextView Ant2;
	TextView Med;
	String re;
	

	String url = "http://192.168.100.1/get_fichesante.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fiche_sante);
		
		
		 Nom=(TextView) findViewById(R.id.textView1);
	        NumAss=(TextView) findViewById(R.id.textView3);
	        groupeSang=(TextView) findViewById(R.id.textView7);
	        DonneurOrg=(TextView) findViewById(R.id.textView10);
	        Assurence=(TextView) findViewById(R.id.textView12);
	        Path1=(TextView) findViewById(R.id.textView16);
	        Path2=(TextView) findViewById(R.id.textView17);
	        Aller1=(TextView) findViewById(R.id.textView20);
	        Aller2=(TextView) findViewById(R.id.textView21);
	        Trait1=(TextView) findViewById(R.id.textView24);
	        Trait2=(TextView) findViewById(R.id.textView25);
	        Ant1=(TextView) findViewById(R.id.textView28);
	        Ant2=(TextView) findViewById(R.id.textView29);
	        Med=(TextView) findViewById(R.id.textView31);
	        
	        certif=(ImageView) findViewById(R.id.imageView1);
	        
	        b=(ImageButton) findViewById(R.id.btn1);
	        b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i=new Intent(FicheSante.this, GetRequest.class);
					startActivity(i);
					
				}
			});
	        
	        if(android.os.Build.VERSION.SDK_INT > 9){
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				
			}
	        
	        affich();
}
	
	
public void affich(){
		
		try{
			JSONArray data = new JSONArray(getJSONUrl("http://192.168.26.51/get_fichesante.php"));
			
			
			for(int i = 0; i < data.length(); i++){
				JSONObject c = data.getJSONObject(i);
				String sname=c.getString("sname");
				String snassurance=c.getString("snassurance");
				String sdonorgane=c.getString("sdonorgane");
				String stypesang=c.getString("stypesang");
				String stypeassurence=c.getString("stypeassurence");
				String spathologie1=c.getString("spathologie1");
				String spathologie2=c.getString("spathologie2");
				String sallergie1=c.getString("sallergie1");
				String sallergie2=c.getString("sallergie2");
				String santfamil1=c.getString("santfamil1");
				String santfamil2=c.getString("santfamil2");
				String straitement=c.getString("straitement");
				String smedecin=c.getString("smedecin");
				String certification=c.getString("certification");
				
				// Résultats de la requête
				
				 Nom.setText(sname);
				 NumAss.setText(snassurance);
				 groupeSang.setText(stypesang);
				 DonneurOrg.setText(sdonorgane);
				 Assurence.setText(stypeassurence);
				 Path1.setText(spathologie1);
				 Path2.setText(spathologie2);
				 Aller1.setText(sallergie1);
				 Aller2.setText(sallergie2);
				 Trait1.setText(straitement);
				 Trait2.setText(straitement);
				 Ant1.setText(santfamil1);
				 Ant2.setText(santfamil2);
				 Med.setText(smedecin);
				
				 if(certification.equals("non certifiee")){
					 certif.setVisibility(View.INVISIBLE);
				 }else {
					 certif.setVisibility(View.VISIBLE);
				 }
				
			}
			
			
			
		}catch(JSONException e){
			e.printStackTrace();
		}		
		
	}

	//connect to server
	public String  getJSONUrl(String url) {
		Intent intent = getIntent();
			
		 String email = intent.getStringExtra("email");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("email",email));
		
		StringBuilder strBuilder = new StringBuilder();
		try{
		
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://192.168.100.1/get_fichesante.php");
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		HttpResponse response = httpclient.execute(httpPost);
		StatusLine sLine = response.getStatusLine();
			int statusCode = sLine.getStatusCode();
			if(statusCode == 200){
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while((line = reader.readLine()) != null){
					strBuilder.append(line);
				}
			}else{
				Log.e("Log", "Failed to download result...");
				  Toast.makeText(this,"echec de l'affichage de fiche de sante", Toast.LENGTH_LONG)
                          .show();
			}
			
		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		 re= strBuilder.toString();
		return re;
	}
}
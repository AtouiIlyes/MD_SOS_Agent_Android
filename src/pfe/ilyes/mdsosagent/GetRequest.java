package pfe.ilyes.mdsosagent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class GetRequest extends Activity  {
	String job;
	String username;
	Button refresh;

@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.get_request);
				
				View v = null;
				
				Intent in= getIntent();
				 job=in.getStringExtra("job");
				 username=in.getStringExtra("username");
					affiche(v);
					
					

		   
		    }
			
  public void affiche(View v){
	  
	  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	  
	  final ListView listView1 = (ListView)findViewById(R.id.listView1);
		String url = "http://192.168.100.1/get_data.php";
		try{
			JSONArray data = new JSONArray(getJSONUrl(url));
			final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
			
			
			for(int i = 0; i < data.length(); i++){
				JSONObject c = data.getJSONObject(i);
              
  			map = new HashMap<String, String>();
				
				map.put("email", c.getString("email"));
				map.put("danger", c.getString("danger"));
				map.put("latitude", c.getString("latitude"));
				map.put("longitude", c.getString("longitude"));
				map.put("adresse", c.getString("adresse"));
				map.put("created_at", c.getString("created_at"));
				MyArrList.add(map);
			}
			SimpleAdapter sAdap;
			sAdap = new SimpleAdapter(this, MyArrList, R.layout.activity_coloumn_ag,
	                new String[] {"email", "danger", "created_at"}, new int[] {R.id.ColMemberID, R.id.ColName, R.id.ColTel});      
			listView1.setAdapter(sAdap);
			final AlertDialog.Builder viewdetail = new AlertDialog.Builder(this);
			
			
			listView1.setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> myAdapter, View view, int position, long mylng) {
					final String email = MyArrList.get(position).get("email").toString();
					String danger = MyArrList.get(position).get("danger").toString();
					final String latitude = MyArrList.get(position).get("latitude").toString();
					final String longitude = MyArrList.get(position).get("longitude").toString();
					String adresse = MyArrList.get(position).get("adresse").toString();
					String created_at = MyArrList.get(position).get("created_at").toString();
					
					
					viewdetail.setIcon(android.R.drawable.btn_star_big_on);
					viewdetail.setTitle("Détails demande d'urgence");
					viewdetail.setMessage("email :" + email + "\n" + "danger :" + danger +"\n" + "latitude: " 
					+ latitude +"\n" + "longitude: " + longitude + "\n" + "adresse: " + adresse + "\n" + "created_at: " + created_at
					 );
					viewdetail.setPositiveButton("position sur map", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//
							Intent i=new Intent(GetRequest.this, MapAgent.class);
							i.putExtra("latitude", latitude);
							i.putExtra("longitude", longitude);
							startActivity(i);
						}
					});	
					viewdetail.setNeutralButton("Fiche sante client", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//
							Intent i=new Intent(GetRequest.this, FicheSante.class);
							i.putExtra("email", email);
							startActivity(i);
						}	
					});
					viewdetail.setNegativeButton("annuler", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					viewdetail.show();
				}
				
			});
			
			final AlertDialog.Builder takemission = new AlertDialog.Builder(this);
	         listView1.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> myAdapter, View view, int position, long mylng) {
					
					final String email = MyArrList.get(position).get("email").toString();
					String danger = MyArrList.get(position).get("danger").toString();
					
					takemission.setIcon(android.R.drawable.btn_star_big_on);
					takemission.setTitle("Accepter mission ?");
					takemission.setMessage("email :" + email + "\n" + "danger :" + danger );
					takemission.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//
							acepter_mission(email, username);
						Toast.makeText(getApplicationContext(), "mission declaree", Toast.LENGTH_SHORT).show();
						}
					});	
					
					takemission.setNegativeButton("Non", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							
						}
					});
					takemission.show();
					return true;
				}
			});
			
		}catch(JSONException e){
			e.printStackTrace();
		}		
	  
	  
  }
			

//connect to server
		public String  getJSONUrl(String url) {
			
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("agent",job));
			StringBuilder strBuilder = new StringBuilder();
			try{
			
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
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
				}
				
			}catch(ClientProtocolException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
			return strBuilder.toString();
		}
			

		
		public void acepter_mission(String email,String nom_agent){
			InputStream is = null;
			
				
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("email",email));
				nameValuePairs.add(new BasicNameValuePair("nom",nom_agent));
				

       		try{
       			HttpClient httpclient = new DefaultHttpClient();
       			HttpPost httpPost = new HttpPost("http://192.168.100.1/update_declaration.php");
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
		
			
		

	
	

	


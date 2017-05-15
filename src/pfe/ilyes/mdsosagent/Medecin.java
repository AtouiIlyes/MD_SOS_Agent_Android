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
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Medecin extends Activity{
	Button refresh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_urgence_request);
	
	affiche();
	
	
	refresh=(Button) findViewById(R.id.btn1);
	refresh.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			affiche();
			
		}
	});




 

}

	public void affiche(){
		

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		final ListView listView1 = (ListView)findViewById(R.id.listView1);
		String url = "http://192.168.100.1/get_fichesantemedecin.php";
		
		try{
			JSONArray data = new JSONArray(getJSONUrl(url));
			final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
			
			
			for(int i = 0; i < data.length(); i++){
				JSONObject c = data.getJSONObject(i);
		        
				map = new HashMap<String, String>();
				
				map.put("email", c.getString("email"));
				map.put("sname", c.getString("sname"));
				map.put("snassurance", c.getString("snassurance"));
				map.put("sdonorgane", c.getString("sdonorgane"));
				map.put("stypesang", c.getString("stypesang"));
				map.put("stypeassurence", c.getString("stypeassurence"));
				map.put("spathologie1", c.getString("spathologie1"));
				map.put("spathologie2", c.getString("spathologie2"));
				map.put("sallergie1", c.getString("sallergie1"));
				map.put("sallergie2", c.getString("sallergie2"));
				map.put("santfamil1", c.getString("santfamil1"));
				map.put("santfamil2", c.getString("santfamil2"));
				map.put("straitement", c.getString("straitement"));
				map.put("smedecin", c.getString("smedecin"));
				map.put("certification", c.getString("certification"));
				MyArrList.add(map);
			}
			SimpleAdapter sAdap;
			sAdap = new SimpleAdapter(this, MyArrList, R.layout.activity_column,
		            new String[] {"email", "sname", "sdonorgane"}, new int[] {R.id.ColMemberID, R.id.ColName, R.id.ColTel});      
			listView1.setAdapter(sAdap);
			final AlertDialog.Builder viewdetail = new AlertDialog.Builder(this);
			
			listView1.setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> myAdapter, View view, int position, long mylng) {
					final String email = MyArrList.get(position).get("email").toString();
					final String sname = MyArrList.get(position).get("sname").toString();
					final String snassurance = MyArrList.get(position).get("snassurance").toString();
					final String sdonorgane = MyArrList.get(position).get("sdonorgane").toString();
					final String stypesang = MyArrList.get(position).get("stypesang").toString();
					final String stypeassurence = MyArrList.get(position).get("stypeassurence").toString();
					final String spathologie1 = MyArrList.get(position).get("spathologie1").toString();
					final String spathologie2 = MyArrList.get(position).get("spathologie2").toString();
					final String sallergie1 = MyArrList.get(position).get("sallergie1").toString();
					final String sallergie2 = MyArrList.get(position).get("sallergie2").toString();
					final String santfamil1 = MyArrList.get(position).get("santfamil1").toString();
					final String santfamil2 = MyArrList.get(position).get("santfamil2").toString();
					final String straitement = MyArrList.get(position).get("straitement").toString();
					final String smedecin = MyArrList.get(position).get("smedecin").toString();
					final String certification = MyArrList.get(position).get("certification").toString();

					
					
					viewdetail.setIcon(android.R.drawable.btn_star_big_on);
					viewdetail.setTitle("Détails de client");
					viewdetail.setMessage("verfifier la fiche de sante de Mr. :" + sname +"?");
					viewdetail.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//
							Intent i=new Intent(Medecin.this, FicheSanteMedecin.class);
							i.putExtra("email", email);
							i.putExtra("sname", sname);
							i.putExtra("snassurance", snassurance);
							i.putExtra("sdonorgane", sdonorgane);
							i.putExtra("stypesang", stypesang);
							i.putExtra("stypeassurence", stypeassurence);
							i.putExtra("spathologie1", spathologie1);
							i.putExtra("spathologie2", spathologie2);
							i.putExtra("sallergie1", sallergie1);
							i.putExtra("sallergie2", sallergie2);
							i.putExtra("santfamil1", santfamil1);
							i.putExtra("santfamil2", santfamil2);
							i.putExtra("straitement", straitement);
							i.putExtra("smedecin", smedecin);

							

							startActivity(i);
						}
					});	
					viewdetail.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//
							dialog.dismiss();
						}	
					});
					
					viewdetail.show();
				}
				
			});
			
		}catch(JSONException e){
			e.printStackTrace();
		}		
		
	}


//connect to server
public String  getJSONUrl(String url) {

	Intent intent = getIntent();
   String username = intent.getStringExtra("username");
ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
nameValuePairs.add(new BasicNameValuePair("nom",username));
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



}

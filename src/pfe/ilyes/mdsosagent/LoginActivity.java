package pfe.ilyes.mdsosagent;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;




import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends Activity {
		private RadioGroup r;
		private RadioButton rb1;
		private Button btnLogin;
		private EditText inputEmail;
		private EditText inputPassword;
		int selected;
		String job;
		String password;
		String email ;
		private Dialog loadingDialog;
		
		
		public static final String EMAIL = "EMAIL";
		public static final String JOB= "JOB";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		inputEmail = (EditText) findViewById(R.id.email);
		inputPassword = (EditText) findViewById(R.id.password);
	

	    r = (RadioGroup) findViewById(R.id.radioGroup1);
	    btnLogin = (Button) findViewById(R.id.btnLogin);
	    btnLogin.setOnClickListener(new View.OnClickListener() {

	      @Override
		public void onClick(View v) {
	        // get selected radio button from radioGroupCricket
	         selected = r.getCheckedRadioButtonId();
	        rb1 = (RadioButton) findViewById(selected);
	        job=(String) rb1.getText();
	        
	        email = inputEmail.getText().toString();
			password = inputPassword.getText().toString();
	        
	        if (email.trim().length() > 0 && password.trim().length() > 0 && job.trim().length() > 0) {
				// login user
	        	//Toast.makeText(getApplicationContext(),job, Toast.LENGTH_LONG).show();
	        	login(email,password,job);
			} else {
				// Prompt user to enter credentials
				Toast.makeText(getApplicationContext(),
						"Please enter the credentials!", Toast.LENGTH_LONG)
						.show();
			}

	     
	      }
	 });	
	   }
	
public void login(final String email, String password, final String job){
		
		class LoginAsync extends AsyncTask<String, Void, String>{
			 
            private Dialog loadingDialog;
 
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(LoginActivity.this, "Please wait", "Loading...");
            }
 
            @Override
            protected String doInBackground(String... params) {
                String email = params[0];
                String password = params[1];
                String job = params[2];
 
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                nameValuePairs.add(new BasicNameValuePair("job", job));
                String result = null;
 
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://192.168.100.1/loginagent.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 
                    HttpResponse response = httpClient.execute(httpPost);
 
                    HttpEntity entity = response.getEntity();
 
                    is = entity.getContent();
 
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
 
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
 
            @Override
            protected void onPostExecute(String result){
                String s = result.trim();
                int n=Integer.parseInt(s);
                loadingDialog.dismiss();
                if(n==1){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(EMAIL, email);
                    intent.putExtra(JOB, job);
                    finish();
                    startActivity(intent);
                }
                //pompier
                if(n==2){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(EMAIL, email);
                    intent.putExtra(JOB, job);
                    finish();
                    startActivity(intent);
                }
                //ambulancier
                if(n==4){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(EMAIL, email);
                    intent.putExtra(JOB, job);
                    finish();
                    startActivity(intent);
                }
                //medecin
                if(n==6){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(EMAIL, email);
                    intent.putExtra(JOB, job);
                    finish();
                    startActivity(intent);
                }
                
            }
        }
 
        LoginAsync la = new LoginAsync();
        la.execute(email, password,job);
 
    }
	

}

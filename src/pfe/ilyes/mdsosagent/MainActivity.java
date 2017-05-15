package pfe.ilyes.mdsosagent;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ImageView img1;
	Button btn1;
	Button btn2;
	String username;
	String job;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

        Intent intent = getIntent();
         username = intent.getStringExtra(LoginActivity.EMAIL);
         job = intent.getStringExtra(LoginActivity.JOB);
        TextView textView = (TextView) findViewById(R.id.tv1);
 
        textView.setText(username +"\n"+job);
        
        img1=(ImageView) findViewById(R.id.img1);
        
       if(job.equals("policier")){
        	img1.setImageDrawable(getResources().getDrawable(R.drawable.poli));
       }
       if(job.equals("medecin")){
       	img1.setImageDrawable(getResources().getDrawable(R.drawable.medecin));
      }
       if(job.equals("pompier")){
          	img1.setImageDrawable(getResources().getDrawable(R.drawable.medecin));
         }
       if(job.equals("ambulancier")){
         	img1.setImageDrawable(getResources().getDrawable(R.drawable.medecin));
        }
       
       
       btn1=(Button) findViewById(R.id.btn1);
       btn1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			if(job.equals("medecin")){
				
				Intent i1= new Intent(MainActivity.this, Medecin.class);
	            i1.putExtra("username", username);
	            startActivity(i1);
			}
			else{
			Intent i1= new Intent(MainActivity.this, GetRequest.class);
			i1.putExtra("job", job);
			i1.putExtra("username", username);
            startActivity(i1);
			}
			
		}
	});
       
       btn2=(Button) findViewById(R.id.btn2);
       btn2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Intent i1= new Intent(MainActivity.this, LoginActivity.class);
			startActivity(i1);
			
		}
	});
       
       
	}
	}

	


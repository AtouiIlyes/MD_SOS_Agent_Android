package pfe.ilyes.mdsosagent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FicheSanteMedecin extends Activity {

	ImageButton b;
	ImageButton edit;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fichesante_medecin);
		
		Intent i = getIntent();
		final String email =i.getStringExtra("email");
		final String sname =i.getStringExtra("sname");
		final String snassurance =i.getStringExtra("snassurance");
		final String sdonorgane =i.getStringExtra("sdonorgane");
		final String stypesang =i.getStringExtra("stypesang");
		final String stypeassurence =i.getStringExtra("stypeassurence");
		final String spathologie1 =i.getStringExtra("spathologie1");
		final String spathologie2 =i.getStringExtra("spathologie2");
		final String sallergie1 =i.getStringExtra("sallergie1");
		final String sallergie2 =i.getStringExtra("sallergie2");
		final String santfamil1 =i.getStringExtra("santfamil1");
		final String santfamil2 =i.getStringExtra("santfamil2");
		final String straitement =i.getStringExtra("straitement");
		final String smedecin =i.getStringExtra("smedecin");
		
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
			 
			 b=(ImageButton) findViewById(R.id.btn1);
		        b.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent i=new Intent(FicheSanteMedecin.this, Medecin.class);
						startActivity(i);
						
					}
				});
		        
		        edit=(ImageButton) findViewById(R.id.edit);
		        
		        edit.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent i=new Intent(FicheSanteMedecin.this, Edit_fichesante.class);
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
						startActivity(i);
						
					}
				});
			 
			
}
	
}

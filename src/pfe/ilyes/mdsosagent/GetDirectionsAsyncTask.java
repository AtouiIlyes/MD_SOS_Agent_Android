package pfe.ilyes.mdsosagent;

import java.util.ArrayList;
import java.util.Map;
import org.w3c.dom.Document;
import com.google.android.gms.maps.model.LatLng;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class GetDirectionsAsyncTask extends AsyncTask<Map<String, String>, Object, ArrayList<LatLng>>
{
    public static final String USER_CURRENT_LAT = "user_current_lat";
    public static final String USER_CURRENT_LONG = "user_current_long";
    public static final String DESTINATION_LAT = "destination_lat";
    public static final String DESTINATION_LONG = "destination_long";
    public static final String DIRECTIONS_MODE = "directions_mode";
    private MapAgent activity;
    private Exception exception;
    private ProgressDialog progressDialog;
 
    public GetDirectionsAsyncTask(MapAgent activity)
    {
        super();
        this.activity = activity;
    }
 
    @Override
	public void onPreExecute()
    {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Calculating directions");
        progressDialog.show();
    }
 
    @Override
    public void onPostExecute(ArrayList result)
    {
        progressDialog.dismiss();
        if (exception == null)
        {
            activity.handleGetDirectionsResult(result);
        }
        else
        {
            processException();
        }
    }
 
    @Override
    protected ArrayList<LatLng> doInBackground(Map<String, String>... params)
    {
        Map<String, String> paramMap = params[0];
        try
        {
            LatLng fromPosition = new LatLng(Double.valueOf(paramMap.get(USER_CURRENT_LAT)) , Double.valueOf(paramMap.get(USER_CURRENT_LONG)));
            LatLng toPosition = new LatLng(Double.valueOf(paramMap.get(DESTINATION_LAT)) , Double.valueOf(paramMap.get(DESTINATION_LONG)));
            GMapV2Direction md = new GMapV2Direction();
            Document doc = md.getDocument(fromPosition, toPosition, paramMap.get(DIRECTIONS_MODE));
            ArrayList<LatLng> directionPoints = md.getDirection(doc);
            return directionPoints;
        }
        catch (Exception e)
        {
            exception = e;
            return null;
        }
    }
 
    private void processException()
    {
        Toast.makeText(activity, "Error retriving data", 3000).show();
    }
}
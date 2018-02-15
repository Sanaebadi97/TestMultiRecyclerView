package apiService;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.AppController;
import models.DiscoverFirst;
import models.DiscoverSecond;

/**
 * Created by sanaebadi on 2/9/18.
 */

public class SecondDiscoverApi {

  private static final String TAG = "SecondDiscoverApi";
  private Context context;
  private String url = "http://sanaebadi.ir/MainDemo/get-discover-second-demo-data.php";

  public SecondDiscoverApi(Context context) {
    this.context = context;

  }

  public void getSecondDiscoverData(final onGetData onGetData) {

    JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

          Log.i(TAG, "onResponseSecondDiscover: " + response.toString());

          List<DiscoverSecond> discoverSecondList = new ArrayList<>();
          for (int i = 0; i < response.length(); i++) {

            DiscoverSecond discoverSecond = new DiscoverSecond();

            try {
              JSONObject object = response.getJSONObject(i);

              discoverSecond.setId(object.getInt("id"));
              discoverSecond.setImageUrl(object.getString("image_url"));

              discoverSecondList.add(discoverSecond);


            } catch (JSONException e) {
              e.printStackTrace();
            }
          }

          onGetData.onGet(discoverSecondList);

        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

        Log.e(TAG, "onErrorResponseSecondDiscover: " + error.getMessage());
        onGetData.onGet(null);
      }
    });

    request.setRetryPolicy(new DefaultRetryPolicy(8000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
      DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    AppController.getmIstance().getmRequestQueue().add(request);
  }



  public interface onGetData {
    void onGet(List<DiscoverSecond> discoverSecondList);
  }
}

package apiService;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

/**
 * Created by sanaebadi on 2/9/18.
 */

public class FirstDiscoverApi {

  private static final String TAG = "FirstDiscoverApi";
  private Context context;
  private String url = "http://sanaebadi.ir/MainDemo/get-discover-first-demo-data.php";

  public FirstDiscoverApi(Context context) {
    this.context = context;

  }

  public void getFirstDiscoverData(final onGetData onGetData) {

    JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

          Log.i(TAG, "onResponseFirstDiscover: " + response.toString());

          List<DiscoverFirst> discoverFirstList = new ArrayList<>();
          for (int i = 0; i < response.length(); i++) {

            DiscoverFirst discoverFirst = new DiscoverFirst();

            try {
              JSONObject object = response.getJSONObject(i);

              discoverFirst.setId(object.getInt("id"));
              discoverFirst.setTitle(object.getString("title"));
              discoverFirst.setImageUrl(object.getString("image_url"));

              discoverFirstList.add(discoverFirst);


            } catch (JSONException e) {
              e.printStackTrace();
            }
          }

          onGetData.onGet(discoverFirstList);

        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

        Log.e(TAG, "onErrorResponseFirstDiscover: " + error.getMessage());
        onGetData.onGet(null);
      }
    });

    request.setRetryPolicy(new DefaultRetryPolicy(8000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
      DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    AppController.getmIstance().getmRequestQueue().add(request);
  }



  public interface onGetData {
    void onGet(List<DiscoverFirst> discoverFirstList);
  }
}

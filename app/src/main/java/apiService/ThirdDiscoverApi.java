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
import models.DiscoverThird;

/**
 * Created by sanaebadi on 2/9/18.
 */

public class ThirdDiscoverApi {

  private static final String TAG = "ThirdDiscoverApi";
  private Context context;
  private String url = "http://sanaebadi.ir/MainDemo/get-discover-third-demo-data.php";

  public ThirdDiscoverApi(Context context) {
    this.context = context;

  }

  public void getThirdDiscoverData(final onGetData onGetData) {

    JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

          Log.i(TAG, "onResponseThirdDiscover: " + response.toString());

          List<DiscoverThird> discoverThirdList = new ArrayList<>();
          for (int i = 0; i < response.length(); i++) {

            DiscoverThird discoverThird = new DiscoverThird();

            try {
              JSONObject object = response.getJSONObject(i);

              discoverThird.setId(object.getInt("id"));
              discoverThird.setTitle(object.getString("title"));
              discoverThird.setImageUrl(object.getString("image_url"));

              discoverThirdList.add(discoverThird);


            } catch (JSONException e) {
              e.printStackTrace();
            }
          }

          onGetData.onGet(discoverThirdList);

        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

        Log.e(TAG, "onErrorResponseThirdDiscover: " + error.getMessage());
        onGetData.onGet(null);
      }
    });

    request.setRetryPolicy(new DefaultRetryPolicy(8000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
      DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    AppController.getmIstance().getmRequestQueue().add(request);
  }



  public interface onGetData {
    void onGet(List<DiscoverThird> discoverThirdList);
  }
}

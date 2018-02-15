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
import models.FeaturedVertical;

/**
 * Created by sanaebadi on 2/7/18.
 */

public class VerticalFeaturedApi {
  private static final String TAG = "VerticalFeaturedApi";
  private Context context;
  private String url = "http://sanaebadi.ir/MainDemo/get-featured-second-demo-data.php";

  public VerticalFeaturedApi(Context context) {
    this.context = context;
  }

  public void getVerticalFeaturedData(final onGetData onGetData) {

    final JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

          Log.i(TAG, "onResponseVerticalFeatured: " + response.toString());

          List<FeaturedVertical> featuredVerticals = new ArrayList<>();
          for (int i = 0; i < response.length(); i++) {

            FeaturedVertical featuredVertical = new FeaturedVertical();

            try {
              JSONObject object = response.getJSONObject(i);

              featuredVertical.setId(object.getInt("id"));
              featuredVertical.setImageUrl(object.getString("image_url"));

             featuredVerticals.add(featuredVertical);

            } catch (JSONException e) {
              e.printStackTrace();
            }
          }

          onGetData.onGet(featuredVerticals);
        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {


        Log.e(TAG, "onErrorResponseVerticalFeatured: " + error.getMessage());
        onGetData.onGet(null);
      }
    });

    request.setRetryPolicy(new DefaultRetryPolicy(8000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
      DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    AppController.getmIstance().getmRequestQueue().add(request);
  }

  public interface onGetData {
    void onGet(List<FeaturedVertical> featuredVerticals);
  }
}

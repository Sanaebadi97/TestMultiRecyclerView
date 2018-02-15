package apiService;

import android.content.Context;
import android.util.Log;
import android.view.View;

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
import fragments.FragmentFeatured;
import models.FeaturedHorizontal;

/**
 * Created by sanaebadi on 2/7/18.
 */

public class HorizontalFeaturedApi {
  private static final String TAG = "HorizontalFeaturedApi";
  private Context context;
  private String url = "http://sanaebadi.ir/MainDemo/get-featured-first-demo-data.php";

  public HorizontalFeaturedApi(Context context) {
    this.context = context;
  }

  public void getHorizontalFeaturedData(final onGetData onGetData) {

    final JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {


          Log.i(TAG, "onResponseHorizontalFeatured: " + response.toString());

          List<FeaturedHorizontal> featuredHorizontals = new ArrayList<>();
          for (int i = 0; i < response.length(); i++) {
            FeaturedHorizontal featuredHorizontal = new FeaturedHorizontal();

            try {
              JSONObject object = response.getJSONObject(i);

              featuredHorizontal.setId(object.getInt("id"));
              featuredHorizontal.setImageUrl(object.getString("image_url"));
              featuredHorizontal.setTitle(object.getString("title"));

              featuredHorizontals.add(featuredHorizontal);

            } catch (JSONException e) {
              e.printStackTrace();
            }
          }

          onGetData.onGet(featuredHorizontals);
        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {


        Log.e(TAG, "onErrorResponseHorizontalFeatured: " + error.getMessage());
        onGetData.onGet(null);
      }
    });

    request.setRetryPolicy(new DefaultRetryPolicy(8000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
      DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    AppController.getmIstance().getmRequestQueue().add(request);
  }

  public interface onGetData {
    void onGet(List<FeaturedHorizontal> featuredHorizontals);
  }
}

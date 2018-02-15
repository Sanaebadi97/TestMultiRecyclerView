package app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.sanaebadi.myapplication.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by sanaebadi on 2/7/18.
 */

public class AppController extends Application {
  private static final String TAG = "AppController";
  private RequestQueue mRequestQueue;
  private static AppController mIstance;

  public AppController() {

  }

  @Override
  public void onCreate() {
    super.onCreate();
    mIstance = this;
    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
      .setDefaultFontPath("arlrdbd.ttf")
      .setFontAttrId(R.attr.fontPath)
      .build());
  }

  public static synchronized AppController getmIstance() {
    return mIstance;
  }

  public RequestQueue getmRequestQueue() {
    if (mRequestQueue == null) {
      mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }
    return mRequestQueue;
  }

  public <T> void addToRequestQueu(Request<T> request, String tag) {
    request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
    getmRequestQueue().add(request);
  }

  public <T> void addToRequestQueu(Request<T> request) {
    request.setTag(TAG);
    getmRequestQueue().add(request);
  }

  public void cancelPendingRequest(Object tag) {
    if (mRequestQueue != null) {
      mRequestQueue.cancelAll(tag);
    }
  }
}

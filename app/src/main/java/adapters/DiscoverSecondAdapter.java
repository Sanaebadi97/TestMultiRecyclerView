package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanaebadi.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import models.DiscoverFirst;
import models.DiscoverSecond;

/**
 * Created by sanaebadi on 2/9/18.
 */

public class DiscoverSecondAdapter extends RecyclerView.Adapter<DiscoverSecondAdapter.DiscoverSecondViewHolder> {

  private static final String TAG = "DiscoverSecondAdapter";
  private Context context;
  private List<DiscoverSecond> discoverSecondList;

  public DiscoverSecondAdapter(Context context, List<DiscoverSecond> discoverSecondList) {
    this.context = context;
    this.discoverSecondList = discoverSecondList;
  }

  @Override
  public DiscoverSecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.discover_second_single_item, parent, false);

    return new DiscoverSecondViewHolder(view);
  }

  @Override
  public void onBindViewHolder(DiscoverSecondViewHolder holder, int position) {
    DiscoverSecond discoverSecond = discoverSecondList.get(position);

    Picasso.with(context).load(discoverSecond.getImageUrl())
      .into(holder.img_second_discover);
  }

  @Override
  public int getItemCount() {
    return discoverSecondList.size();
  }

  class DiscoverSecondViewHolder extends RecyclerView.ViewHolder {

    private ImageView img_second_discover;

    public DiscoverSecondViewHolder(View itemView) {
      super(itemView);
      img_second_discover = itemView.findViewById(R.id.img_second_discover);

    }
  }
}

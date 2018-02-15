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

import models.DiscoverSecond;
import models.DiscoverThird;

/**
 * Created by sanaebadi on 2/9/18.
 */

public class DiscoverThirdAdapter extends RecyclerView.Adapter<DiscoverThirdAdapter.DiscoverThirdViewHolder> {

  private static final String TAG = "DiscoverThirdAdapter";
  private Context context;
  private List<DiscoverThird> discoverThirdList;

  public DiscoverThirdAdapter(Context context, List<DiscoverThird> discoverThirdList) {
    this.context = context;
    this.discoverThirdList = discoverThirdList;
  }

  @Override
  public DiscoverThirdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.discover_third_single_item, parent, false);

    return new DiscoverThirdViewHolder(view);
  }

  @Override
  public void onBindViewHolder(DiscoverThirdViewHolder holder, int position) {
    DiscoverThird discoverThird = discoverThirdList.get(position);

    holder.txt_third_discover.setText(discoverThird.getTitle());
    Picasso.with(context).load(discoverThird.getImageUrl())
      .into(holder.img_third_discover);
  }

  @Override
  public int getItemCount() {
    return discoverThirdList.size();
  }

  class DiscoverThirdViewHolder extends RecyclerView.ViewHolder {

    private ImageView img_third_discover;
    private TextView txt_third_discover;

    public DiscoverThirdViewHolder(View itemView) {
      super(itemView);
      img_third_discover = itemView.findViewById(R.id.img_third_discover);
      txt_third_discover = itemView.findViewById(R.id.txt_third_discover);
    }
  }
}

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

/**
 * Created by sanaebadi on 2/9/18.
 */

public class DiscoverFirstAdapter extends RecyclerView.Adapter<DiscoverFirstAdapter.DiscoverFirstViewHolder> {

  private static final String TAG = "DiscoverFirstAdapter";
  private Context context;
  private List<DiscoverFirst> discoverFirstList;

  public DiscoverFirstAdapter(Context context, List<DiscoverFirst> discoverFirstList) {
    this.context = context;
    this.discoverFirstList = discoverFirstList;
  }

  @Override
  public DiscoverFirstViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.discover_first_single_item, parent, false);

    return new DiscoverFirstViewHolder(view);
  }

  @Override
  public void onBindViewHolder(DiscoverFirstViewHolder holder, int position) {
    DiscoverFirst discoverFirst = discoverFirstList.get(position);

    holder.txt_first_discover.setText(discoverFirst.getTitle());

    Picasso.with(context).load(discoverFirst.getImageUrl())
      .into(holder.img_first_discover);
  }

  @Override
  public int getItemCount() {
    return discoverFirstList.size();
  }

  class DiscoverFirstViewHolder extends RecyclerView.ViewHolder {

    private ImageView img_first_discover;
    private TextView txt_first_discover;


    public DiscoverFirstViewHolder(View itemView) {
      super(itemView);

      img_first_discover = itemView.findViewById(R.id.img_first_discover);
      txt_first_discover = itemView.findViewById(R.id.txt_first_discover);

    }
  }
}

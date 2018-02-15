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

import models.FeaturedHorizontal;

/**
 * Created by sanaebadi on 2/7/18.
 */

public class FeaturedHorizontalAdapter extends RecyclerView.Adapter<FeaturedHorizontalAdapter.FeaturedHorizontalViewHolder> {

  private static final String TAG = "FeaturedHorizontalAdapt";
  private Context context;
  private List<FeaturedHorizontal> horizontalList;

  public FeaturedHorizontalAdapter(Context context, List<FeaturedHorizontal> horizontalList) {
    this.context = context;
    this.horizontalList = horizontalList;
  }

  @Override
  public FeaturedHorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(context).inflate(R.layout.featured_horizontal_single_item, parent, false);
    return new FeaturedHorizontalViewHolder(view);
  }

  @Override
  public void onBindViewHolder(FeaturedHorizontalViewHolder holder, int position) {
    FeaturedHorizontal featuredHorizontal = horizontalList.get(position);
    holder.txt_horizontal_featured.setText(featuredHorizontal.getTitle());

    Picasso.with(context).load(featuredHorizontal.getImageUrl())
      .into(holder.img_horizontal_featured);

  }

  @Override
  public int getItemCount() {
    return horizontalList.size();
  }

  class FeaturedHorizontalViewHolder extends RecyclerView.ViewHolder {

    private ImageView img_horizontal_featured;
    private TextView txt_horizontal_featured;

    public FeaturedHorizontalViewHolder(View itemView) {
      super(itemView);

      img_horizontal_featured = itemView.findViewById(R.id.img_horizontal_featured);
      txt_horizontal_featured = itemView.findViewById(R.id.txt_horizontal_featured);

    }
  }
}

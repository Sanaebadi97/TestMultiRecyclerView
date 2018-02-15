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
import models.FeaturedVertical;

/**
 * Created by sanaebadi on 2/7/18.
 */

public class FeaturedVerticalAdapter extends RecyclerView.Adapter<FeaturedVerticalAdapter.FeaturedHorizontalViewHolder> {

  private static final String TAG = "FeaturedHorizontalAdapt";
  private Context context;
  private List<FeaturedVertical> verticalList;

  public FeaturedVerticalAdapter(Context context, List<FeaturedVertical> verticalList) {
    this.context = context;
    this.verticalList = verticalList;
  }

  @Override
  public FeaturedHorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(context).inflate(R.layout.featured_vertical_single_item, parent, false);
    return new FeaturedHorizontalViewHolder(view);
  }

  @Override
  public void onBindViewHolder(FeaturedHorizontalViewHolder holder, int position) {
    FeaturedVertical featuredVertical = verticalList.get(position);

    Picasso.with(context).load(featuredVertical.getImageUrl())
      .into(holder.img_vertical_featured);

  }

  @Override
  public int getItemCount() {
    return verticalList.size();
  }

  class FeaturedHorizontalViewHolder extends RecyclerView.ViewHolder {

    private ImageView img_vertical_featured;

    public FeaturedHorizontalViewHolder(View itemView) {
      super(itemView);

      img_vertical_featured = itemView.findViewById(R.id.img_vertical_featured);

    }
  }
}

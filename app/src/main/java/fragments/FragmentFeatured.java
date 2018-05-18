package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanaebadi.myapplication.R;

import java.util.List;

import adapters.FeaturedHorizontalAdapter;
import adapters.FeaturedVerticalAdapter;
import apiService.HorizontalFeaturedApi;
import apiService.VerticalFeaturedApi;
import models.FeaturedHorizontal;
import models.FeaturedVertical;


/**
 * Created by sanaebadi on 2/4/18.
 */

public class FragmentFeatured extends Fragment {

  private RecyclerView featured_horizontal_list, featured_vertical_list;
  private HorizontalFeaturedApi horizontalFeaturedApi;
  private FeaturedHorizontalAdapter featuredHorizontalAdapter;
  private VerticalFeaturedApi verticalFeaturedApi;
  private FeaturedVerticalAdapter featuredVerticalAdapter;

  public FragmentFeatured() {

  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_featured, container, false);


    featured_horizontal_list = view.findViewById(R.id.featured_horizontal_list);
    featured_vertical_list = view.findViewById(R.id.featured_vertical_list);


    horizontalFeaturedApi = new HorizontalFeaturedApi(this.getActivity());
    horizontalFeaturedApi.getHorizontalFeaturedData(new HorizontalFeaturedApi.onGetData() {
      @Override
      public void onGet(List<FeaturedHorizontal> featuredHorizontals) {

        featuredHorizontalAdapter = new FeaturedHorizontalAdapter(getActivity(), featuredHorizontals);
        featured_horizontal_list.setHasFixedSize(true);
        featured_horizontal_list.setLayoutManager(new LinearLayoutManager(getActivity(),
          LinearLayoutManager.HORIZONTAL, false));
        featured_horizontal_list.setNestedScrollingEnabled(false);
        featured_horizontal_list.setAdapter(featuredHorizontalAdapter);

      }
    });

    verticalFeaturedApi = new VerticalFeaturedApi(this.getActivity());
    verticalFeaturedApi.getVerticalFeaturedData(new VerticalFeaturedApi.onGetData() {
      @Override
      public void onGet(List<FeaturedVertical> featuredVerticals) {

        featuredVerticalAdapter = new FeaturedVerticalAdapter(getActivity(), featuredVerticals);
        featured_vertical_list.setHasFixedSize(true);
        featured_vertical_list.setLayoutManager(new GridLayoutManager(getActivity(),
          3, LinearLayoutManager.VERTICAL, false));
        featuredVerticalAdapter.setNestedScrollingEnabled(false);
        featured_vertical_list.setAdapter(featuredVerticalAdapter);

      }
    });

    return view;
  }
}

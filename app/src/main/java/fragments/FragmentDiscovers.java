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

import adapters.DiscoverFirstAdapter;
import adapters.DiscoverSecondAdapter;
import adapters.DiscoverThirdAdapter;
import apiService.FirstDiscoverApi;
import apiService.SecondDiscoverApi;
import apiService.ThirdDiscoverApi;
import models.DiscoverFirst;
import models.DiscoverSecond;
import models.DiscoverThird;


/**
 * Created by sanaebadi on 2/4/18.
 */

public class FragmentDiscovers extends Fragment {

  private RecyclerView discover_first_list, discover_second_list, discover_third_list;
  private FirstDiscoverApi firstDiscoverApi;
  private DiscoverFirstAdapter discoverFirstAdapter;
  private SecondDiscoverApi secondDiscoverApi;
  private DiscoverSecondAdapter discoverSecondAdapter;
  private ThirdDiscoverApi thirdDiscoverApi;
  private DiscoverThirdAdapter discoverThirdAdapter;


  public FragmentDiscovers() {

  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_discover, container, false);

    discover_first_list = view.findViewById(R.id.discover_first_list);
    discover_second_list = view.findViewById(R.id.discover_second_list);
    discover_third_list = view.findViewById(R.id.discover_third_list);

    firstDiscoverApi = new FirstDiscoverApi(this.getActivity());
    firstDiscoverApi.getFirstDiscoverData(new FirstDiscoverApi.onGetData() {
      @Override
      public void onGet(List<DiscoverFirst> discoverFirstList) {

        discoverFirstAdapter = new DiscoverFirstAdapter(getActivity(), discoverFirstList);
        discover_first_list.setHasFixedSize(true);
        discover_first_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,
          false));
        discover_first_list.setNestedScrollingEnabled(false);
        discover_first_list.setAdapter(discoverFirstAdapter);
      }
    });

    secondDiscoverApi = new SecondDiscoverApi(this.getActivity());
    secondDiscoverApi.getSecondDiscoverData(new SecondDiscoverApi.onGetData() {
      @Override
      public void onGet(List<DiscoverSecond> discoverSecondList) {

        discoverSecondAdapter = new DiscoverSecondAdapter(getActivity(), discoverSecondList);
        discover_second_list.setHasFixedSize(true);
        discover_second_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,
          false));
         discover_second_list.setNestedScrollingEnabled(false);
        discover_second_list.setAdapter(discoverSecondAdapter);

      }
    });

    thirdDiscoverApi = new ThirdDiscoverApi(this.getActivity());
    thirdDiscoverApi.getThirdDiscoverData(new ThirdDiscoverApi.onGetData() {
      @Override
      public void onGet(List<DiscoverThird> discoverThirdList) {

        discoverThirdAdapter = new DiscoverThirdAdapter(getActivity(), discoverThirdList);
        discover_third_list.setHasFixedSize(true);
        discover_third_list.setLayoutManager(new GridLayoutManager(getActivity(), 2,
          LinearLayoutManager.VERTICAL, false));
        discover_third_list.setAdapter(discoverThirdAdapter);

      }
    });

    return view;
  }

}

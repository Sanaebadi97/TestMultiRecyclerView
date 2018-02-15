package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanaebadi on 2/6/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

  private List<Fragment> fragmentList = new ArrayList<>();
  private List<String> titleList = new ArrayList<>();

  public ViewPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int position) {
    return fragmentList.get(position);
  }

  @Override
  public int getCount() {
    return fragmentList.size();
  }

  public void addFragment(Fragment fragment, String title) {
    fragmentList.add(fragment);
    titleList.add(title);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return titleList.get(position);
  }
}

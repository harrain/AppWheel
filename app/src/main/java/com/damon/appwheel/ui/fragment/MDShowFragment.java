package com.damon.appwheel.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.damon.appwheel.R;
import com.damon.appwheel.ui.base.BaseSimpleFragment;
import com.damon.appwheel.ui.util.UIConvertUtils;

import java.lang.reflect.Field;

/**
 * Use the {@link MDShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MDShowFragment extends BaseSimpleFragment<String> {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private OneFragment zero;
    private OneFragment one;
    private OneFragment two;
    private String tag = "MDShowFragment";
    private int[] iconNormalRes = {R.drawable.weixin_normal,R.drawable.find_normal,R.drawable.contact_list_normal};
    private int[] iconPressedRes = {R.drawable.weixin_pressed,R.drawable.find_pressed,R.drawable.contact_list_pressed};
    private String[] tabTitles = {"a","b","c"};

    public MDShowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MDShowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MDShowFragment newInstance(String param1, String param2) {
        MDShowFragment fragment = new MDShowFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_mdshow, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        zero = OneFragment.create(0);
        one = OneFragment.create(1);
        two = OneFragment.create(2);
        viewPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));
//        tabLayout.addTab(tabLayout.newTab());
//        tabLayout.addTab(tabLayout.newTab());
//        tabLayout.addTab(tabLayout.newTab());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        /** 5个tab情况下，tabmode为fixed和scrollable是同样的fix效果；5个以下tab用fixed的模式，5个tab以上用scrollable的模式 */
        tabLayout.getTabAt(0).setCustomView(getTabView(0));
        tabLayout.getTabAt(1).setCustomView(getTabView(1));
        tabLayout.getTabAt(2).setCustomView(getTabView(2));
        setIndicator(tabLayout,10,10);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabStatus(tab,true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabStatus(tab,false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    private View getTabView(int position){
        View view = View.inflate(context,R.layout.tab_layout_item,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        TextView textView = (TextView) view.findViewById(R.id.tab_title);
        imageView.setImageResource(iconNormalRes[position]);
        textView.setText(tabTitles[position]);
        if (position == 0){
            imageView.setImageResource(iconPressedRes[0]);
            textView.setTextColor(getResources().getColor(R.color.wechat_green));
        }
        return view;
    }

    private void changeTabStatus(TabLayout.Tab tab, boolean isSelected){
        View view = tab.getCustomView();
        TextView textView = (TextView) view.findViewById(R.id.tab_title);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageResource(isSelected ? iconPressedRes[tab.getPosition()] : iconNormalRes[tab.getPosition()]);
        textView.setTextColor(isSelected ? getResources().getColor(R.color.wechat_green) : getResources().getColor(R.color.black6));
    }

    public  void setIndicator(TabLayout tabLayout, int leftDip, int rightDip) {
        Class<?> tabLayoutClass = tabLayout.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayoutClass.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabLayout);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = UIConvertUtils.dp2px(leftDip);
        int right = UIConvertUtils.dp2px(rightDip);

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            if (position == 0) return zero;
            else if (position == 1) return one;
            else if (position == 2) return two;
            return new OneFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            Drawable drawable = null;
//            String title = null;
//            switch (position) {
//                case 0:
//                    drawable = ContextCompat.getDrawable(context, R.drawable.weixin_pressed);
//                    title = "a";
//                    break;
//                case 1:
//                    drawable = ContextCompat.getDrawable(context, R.drawable.find_normal);
//                    title = "b";
//                    break;
//                case 2:
//                    drawable = ContextCompat.getDrawable(context, R.drawable.contact_list_normal);
//                    title = "c";
//                    break;
//                default:
//                    break;
//            }
//            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//
//            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
//            SpannableString spannableString = new SpannableString("   " + title);
//            spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//            return spannableString;
//
//        }
    }

    public static class OneFragment extends Fragment {

        public static OneFragment create(int position) {
            OneFragment oneFragment = new OneFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            oneFragment.setArguments(bundle);
            return oneFragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            if (getArguments() != null)textView.setText(getArguments().getInt("position") + "");
            else textView.setText("kong de ");
            return textView;
        }
    }
}

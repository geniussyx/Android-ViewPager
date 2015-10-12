package com.sunsr.android.calculate;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

   private static int[] pics =  {R.drawable.guide1,R.drawable.guide2,R.drawable.guide3,R.drawable.guide4,R.drawable.guide5};

    final List<View> viewList= new ArrayList<View>();
    final List<ImageView> pointList= new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int pic:pics){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(pic);
            viewList.add(imageView);
        }

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(viewList);
        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(myViewPagerAdapter);

        LinearLayout tabLay = (LinearLayout)findViewById(R.id.tabLay);
        for(int i=0;i<tabLay.getChildCount();i++){
            ImageView imageView = (ImageView)tabLay.getChildAt(i);
            pointList.add(imageView);
            imageView.setSelected(false);
            imageView.setTag(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem((Integer)v.getTag());
                }
            });
        }

        tabLay.getChildAt(0).setSelected(true);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                for(int j=0;j<pointList.size();j++){
                    if(j==i){
                        pointList.get(j).setSelected(true);
                    }else{
                        pointList.get(j).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

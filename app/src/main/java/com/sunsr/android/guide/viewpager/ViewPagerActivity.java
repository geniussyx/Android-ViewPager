package com.sunsr.android.guide.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sunsr.android.guide.R;
import com.sunsr.android.guide.GuideViewDoor;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerActivity extends Activity {

   private static int[] pics =  {R.drawable.guide1,R.drawable.guide2,R.drawable.guide3,R.drawable.guide4,R.drawable.guide5};

    final List<View> viewList= new ArrayList<View>();
    final List<ImageView> pointList= new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        for(int pic:pics){
            if(pic == R.drawable.guide5){
                continue;
            }
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(pic);
            viewList.add(imageView);
        }
        LayoutInflater inf = LayoutInflater.from(this);
        View guide6View = inf.inflate(R.layout.startlayout,null);
        viewList.add(guide6View);

        Button startButton = (Button)guide6View.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ViewPagerActivity.this,GuideViewDoor.class);
                startActivity(intent);
                ViewPagerActivity.this.finish();
            }
        });

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

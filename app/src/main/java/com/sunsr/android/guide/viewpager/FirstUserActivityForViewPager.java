package com.sunsr.android.guide.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.sunsr.android.guide.GuideViewDoor;
import com.sunsr.android.guide.imageanim.ImageViewActivity;

/**
 * 判断是否第一次打开
 * Created by Administrator on 2015-10-15.
 */
public class FirstUserActivityForViewPager extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences =  getSharedPreferences("isFirstUseFor",MODE_WORLD_READABLE);
        boolean isFirstUse = preferences.getBoolean("isFirstUseForViewPager", true);
        if(isFirstUse == true){
            startActivity(new Intent(FirstUserActivityForViewPager.this,ImageViewActivity.class));
        }else{
            startActivity(new Intent(FirstUserActivityForViewPager.this,GuideViewDoor.class));
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstUseForViewPager",false);
        editor.commit();
        finish();

    }
}

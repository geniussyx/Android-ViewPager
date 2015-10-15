package com.sunsr.android.guide.imageanim;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.sunsr.android.guide.GuideViewDoor;

/**
 * 判断是否第一次打开
 * Created by Administrator on 2015-10-15.
 */
public class FirstUserActivityForImageAnim extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences =  getSharedPreferences("isFirstUseFor",MODE_WORLD_READABLE);
        boolean isFirstUse = preferences.getBoolean("isFirstUseForImageAnim", true);
        if(isFirstUse == true){
            startActivity(new Intent(FirstUserActivityForImageAnim.this,ImageViewActivity.class));
        }else{
            startActivity(new Intent(FirstUserActivityForImageAnim.this,GuideViewDoor.class));
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstUseForImageAnim",false);
        editor.commit();
        finish();

    }
}

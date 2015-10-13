package com.sunsr.android.guide.imageanim;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.sunsr.android.guide.GuideViewDoor;
import com.sunsr.android.guide.R;

/**
 * Created by Administrator on 2015-10-13.
 */
public class ImageViewActivity extends Activity {

    Drawable[] pics;            //所有图片
    Animation[] anims;          //所有动画

    ImageView guideImageView;   //欢迎界面，背景图片
    int currentItem = 0;        //当前播放资源索引

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);

        pics = new Drawable[]{getResources().getDrawable(R.drawable.v5_3_0_guide_pic1),getResources().getDrawable(R.drawable.v5_3_0_guide_pic2),getResources().getDrawable(R.drawable.v5_3_0_guide_pic3)};
        anims = new Animation[]{AnimationUtils.loadAnimation(this,R.anim.fade_in),AnimationUtils.loadAnimation(this,R.anim.fade_in_scale),AnimationUtils.loadAnimation(this,R.anim.fade_out)};

        //设置动画持续时间
        anims[0].setDuration(1500);
        anims[1].setDuration(3000);
        anims[2].setDuration(1500);

        guideImageView = (ImageView)findViewById(R.id.guideImageView);

        class GuideAnimationListener implements Animation.AnimationListener{
            private int index;

            public GuideAnimationListener(int index){
                this.index = index;
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                if (index < (anims.length - 1)) {
                    guideImageView.startAnimation(anims[index + 1]);
                } else {
                    currentItem++;
                    if (currentItem > (pics.length - 1)) {
                        currentItem = 0;
                    }
                    guideImageView.setImageDrawable(pics[currentItem]);
                    guideImageView.startAnimation(anims[0]);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        }

        anims[0].setAnimationListener(new GuideAnimationListener(0));
        anims[1].setAnimationListener(new GuideAnimationListener(1));
        anims[2].setAnimationListener(new GuideAnimationListener(2));

        guideImageView.setImageDrawable(pics[currentItem]);
        guideImageView.startAnimation(anims[currentItem]);

        Button loginButton = (Button)findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ImageViewActivity.this,GuideViewDoor.class);
                startActivity(intent);
            }
        });
    }
}

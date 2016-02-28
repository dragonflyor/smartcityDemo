package com.xiaozhe.smaartcity;

import com.xiaozhe.utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

    private RelativeLayout rl_splash;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
        
        startAnim();
    }

	private void startAnim() {
		//动画集
		AnimationSet animationSet = new AnimationSet(false);
		//旋转
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(1000);
		animationSet.addAnimation(rotateAnimation);
		//缩放
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, 
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(1000);
		animationSet.addAnimation(scaleAnimation);
		
		//渐变
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(1000);
		animationSet.addAnimation(alphaAnimation);
		
		//监听
		animationSet.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				//跳转到下一页
				jumpToNextPage();

				
			}

		});
		//播放动画
		rl_splash.startAnimation(animationSet);
	}
	
	private void jumpToNextPage() {
		Boolean guiderShowed = PrefUtils.getBoolean(SplashActivity.this, PrefUtils.IS_USER_GUIDER_SHOWED, false);
		
		//没有显示过向导页
		if(!guiderShowed){
			// 启动向导页面
			Intent intent = new Intent(SplashActivity.this, GuiderActivity.class);
			startActivity(intent);
		}else{
			//直接到主页
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
		}

		finish();
		
	}
  
}

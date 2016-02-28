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
		//������
		AnimationSet animationSet = new AnimationSet(false);
		//��ת
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(1000);
		animationSet.addAnimation(rotateAnimation);
		//����
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, 
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(1000);
		animationSet.addAnimation(scaleAnimation);
		
		//����
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(1000);
		animationSet.addAnimation(alphaAnimation);
		
		//����
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
				//��ת����һҳ
				jumpToNextPage();

				
			}

		});
		//���Ŷ���
		rl_splash.startAnimation(animationSet);
	}
	
	private void jumpToNextPage() {
		Boolean guiderShowed = PrefUtils.getBoolean(SplashActivity.this, PrefUtils.IS_USER_GUIDER_SHOWED, false);
		
		//û����ʾ����ҳ
		if(!guiderShowed){
			// ������ҳ��
			Intent intent = new Intent(SplashActivity.this, GuiderActivity.class);
			startActivity(intent);
		}else{
			//ֱ�ӵ���ҳ
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
		}

		finish();
		
	}
  
}

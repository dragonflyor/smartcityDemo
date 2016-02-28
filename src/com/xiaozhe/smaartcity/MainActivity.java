package com.xiaozhe.smaartcity;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.xiaozhe.fragment.ContentFragment;
import com.xiaozhe.fragment.LeftMenuFragment;

public class MainActivity extends SlidingFragmentActivity {

	private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
	private static final String FRAGMENT_CONTENT = "fragment_content";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		//添加侧边布局
		setBehindContentView(R.layout.left_menu);
		//设置侧边属性
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏触摸有效
		slidingMenu.setBehindOffset(200);
		
		initFragment();
	}
	
	void initFragment(){
		FragmentManager fragmentManager = getSupportFragmentManager();
		
		FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
		
		beginTransaction.replace(R.id.fl_left_menu,new LeftMenuFragment(),FRAGMENT_LEFT_MENU);
		beginTransaction.replace(R.id.fl_content,new ContentFragment(),FRAGMENT_CONTENT);
		
		beginTransaction.commit();
	}
	
}

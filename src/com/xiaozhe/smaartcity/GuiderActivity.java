package com.xiaozhe.smaartcity;

import java.util.ArrayList;

import com.xiaozhe.utils.PrefUtils;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GuiderActivity extends Activity {
	private ViewPager vp_guider;
	static final int [] mImagesId = new int[]{R.drawable.guide_1,
												R.drawable.guide_2,
												R.drawable.guide_3};
	private ArrayList<ImageView> mViewsList;
	private LinearLayout ll_point_group; 
	//导航页圆点之间的距离
	private int mPointDistance;
	private View viewRedPoint;
	private Button btnStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guider);
		
		ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
		vp_guider = (ViewPager) findViewById(R.id.vp_guider);
		viewRedPoint = findViewById(R.id.view_red_point);
		btnStart = (Button) findViewById(R.id.btn_start);
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//保存
				PrefUtils.setBoolean(GuiderActivity.this, PrefUtils.IS_USER_GUIDER_SHOWED, true);
				
				startActivity(new Intent(GuiderActivity.this, MainActivity.class));
				GuiderActivity.this.finish();
			}
		});
		
		
		initView();
		vp_guider.setAdapter(new MyGuiderAdapter());
		
		//viewpage设置事件监听
		vp_guider.setOnPageChangeListener(new GuiderPageListener());
		
	}
	
	private void initView(){
		mViewsList = new ArrayList<ImageView>();
		
		for ( int imageId: mImagesId) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(imageId);
			
			mViewsList.add(imageView);
		}
		
		for ( int i=0; i<mImagesId.length;i++) {
			ImageView point = new ImageView(this);
			point.setBackgroundResource(R.drawable.shape_point_gray);
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
	
			if(i>0){
				params.leftMargin = 10;
			}
			ll_point_group.addView(point, params);
		}
		
		
		//获取视图布局监听，布局完成后测量，取消监听
		ll_point_group.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						System.out.println("layout 结束");
						
						ll_point_group.getViewTreeObserver().removeGlobalOnLayoutListener(this);
						mPointDistance = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
						
						System.out.println("圆点距离："+mPointDistance);
					}
					
				});
	}
	
	/**
	 * 配适ViewPage
	 * @author zhe
	 *
	 */
	class MyGuiderAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return mImagesId.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			ImageView imageView = mViewsList.get(position);
			container.addView(imageView);
			return imageView;
		}
		
	}
	
	/**
	 * 监听页面滑动
	 * @author zhe
	 *
	 */
	class GuiderPageListener implements OnPageChangeListener{

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			System.out.println("页面："+position+"  位置偏移："+positionOffset+"  移动像素："+positionOffsetPixels);
			
			//圆点应该平移的距离
			int len = (int) (mPointDistance*positionOffset + mPointDistance*position);
			
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewRedPoint.getLayoutParams();
			params.leftMargin  = len;//设置左边距
			viewRedPoint.setLayoutParams(params);//重新显示位置
			
		}

		@Override
		public void onPageSelected(int position) {
			if(position == mImagesId.length-1){
				btnStart.setVisibility(View.VISIBLE);
			}else{
				btnStart.setVisibility(View.INVISIBLE);
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
			
		}

	}
	
	
	
}

package com.xiaozhe.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

	public Activity mActivity = null;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return initViews();
	}

	/**
	 * 之类必须实现，填充视图
	 * @return 
	 */
	abstract View initViews();
	
	/**
	 * 初始化数据，选择实现
	 */
	public void initData(){
		
	};
 
	
}

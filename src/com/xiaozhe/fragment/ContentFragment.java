package com.xiaozhe.fragment;

import com.xiaozhe.smaartcity.R;

import android.view.View;

public class ContentFragment extends BaseFragment {

	@Override
	View initViews() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		return view;
	}

}

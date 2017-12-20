package com.lzq.mvp.ui.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.lzq.mvp.R;
import com.lzq.mvp.ui.fragment.TestFragment;

public class TestFragmentMVPActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment_mvp);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new TestFragment()).commitAllowingStateLoss();
    }
}

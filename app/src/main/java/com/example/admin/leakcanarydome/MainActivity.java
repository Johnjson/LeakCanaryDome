package com.example.admin.leakcanarydome;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final Handler mHandler = new Handler();
    static LeakcancaryDemo mleakcancaryDemo;
    /**
     * Hello World!
     */
    private TextView mAll;
    /**
     * Handle类泄露
     */
    private Button mBut2;
    /**
     * Activity类泄露
     */
    private Button mBut3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    //2:  handle泄漏
    private void handleLeakcancary() {
        //模拟内存泄露
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 2 * 60 * 1000);
        finish();
    }


    //3:  activity类泄漏
    private void OneThreadLeakcancary() {
        //模拟内存泄露
        if (mleakcancaryDemo == null) {
            mleakcancaryDemo = new LeakcancaryDemo();
            }
        finish();
    }


    private void initView() {
        mAll = (TextView) findViewById(R.id.all);
        mBut2 = (Button) findViewById(R.id.but_2);
        mBut3 = (Button) findViewById(R.id.but_3);
        mBut2.setOnClickListener(this);
        mBut3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_2:
                handleLeakcancary();
                break;
            case R.id.but_3:
                OneThreadLeakcancary();
                break;
        }
    }

    class LeakcancaryDemo {
    }
}

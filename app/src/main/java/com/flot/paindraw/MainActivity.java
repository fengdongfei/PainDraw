package com.flot.paindraw;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity {


    private Button clear;
    private Button del;
    private Button recover;
    private TuyaView tuyaView;
    private Button savepic;
    private Button paintcolor;
    private Button paintsize;
    private Button xiangpicha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clear = (Button) findViewById(R.id.clear);
        del = (Button) findViewById(R.id.xpc);
        recover = (Button) findViewById(R.id.recover);
        savepic = (Button) findViewById(R.id.savepic);
        paintcolor = (Button) findViewById(R.id.paintcolor);
        paintsize = (Button) findViewById(R.id.paintsize);
        xiangpicha = (Button) findViewById(R.id.xiangpicha);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuyaView.redo();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuyaView.undo();
            }
        });
        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuyaView.recover();
            }
        });
        savepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuyaView.saveToSDCard();
                Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });
        paintcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuyaView.selectPaintColor(2);
            }
        });
        paintsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuyaView.selectPaintSize(20);
            }
        });
        xiangpicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuyaView.selectPaintStyle(1);
            }
        });
        initData();

    }


    private void initData() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.ff);
        //虽然此时获取的是屏幕宽高，但是我们可以通过控制framlayout来实现控制涂鸦板大小
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        int screenWidth = defaultDisplay.getWidth();
        int screenHeight = defaultDisplay.getHeight();
        tuyaView = new TuyaView(this, screenWidth, screenHeight);
        frameLayout.addView(tuyaView);
        tuyaView.requestFocus();
        tuyaView.selectPaintSize(10);
    }
}

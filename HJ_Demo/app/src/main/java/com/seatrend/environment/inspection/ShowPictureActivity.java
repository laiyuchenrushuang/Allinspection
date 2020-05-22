package com.seatrend.environment.inspection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;

/**
 * Created by ly on 2020/5/19 16:21
 */
public class ShowPictureActivity extends AppCompatActivity {

    TextView tv_titile;
    ImageView iv_back;
    PhotoView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        tv_titile = findViewById(R.id.tv_titile);
        iv_back = findViewById(R.id.iv_back);
        img = findViewById(R.id.img);
        tv_titile.setText(getIntent().getStringExtra("zpmc"));
        img.enable();//启动缩放
        Glide.with(this).load(getIntent().getStringExtra("zplj")).into(img);
        bindevent();
    }

    private void bindevent() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

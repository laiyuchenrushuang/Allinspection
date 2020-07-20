package com.seatrend.environment.inspection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ly on 2020/6/10 17:41
 */
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void btBack(View view) {
//        Intent intent = new Intent();
//        String result = "合格";
//        intent.putExtra("hj_result", result);
//        setResult(RESULT_OK, intent);
        finish();
    }
}

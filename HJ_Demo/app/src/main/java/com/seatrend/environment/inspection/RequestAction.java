package com.seatrend.environment.inspection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seatrend.environment.inspection.service.AIDLService;
import com.seatrend.vendor.IInspect;

public class RequestAction extends AppCompatActivity {

    Button bt;
    Button hj_send;
    EditText aj_send;

    private IInspect iInspect = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.hj_exit);
        aj_send = findViewById(R.id.aj_send);
        hj_send = findViewById(R.id.hj_send);
        getData();
        bindevent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindServiceByAidl();
    }

    private void bindServiceByAidl() {
        Intent intent = new Intent("com.seatrend.vendor.respond_message");
        intent.setPackage("com.seatrend.environment.inspection");
        bindService(intent, cnnec, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection cnnec = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iInspect = IInspect.Stub.asInterface(service);

            try {
                // 注册死亡代理
                if (iInspect != null) {
                    service.linkToDeath(mDeathRecipient, 0);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) { }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (iInspect == null) {
                return;
            }
            iInspect.asBinder().unlinkToDeath(this, 0);
            iInspect = null;
            //重新绑定
            bindServiceByAidl();
        }
    };

    private void getData() {
        aj_send.setText(getIntent().getStringExtra("aj_send"));
    }

    private void bindevent() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String result = "环检结论：OK";
                intent.putExtra("hj_result", result);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        hj_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    if (AIDLService.mServiceLisener != null) {


                //方案二
                if(iInspect != null) {
                    try {
                        showToast("service 发送成功");
                        iInspect.getResultByServer("主动发来错误信息: " + aj_send.getText().toString(),null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        showToast("service 注册为Exception");
                    }


//                        AIDLService.getService().serviceError("主动发来错误信息: " + aj_send.getText().toString());
//                        showToast("service 发送成功");
                    } else {
                        showToast("service 注册为空");
                    }
//                }
//                catch (RemoteException e) {
//                    e.printStackTrace();
//                    showToast("service 注册为Exception");
//                }
            }
        });
    }

    public void showLog(Object msg) {
        Log.d("lylog", " <<-- service  " + msg);
    }

    public void showToast(Object msg) {
        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
    }
}

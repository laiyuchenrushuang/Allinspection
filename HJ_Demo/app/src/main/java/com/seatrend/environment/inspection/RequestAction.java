package com.seatrend.environment.inspection;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.seatrend.environment.inspection.adpter.PhotoAdapter;
import com.seatrend.environment.inspection.entity.ShareEntity;
import com.seatrend.environment.inspection.service.AIDLService;
import com.seatrend.vendor.IInspect;

import java.security.Permission;
import java.util.ArrayList;

public class RequestAction extends AppCompatActivity {

    Button bt;
    Button hj_send;
    EditText aj_send;
    RecyclerView m_recycler_view;
    PhotoAdapter mPhotoAdapter;
    ArrayList<ShareEntity.PhotoListBean> photoList = new ArrayList<>();
    private IInspect iInspect = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.hj_exit);
        m_recycler_view = findViewById(R.id.m_recycler_view);
        aj_send = findViewById(R.id.aj_send);
        hj_send = findViewById(R.id.hj_send);

        permissionCheck();
        getData();
        initRecycleView();
        bindevent();
    }

    private void permissionCheck() {
        if (Build.VERSION.SDK_INT >= 23) {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int readPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if ( hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED
                    || readPermission != PackageManager.PERMISSION_GRANTED
                    ) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    private void initRecycleView() {
        m_recycler_view.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mPhotoAdapter = new PhotoAdapter(this, photoList);
        m_recycler_view.setAdapter(mPhotoAdapter);
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
        public void onServiceDisconnected(ComponentName name) {
        }
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

        showLog(getIntent().getStringExtra("aj_send"));
        try {
            ShareEntity entity = GsonUtils.gson(getIntent().getStringExtra("aj_send"), ShareEntity.class);
            aj_send.setText(entity.getJscs().toString());
            if (entity.getPhoto_list().size() > 0) {
                photoList = (ArrayList<ShareEntity.PhotoListBean>) entity.getPhoto_list();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showToast(e.getMessage());
        }


    }

    private void bindevent() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String result = "合格";
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
                if (iInspect != null) {
                    try {
                        showToast("service 发送成功");
                        iInspect.getResultByServer("主动发来错误信息: " + aj_send.getText().toString(), null);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            Intent intent = new Intent();
            String result = "合格";
            intent.putExtra("hj_result", result);
            setResult(RESULT_OK, intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(cnnec);
    }

    public void showLog(Object msg) {
        Log.d("lylog", " <<-- service  " + msg);
    }

    public void showToast(Object msg) {
        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
    }
}

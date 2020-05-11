# Allinspection

对程序的AIDL的一些新的理解

通信方式：

  1.UI -> Service [startService  通过intent  bindService 通过binder获取service的实例]   ？通过接口？
  2.Service -> UI [通过接口 UI对接口的实现（前端）    通过广播（后台）  ]
  
  
实现方案：

  客户端 ：Allinspection 的MainActivity    HJ_Demo 的MainActivity
  服务端 ：HJ_Demo  的AIDLService服务
  
  
流程：

  客户端向服务端请求数据：
  
  Allinspection客户端->aidl->Service->ServiceListener回调->客户端->UI
  
  服务端向客户端下发数据：
  
  HJ_Demo客户端->获取Service的Binder对象->Binder的接口->ServiceListener回调->客户端->UI
  
动态的监听注册储存器：

    //RemoteCallbackList是专门用于删除跨进程listener的接口，它是一个泛型，支持管理任意的AIDL接口
    private RemoteCallbackList<ServiceLisener> mListener = new RemoteCallbackList<>();
    
    
注意事项：APK保证独立，就是service一个 client一个，不然爆DeadObjectException的错误。
  
  

package com.seatrend.vendor;

// Declare any non-default types here with import statements
import com.seatrend.vendor.ServiceLisener;
interface IInspect {
  void  registerListener(ServiceLisener servicelisener);
  void  unRegisterListener(ServiceLisener servicelisener);

  void sendVehInfo(String sendJsonData,ServiceLisener servicelisener);
  void sendDataToServer(String sendJsonData,ServiceLisener servicelisener);
  void getResultByServer(String sendJsonData,ServiceLisener servicelisener);

}

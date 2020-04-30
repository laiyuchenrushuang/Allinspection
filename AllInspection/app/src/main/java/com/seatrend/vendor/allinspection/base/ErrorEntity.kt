package com.seatrend.vendor.allinspection.base

/**
 * Created by seatrend on 2018/8/22.
 */

class ErrorEntity {


    /**
     * timestamp : 1534930699812
     * status : 500
     * error : Internal Server Error
     * exception : feign.RetryableException
     * message : Connection refused: connect executing GET http://localhost:8081/vio/getVioByCar?hpzl=02&hphm=%E5%B7%9DAJL122&clsbdh=1234
     * path : /vio/getVioByCar
     */

    var timestamp: Long = 0
    var status: Int = 0
    var error: String? = null
    var exception: String? = null
    var message: String? = null
    var path: String? = null

    override fun toString(): String {
        return "ErrorEntity{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\''.toString() +
                ", exception='" + exception + '\''.toString() +
                ", message='" + message + '\''.toString() +
                ", path='" + path + '\''.toString() +
                '}'.toString()
    }
}

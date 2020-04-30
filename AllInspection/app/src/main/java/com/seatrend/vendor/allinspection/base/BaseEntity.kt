package com.seatrend.vendor.allinspection.base

import java.io.Serializable

/**
 * Created by seatrend on 2018/8/21.
 */

class BaseEntity : Serializable {
    var status: Boolean = false
    var code: Int = 0
    var message: String? = null
}

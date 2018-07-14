package com.perspective23.willing.modules.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.perspective23.willing.modules.dao.feed.Pagination

/**
 * Created by beer on 1/29/2018 AD.
 */

class BaseListResult<T> {
    @SerializedName("response_code")
    @Expose
    var response_code: Int? = null
    @SerializedName("response_msg")
    @Expose
    var response_msg: String? = null
    @SerializedName("result")
    @Expose
    var data: List<T>? = null
    @SerializedName("pagination")
    @Expose
    var pagination: Pagination? = null
}
package com.perspective23.willing.modules.dao.feed

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pagination {

    @SerializedName("content_count")
    @Expose
    var contentCount: Int? = null
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("next_page")
    @Expose
    var nextPage: Int? = null
    @SerializedName("last_page")
    @Expose
    var lastPage: Int? = null
    @SerializedName("perpage")
    @Expose
    var perpage: Int? = null

}

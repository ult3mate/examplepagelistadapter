package com.perspective23.willing.modules.dao.feed

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FeedResult{

    @SerializedName("content_id")
    @Expose
    var contentId: String? = null
    @SerializedName("post_time")
    @Expose
    var postTime: String? = null
    @SerializedName("location_name")
    @Expose
    var locationName: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("thumbnail_url")
    @Expose
    var thumbnailUrl: String? = null
    @SerializedName("picture_url")
    @Expose
    var pictureUrl: List<String>? = null
    @SerializedName("detail")
    @Expose
    var detail: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("tel")
    @Expose
    var tel: String? = null
    @SerializedName("line_id")
    @Expose
    var lineId: String? = null
    @SerializedName("total_like")
    @Expose
    var totalLike: Int? = null
    @SerializedName("total_want")
    @Expose
    var totalWant: Int? = null
    @SerializedName("total_comment")
    @Expose
    var totalComment: Int? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("post_time_text")
    @Expose
    var postTimeText: String? = null
    @SerializedName("liked")
    @Expose
    var liked: Boolean? = null
}

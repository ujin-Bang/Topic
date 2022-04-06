package com.topic.toyproject.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.topic.toyproject.datas.TopicData

class TopicAdapter(
    val mContext: Context,
    resId: Int,
    val mList: List<TopicData>
): ArrayAdapter<TopicData>(mContext, resId, mList) {
}
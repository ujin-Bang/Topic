package com.topic.toyproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.topic.toyproject.R
import com.topic.toyproject.datas.TopicData

class TopicAdapter(
    val mContext: Context,
    resId: Int,
    val mList: List<TopicData>
): ArrayAdapter<TopicData>(mContext, resId, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.topic_list_item, null)

        }
        val row = tempRow!!

        val data = mList[position]

        val txtTitle = row.findViewById<TextView>(R.id.txtTitle)
        val txtReplyCount = row.findViewById<TextView>(R.id.txtReplyCount)
        val imgTopicBackground = row.findViewById<ImageView>(R.id.imgTopicBackground)

        txtTitle.text = data.title
        txtReplyCount.text = "${data.replyCount.toString()} 명 참여중"
//      data > 서버에서 준 주제 데이터
//        imageURL 변수 파싱 => 이미지의 인터넷 주소.
//        웹이 있는 이미지 > 이미지뷰에 적용 > Glide활용
        Glide.with(mContext).load(data.imageURL).into(imgTopicBackground)

        return row
    }
}
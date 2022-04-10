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
import com.topic.toyproject.datas.ReplyData
import com.topic.toyproject.datas.TopicData
import java.text.SimpleDateFormat
import java.util.*

class ReplyAdapter(
    val mContext: Context,
    resId: Int,
    val mList: List<ReplyData>
): ArrayAdapter<ReplyData>(mContext, resId, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.reply_list_item, null)

        }
        val row = tempRow!!

        val data = mList[position]

        val txtSelectedSide = row.findViewById<TextView>(R.id.txtSelectedSide)
        val txtWriterNickname = row.findViewById<TextView>(R.id.txtWriterNickname)
        val txtReplyContent = row.findViewById<TextView>(R.id.txtReplyContent)
        val txtCreatedAt = row.findViewById<TextView>(R.id.txtCreatedAt)

        txtReplyContent.text = data.content
        txtWriterNickname.text = data.writer.nickname
        txtSelectedSide.text = "[${data.selectedSide.title}]"

//        임시 - 작성일자만 "2022-03-10" 형태로 표현 => 연/ 월/ 일 데이터로 가공
//        월은 1작게 나옴 . +1로 보정
//        txtCreatedAt.text = "${data.createAt.get(Calendar.YEAR)}-${data.createAt.get(Calendar.MONTH)+1}-${data.createAt.get(Calendar.DAY_OF_MONTH)}"

//        임시 2 - "2022-03-10" 형태로 표현 => SimpleDateFormat 활용

//        연습
//        양식1) 2022년 3월 5일
//        양식2) 220305
//        양식3) 3월 5일 오전 2시 5분
//        양식4) 21년 3/5 (토) - 18:05
        val sdf = SimpleDateFormat("yy년 M/D (E) - HH:mm" )

//        sdf.format(Date객체) => 지정해둔 양식의 String으로 가공
//        createdAt : Calendar / format의 파라미터 : Date => Calendar의 내용물인 time변수가 Date.
        txtCreatedAt.text = sdf.format(data.createAt.time)

        return row
    }
}
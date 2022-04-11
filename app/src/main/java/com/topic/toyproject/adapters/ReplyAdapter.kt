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
import com.topic.toyproject.ViewTopicDetailActivity
import com.topic.toyproject.datas.ReplyData
import com.topic.toyproject.datas.TopicData
import com.topic.toyproject.utils.ServerUtil
import org.json.JSONObject
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
        val txtReReplyCount =row.findViewById<TextView>(R.id.txtReReplyCount)
        val txtLikeCount =row.findViewById<TextView>(R.id.txtLikeCount)
        val txtHateCount =row.findViewById<TextView>(R.id.txtHateCount)

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
//        val sdf = SimpleDateFormat("yy년 M/D (E) - HH:mm" )

//        sdf.format(Date객체) => 지정해둔 양식의 String으로 가공
//        createdAt : Calendar / format의 파라미터 : Date => Calendar의 내용물인 time변수가 Date.
        txtCreatedAt.text = data.getFormattedCreatedAt()

        txtReReplyCount.text = "답글 ${data.reReplyCount}"
        txtLikeCount.text = "좋아요 ${data.likeCount}"
        txtHateCount.text = "싫어요 ${data.hateCount}"

        txtLikeCount.setOnClickListener {
            ServerUtil.postRequestLikeOrHate(mContext,data.id, true, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

//                  무조건 댓글 목록 새로고침
//                   Adapter 코딩 => 액티비티의 기능 실행

//                    어댑터 객체화시, mContext 변수에 어느 화면에서 사용하는지 대입
//                    mContext: Context타입. 대입 객체: ViewTopic액티비티 객체 => 다형성

//                    부모 형태의 변수에 담긴 자식 객체는 캐스팅을 통해서 원상복구 가능.
//                    자식에서 만든 별도의 함수들을 다시 사용 가능

                    (mContext as ViewTopicDetailActivity).getTopicDetailFromServer()
                }

            })
        }
        txtHateCount.setOnClickListener {
            ServerUtil.postRequestLikeOrHate(mContext,data.id, false, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                    (mContext as ViewTopicDetailActivity).getTopicDetailFromServer()
                }

            })
        }
        return row
    }
}
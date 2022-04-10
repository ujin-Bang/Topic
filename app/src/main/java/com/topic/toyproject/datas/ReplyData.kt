package com.topic.toyproject.datas

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class ReplyData(
    var id: Int,
    var content: String,
) {
    var writer = UserData() //모든 댓글에는 작성자가 있다. null 가능성이 없다.

    var selectedSide = SideData() //모든 댓글에는 선택한 진영이 있다. null 가능성 x

//    작성일시를 담아둘 변수
//    일/시 데이터를 변경 => 내부의 숫자만 변경. 변수에 새 객체 대입X => val로써도 됨.
    val createAt = Calendar.getInstance()

//    보조 생성자 추가 연습 : 파라미터 x
    constructor() : this(0,"내용없음")

    companion object {
        fun getReplyDataFromJson( jsonObj: JSONObject): ReplyData{

            val replyData = ReplyData()

//            JSON정보 > 멤버변수 채우기
            replyData.id = jsonObj.getInt("id")
            replyData.content = jsonObj.getString("content")
            replyData.writer = UserData.getUerDataFromJson( jsonObj.getJSONObject("user"))

            replyData.selectedSide = SideData.getSideDataFromJson(jsonObj.getJSONObject("selected_side"))

//            Calendar로 되어 있는 작성일시의 시간을, 서버가 알려주는 댓글 작성 일시로 맞춰줘야함.

//            임시1) 2022-01-12 10:55:35로 변경(한번에 모두 변경)
//            replyData.createAt.set(2022, Calendar.JANUARY, 12, 10, 55, 35)

//            임시2) 연도만 2021년으로 변경(항목을 찍어서 변경)
//            replyData.createAt.set(Calendar.YEAR, 2021)

//            실제) 서버가 주는 creatd_at에담긴 String을 => parse해서 Calendar로 변경
//            createdAt 변수의 일시값으로 => parse 결과물 사용.

//            서버가 주는 양식을 보고 그대로 적자.
            val sdf = SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" )

//            created_at으로 내려오는 문구
            val createdAtStr = jsonObj.getString("created_at")

//            createdAtStr 변수를 => Date로 변경 (parse) => Calendar의 time에 대입
            replyData.createAt.time = sdf.parse(createdAtStr)

            return replyData

        }
    }
}
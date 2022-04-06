package com.topic.toyproject.utils

import android.content.Context

class ContextUtil {

    companion object {

//  메모장 파일 이름처럼, SharedPreferences의 이름 설정. 하나의 메모장이 여러개의 항목 저장 가능

        private val prefName = "OkHttpPracticePref"

//        저장할 데이터의 항목명도 변수로 만들어 두자.

       private val TOKEN = "TOKEN"
       private val AUTO_LOGIN = "AUTO_LOGIN"

//        데이터 저장 함수(setter) / 조회함수(getter) 별개로 작성
//        TOKEN 항목에 저장 => token 항목 조회? 데이터 인식x. 대소문자까지 동일해야 함
//        오타를 줄이고, 코딩 편하게 하는 조치로 항목명을 변수로 만듬

        fun setToken( context: Context, token: String ){

//            메모장 파일을 열자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            입력 들어온 token 내용(TOKEN 항목에) 저장.
            pref.edit().putString(TOKEN, token).apply()
        }

        fun getToken(context: Context): String{
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(TOKEN,"")!!
        }


        fun setAutoLogin(context: Context, isAuto: Boolean){
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN, isAuto).apply()
        }

        fun getAutoLogin(context: Context): Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN, true)
        }

    }

}
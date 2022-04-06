package com.topic.toyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

    }

    override fun setValues() {

//        2.5초가 지나면 -> 자동로그인을 해도 되는지? -> 상황에 맞는 화면으로 이동.
        val myHanler = Handler(Looper.getMainLooper())

        myHanler.postDelayed({

//            자동로그인을 해도 되는가?
//            1) 사용자가 자동로그인 의사를 OK 했는가?
//            2) 로그인 시에 받아낸 토큰값이 지금도 유효한가?
         if () {

//             둘다 OK라면 바로 메인화면으로 이동
         }
            else {
//                아니라면 로그인 화면으로 이동
         }

        }, 2500)

    }
}
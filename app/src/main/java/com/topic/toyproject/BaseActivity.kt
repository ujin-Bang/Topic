package com.topic.toyproject

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

// 다른 모든 화면이 공통적으로 가질 기능 / 멤버변수를 모아두는 (부모) 클래스

abstract class BaseActivity: AppCompatActivity() {

//    Context 계열의 파라미터에 대입할때, 보통 this로 대입.
//    인터페이스가 엮이기 시작하면? this@어느화면인지 추가로 고려.

//    미리 mContext변수에 화면에 this를 담아두고 => 모든 액티비티에 상속으로 물려주자.
   lateinit var mContext: Context

//   액션바에서 UI 변수를 멤버변수로 > 상속
//   =>변수에 대입: 커스텀 액션바 세팅 뒤에.

   lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this//화면이 만들어질때 this를 대입
        supportActionBar?.let {
//            supportActionBar변수가 null이 아닐때만 실행할 코드 , 코틀린스러운 문법(if문대신 ?.let 사용)
            setCustomActionBar()
        }


    }

//    setupEvents / setValues 힘수를 만들어두고, 물려주자.
//    실제 함수를 구현해서 물려줘봐야 오버라이딩 해서 사용한다.
//    => 추상 메쏘드로 물려줘서, 반드시 오버라이딩하게 만들자

    abstract fun setupEvents()
    abstract fun setValues()

//    실제 구현 내용을 같이 물려주는 함수.(일반 함수)
//    액션바 설정 기능

    fun setCustomActionBar(){

        val defaultActionBar = supportActionBar!!
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        defaultActionBar.setCustomView(R.layout.my_custom_action_bar)

        val toolbar = defaultActionBar.customView.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0,0)

//        xml에 그려둔 UI가져오기
        btnBack = defaultActionBar.customView.findViewById(R.id.btnBack)

//        누르면 화면 종료 : 모든 화면 공통
        btnBack.setOnClickListener {
            finish()
        }


    }

}
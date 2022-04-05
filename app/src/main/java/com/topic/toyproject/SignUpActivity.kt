package com.topic.toyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.DisplayNameSources.EMAIL
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.topic.toyproject.databinding.ActivitySignUpBinding
import com.topic.toyproject.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        binding.btnEmailCheck.setOnClickListener {

//            입력한 이메일값 추출
            val inputEmail = binding.edtEmail.text.toString()

//            서버에 중복확인 기능(/user_check - GET) API활용 => ServerUtil에 함수 추가, 가져다 활용
//            그 응답 code값에 따라 다른 문구 배치

            ServerUtil.getRequestDuplicatedCheck("EMAIL", inputEmail, null )

        }

        binding.btnSignUp.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()

            ServerUtil.putRequestSignUp(
                inputEmail,
                inputPw,
                inputNickname,
                object : ServerUtil.JsonResponseHandler{
                    override fun onResponse(jsonObj: JSONObject) {

//                        회원가입 성공/ 실패 분기
                        val code = jsonObj.getInt("code")

                        if (code == 200){

//                            가입한 사람의 닉네임 추출 -> ~~님 가입을 축하합니다. 토스트
                            val dataObj = jsonObj.getJSONObject("data")
                            val userObj = dataObj.getJSONObject("user")
                            val nickname = userObj.getString("nick_name")

                            runOnUiThread {
                                Toast.makeText(mContext, "${nickname}님 가입을 축하합니다.", Toast.LENGTH_SHORT).show()

                            }
//                            회원가입 화면 종료 >로그인화면 복귀
//                            화면 종료: 객체 소멸(UI동작이 아님)
                            finish()
                        }
                        else {
                            val message = jsonObj.getString("message")

                            runOnUiThread {
                                Toast.makeText(mContext, "실패사유 : ${message}", Toast.LENGTH_SHORT).show()

                            }
                        }

                    }

                })
        }

    }

    override fun setValues() {

    }



}
package com.topic.toyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.topic.toyproject.databinding.ActivityViewTopicDetailBinding

class ViewTopicDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityViewTopicDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_topic_detail)
    }
}
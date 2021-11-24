package com.example.two_activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    val EXTRA_REPLY = "com.example.two_activities.extra.REPLY"
    private lateinit var mReply: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent = intent
        val message = intent.getStringExtra(MainActivity().EXTRA_MESSAGE)
        val textView = findViewById<TextView>(R.id.text_message)
        mReply = findViewById(R.id.editText_second)

        textView.text = message
    }

    fun returnReply(view: android.view.View) {
        val reply = mReply.text.toString()
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_REPLY, reply)
        setResult(RESULT_OK, replyIntent)
        finish()
    }
}
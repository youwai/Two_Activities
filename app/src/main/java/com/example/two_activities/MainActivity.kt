package com.example.two_activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.two_activities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = MainActivity::class.java.simpleName
    private var mMessageEditText: EditText? = null
    val EXTRA_MESSAGE: String = "com.example.two_activities.extra.MESSAGE"
    val TEXT_REQUEST = 1
    private lateinit var mReplyHeadTextView: TextView
    private lateinit var mReplyTextView: TextView

    private lateinit var binding: ActivityMainBinding
    private lateinit var getReply: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mMessageEditText = binding.editTextMain
        mReplyHeadTextView = binding.textHeaderReply
        mReplyTextView = binding.textMessageReply

        binding.buttonMain.setOnClickListener {
            launchSecondActivity(it)
        }

        getReply = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {  result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                val reply = data?.getStringExtra(SecondActivity().EXTRA_REPLY)

                mReplyHeadTextView.visibility = View.VISIBLE
                mReplyTextView.text = reply
                mReplyTextView.visibility = View.VISIBLE
            }
        }
    }

    private fun launchSecondActivity(view: View) {
        Log.d(LOG_TAG, "Button clicked!")

        val intent = Intent(this, SecondActivity::class.java)
        val message = mMessageEditText?.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)

        getReply.launch(intent)
    }
}
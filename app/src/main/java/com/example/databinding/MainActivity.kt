package com.example.databinding

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create an event handler for buttonSend
        buttonSend.setOnClickListener{
            sendMessage()
        }
    }

    private fun sendMessage() {
        //create an explicit intent for the SecondActivity
        val intent = Intent(this, Second::class.java)

        //prepare extra
        val message = editTextMessage.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        //start activity with no return value
        //startActivity(intent)

        //start activitty with return value
        startActivityForResult(intent, REQUEST_REPLY)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_REPLY) {
            if(resultCode == Activity.RESULT_OK) {
                val reply = data?.getStringExtra(MainActivity.EXTRA_REPLY)
                textViewReply.text = String.format("%s : %s", getString(R.string.reply), reply)
            }
            else {
                textViewReply.text = String.format("%s : %s", getString(R.string.reply), "No reply")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        const val EXTRA_MESSAGE = "com.example.databinding.MESSAGE"
        const val REQUEST_REPLY = 1
        const val EXTRA_REPLY = "com.example.databinding.REPLY"
    }
}

package com.example.timer_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var total = 0
    var started = false
    val handler2 = Handler(){
        val minute = String.format("%02d", total/60)
        val second = String.format("%02d", total%60)
        textTimer.text = "$minute:$second"

        true
    }
    val handler = object : Handler(){
    override fun handleMessage(msg: Message) {
        val minute = String.format("%02d", total/60)
        val second = String.format("%02d", total%60)
        textTimer.text = "$minute:$second"
    }
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            started = true

            thread(start=true){
                while (started){
                    Thread.sleep(1000)
                    if(started){
                        total = total + 1
                        handler?.sendEmptyMessage(0)
                    }
                }
            }
        }

        btnStop.setOnClickListener {
            if(started) {
                started = false
                total = 0
                textTimer.text = "00:00"
            }
        }
    }
}
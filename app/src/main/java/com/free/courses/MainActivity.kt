package com.free.courses

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class MainActivity : AppCompatActivity() {

    lateinit var thread: Thread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getParsedNumbers()
    }



    fun getParsedNumbers(){
        thread = Thread(Runnable {
            try {
                while (!Thread.currentThread().isInterrupted){
                    JsoupExample()
                    Log.d("check", "pp")

                }
            }
            catch (consumed:InterruptedException){
                thread.interrupt();
            }
        })
        thread.start()
    }

    fun JsoupExample(){
        val res: Connection.Response = Jsoup
            .connect("https://coursehunter.net/sign-in")
            .data("e_mail", "job@alif.tj", "password", "UAuJKmPAPrGu7g5")
            .method(Connection.Method.POST)
            .execute()
        val cookies: Map<String, String> = res.cookies()
        val doc: Document = Jsoup.connect("https://coursehunter.net/").cookies(cookies).get()
        Log.d("check", "JsoupExample: "+doc.html())
        thread.interrupt()
    }


}
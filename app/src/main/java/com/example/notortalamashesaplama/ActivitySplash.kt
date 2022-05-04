package com.example.notortalamashesaplama

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activit_splash.*

class ActivitySplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activit_splash)


        var animasyonmButon = AnimationUtils.loadAnimation(this,R.anim.giris_ekran)
        var animasyonResim = AnimationUtils.loadAnimation(this,R.anim.giris_ekran_resim)
        var geriDonenButon = AnimationUtils.loadAnimation(this,R.anim.asagibuton)
        var geriDonenResim = AnimationUtils.loadAnimation(this,R.anim.asagiresim)


        giris.animation=animasyonmButon
        kalemResim.animation=animasyonResim

        giris.setOnClickListener{
            giris.startAnimation(geriDonenButon)
            kalemResim.startAnimation(geriDonenResim)

            object : CountDownTimer(1000,1000){
                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }

            }.start()


            }


        }
    }

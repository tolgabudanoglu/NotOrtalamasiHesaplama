package com.example.notortalamashesaplama

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders.view.*


class MainActivity : AppCompatActivity() {

    private var tumDerslerinBilgileri:ArrayList<Dersler> = ArrayList(5)
    private val DerslerBilgiler = arrayOf("kalkülüs","algoritma","fizik","türk dili","tarih")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter= ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,DerslerBilgiler)

        dersAd.setAdapter(adapter)


        if (rootLayout.childCount == 0){
            Hesaplama.visibility = View.INVISIBLE
        }
        else{
            Hesaplama.visibility = View.VISIBLE
        }

        dersEkleme.setOnClickListener{

            if (!dersAd.text.isNullOrEmpty()){
                var inflater = LayoutInflater.from(this)

                var yeniDersView = inflater.inflate(R.layout.yeni_ders,null)
                yeniDersView.YeniDersAd.setAdapter(adapter)

                //statik alandan değerleri alma
                var dersAdi = dersAd.text.toString()
                var notKredi = dersKredi.selectedItemPosition
                var notHarf = dersNot.selectedItemPosition

                yeniDersView.YeniDersAd.setText(dersAdi)
                yeniDersView.YeniDersKredi.setSelection(notKredi)
                yeniDersView.YeniDersNotu.setSelection(notHarf)

                yeniDersView.dersSilme.setOnClickListener{
                    rootLayout.removeView(yeniDersView)
                    if (rootLayout.childCount == 0){
                        Hesaplama.visibility = View.INVISIBLE
                    }
                    else{
                        Hesaplama.visibility = View.VISIBLE
                    }
                }

                rootLayout.addView(yeniDersView)


                Hesaplama.visibility = View.VISIBLE
                sifirla()
            }
            else{
                FancyToast.makeText(this,"ders adını giriniz !",FancyToast.LENGTH_LONG,
                    FancyToast.WARNING,false).show();
            }

        }

    }

    private fun sifirla() {
        dersAd.setText("")
        dersKredi.setSelection(0)
        dersNot.setSelection(0)
    }


    fun ortalamaHesapla(View : View){

        var toplamNot = 0.0
        var toplamKredi = 0.0
        var sonuc = 0.0

        for (i in 0..rootLayout.childCount-1){

            var tekSatir = rootLayout.getChildAt(i)
            var geciciDers = Dersler(tekSatir.YeniDersAd.text.toString(),((tekSatir.YeniDersKredi.selectedItemPosition) + 1).toString(),tekSatir.YeniDersNotu.selectedItem.toString())
            tumDerslerinBilgileri.add(geciciDers)
        }

        for (oankiDers in tumDerslerinBilgileri){

            toplamNot += harfiNotaCevir(oankiDers.dersNotu) * (oankiDers.dersKredi.toDouble())
            toplamKredi += oankiDers.dersKredi.toDouble()
            sonuc = toplamNot/toplamKredi
        }


        FancyToast.makeText(this,"ORTALAMA : %.2f".format(sonuc),FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
        tumDerslerinBilgileri.clear()

    }

    fun harfiNotaCevir(gelenNotHarf:String) : Double{

        var deger = 0.0
        when(gelenNotHarf){
            "AA" -> deger=4.0
            "BA" -> deger=3.5
            "BB" -> deger=3.0
            "CB" -> deger=2.5
            "CC" -> deger=2.0
            "DC" -> deger=1.5
            "DD" -> deger=1.0
            "FD" -> deger=0.5
            "FF" -> deger=0.0
        }
        return deger

    }
}




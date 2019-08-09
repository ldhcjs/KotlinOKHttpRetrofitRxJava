package com.ldhcjs.kotlinokhttpretrofitrxjava

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val TAG: String = "MainActivity"

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = KotlinOKHttpRetrofitRxJavaManager.getInstance()
        adapter.requestContributors("ldhcjs", "GetPackagesName")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError {
                Toast.makeText(this, "doOnError", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "doOnError")
            }
            .unsubscribeOn(Schedulers.io())
            .onErrorReturn { t: Throwable ->
                Log.d(TAG, "onErrorReturn : " + t.message)
                arrayOf(Contributors())
            }
            .subscribe { result ->
                if ("User" == result[0].getType()) {
                    Toast.makeText(this, "Good", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "subscribe good")
                } else {
                    Log.d(TAG, "subscribe bad")
                }
            }

    }
}

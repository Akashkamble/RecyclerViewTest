package com.akash.recyclerviewtest

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import okhttp3.OkHttpClient

/**
 * Created by Akash on 2020-03-16
 */

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        coilSetUp()
    }

    private fun coilSetUp() {
        Coil.setDefaultImageLoader {
            ImageLoader(this) {
                crossfade(true)
                allowHardware(false)
                bitmapPoolPercentage(0.5)
                okHttpClient {
                    OkHttpClient.Builder()
                        .cache(CoilUtils.createDefaultCache(this@App))
                        .build()
                }
            }
        }
    }
}

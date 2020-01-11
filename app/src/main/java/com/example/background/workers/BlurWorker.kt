package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.R
import timber.log.Timber

class BlurWorker(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        makeStatusNotification("Blurring image", applicationContext)

        return try {
            val picture = BitmapFactory.decodeResource(
                    applicationContext.resources,
                    R.drawable.test
            )

            val output = blurBitmap(picture, applicationContext)
            val outputUri = writeBitmapToFile(applicationContext, output)

            makeStatusNotification("Output is $outputUri", applicationContext)

            Result.success()
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            Result.failure()
        }
    }
}
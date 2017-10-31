package com.gnardini.marketoffers.ui.scan_qr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.gnardini.marketoffers.R
import com.gnardini.marketoffers.kotterknife.bindView
import com.gnardini.marketoffers.zxing.IntentIntegrator
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector


class ScanQrActivity : AppCompatActivity() {

    private val qrView : ImageView by bindView(R.id.qr_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan_qr_activity)


        val detector = BarcodeDetector.Builder(applicationContext)
                .setBarcodeFormats(Barcode.CODE_128)
                .build()
        if (!detector.isOperational) {
            Log.e("ASD",  "Could not set up the detector!")
            return
        }

//        val frame = Frame.Builder().setBitmap(myBitmap).build()
//        val barcodes = detector.detect(frame)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent)
        if (result != null) {
            val contents = result.contents
            if (contents != null) {
                Log.e("ASD", "SUCCESS " + contents)
//                showDialog(R.string.result_succeeded, result.toString())
            } else {
                Log.e("ASD", "FAIL")
//                showDialog(R.string.result_failed,
//                        getString(R.string.result_failed_why))
            }
        }
    }
}
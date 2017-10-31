package com.gnardini.marketoffers.ui.show_qr

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.gnardini.marketoffers.R
import com.gnardini.marketoffers.kotterknife.bindView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter

class ShowQrActivity : AppCompatActivity() {

    private val qrView : ImageView by bindView(R.id.qr_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_qr_activity)

        val barcodeStr : String? = intent.extras.getString(BARCODE_STRING)

        if (barcodeStr == null) {
            finish()
        } else {
            qrView.post { setBarcode(barcodeStr) }
        }
    }

    private fun setBarcode(barcodeStr : String) {
        val bitmap = encodeAsBitmap(barcodeStr)
        qrView.setImageBitmap(bitmap)
    }

    private fun encodeAsBitmap(str: String): Bitmap? {
        val width = qrView.width
        val height = (qrView.width * .5).toInt()

        val writer = MultiFormatWriter()
        val result = writer.encode(str, BarcodeFormat.CODE_128, width, height)
        val imageBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        for (i in 0..width-1) {
            for (j in 0..height-1) {
                imageBitmap.setPixel(i, j, if (result.get(i, j)) Color.BLACK else Color.WHITE)
            }
        }

        return imageBitmap
    }

    companion object {
        val BARCODE_STRING = "BARCODE_STRING"
    }

}
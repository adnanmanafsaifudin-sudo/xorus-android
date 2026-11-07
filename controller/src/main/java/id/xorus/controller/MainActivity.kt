package id.xorus.controller

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }

        val title = TextView(this).apply {
            text = "XORUS CONTROLLER"
            textSize = 26f
            gravity = Gravity.CENTER
        }

        val status = TextView(this).apply {
            text = "Belum ada Agent terhubung"
            textSize = 16f
            gravity = Gravity.CENTER
        }

        val connectButton = Button(this).apply {
            text = "Hubungkan Agent"
            setOnClickListener {
                status.text =
                    "Fitur koneksi belum diaktifkan. Agent harus menyetujui koneksi."
            }
        }

        layout.addView(title)
        layout.addView(status)
        layout.addView(connectButton)

        setContentView(layout)
    }
}

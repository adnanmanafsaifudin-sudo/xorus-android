package id.xorus.agent

import android.app.Activity
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.view.Gravity

class MainActivity : Activity() {

    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }

        val title = TextView(this).apply {
            text = "XORUS AGENT"
            textSize = 28f
            gravity = Gravity.CENTER
        }

        status = TextView(this).apply {
            text = "Perangkat siap"
            textSize = 16f
            gravity = Gravity.CENTER
        }

        val cameraButton = Button(this).apply {
            text = "Minta Izin Kamera"
            setOnClickListener {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                        arrayOf(Manifest.permission.CAMERA), 100
                    )
                } else {
                    status.text = "Izin kamera sudah diberikan"
                }
            }
        }

        val screenButton = Button(this).apply {
            text = "Screen Sharing"
            setOnClickListener {
                status.text =
                    "Berbagi layar memerlukan persetujuan sistem Android setiap sesi."
            }
        }

        layout.addView(title)
        layout.addView(status)
        layout.addView(cameraButton)
        layout.addView(screenButton)

        setContentView(layout)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )

        if (requestCode == 100) {
            status.text = if (
                grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                "Izin kamera diberikan"
            } else {
                "Izin kamera ditolak"
            }
        }
    }
}

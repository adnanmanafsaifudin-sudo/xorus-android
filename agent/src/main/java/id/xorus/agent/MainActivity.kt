package id.xorus.agent

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat

class MainActivity : Activity() {

    private lateinit var previewView: PreviewView
    private lateinit var status: TextView
    private var cameraProvider: ProcessCameraProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        previewView = findViewById(R.id.cameraPreview)
        status = findViewById(R.id.cameraStatus)

        findViewById<Button>(R.id.startCamera).setOnClickListener {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA), 100
                )
            }
        }

        findViewById<Button>(R.id.stopCamera).setOnClickListener {
            stopCamera()
        }
    }

    private fun startCamera() {
        status.text = "Menyiapkan kamera..."

        val future = ProcessCameraProvider.getInstance(this)

        future.addListener({
            try {
                cameraProvider = future.get()

                val preview = Preview.Builder().build()
                preview.setSurfaceProvider(previewView.surfaceProvider)

                cameraProvider?.unbindAll()
                cameraProvider?.bindToLifecycle(
                    this,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview
                )

                status.text = "Kamera aktif"
            } catch (e: Exception) {
                status.text = "Gagal membuka kamera"
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun stopCamera() {
        cameraProvider?.unbindAll()
        status.text = "Kamera dimatikan"
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
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                status.text = "Izin kamera ditolak"
            }
        }
    }

    override fun onDestroy() {
        stopCamera()
        super.onDestroy()
    }
}

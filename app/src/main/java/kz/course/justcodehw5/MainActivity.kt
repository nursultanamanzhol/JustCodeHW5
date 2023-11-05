package kz.course.justcodehw5

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import kz.course.justcodehw5.databinding.ActivityMainBinding
import kz.course.justcodehw5.databinding.BottomSheetDialogMapsBinding

class MainActivity : FragmentActivity(), OnMapReadyCallback {
    private lateinit var gMap: GoogleMap
    private lateinit var binding: ActivityMainBinding // Изменение на использование View Binding
    private lateinit var maps: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        maps = findViewById(R.id.map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.gMap = googleMap

        val Qazaq_Oil = LatLng(43.263151, 76.898191)
        val Royal_Petrol = LatLng(43.256661, 76.924593)
        val SDU = LatLng(43.207623, 76.669741)
        val markerQazaqOil = this.gMap.addMarker(
            MarkerOptions()
                .position(Qazaq_Oil)
                .title("Qazaq Oil in Almaty")
        )

        val markerRoyalPetrol = this.gMap.addMarker(
            MarkerOptions()
                .position(Royal_Petrol)
                .title("Royal Petrol in Almaty")
        )

        val markerSDU = this.gMap.addMarker(
            MarkerOptions()
                .position(SDU)
                .title("SDU")
        )

        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(SDU))

        this.gMap.setOnMarkerClickListener { marker ->
            when (marker) {
                markerQazaqOil -> {
                    showBottomTypeRadioButtonDialog(marker.title, R.drawable.qazaq_oil)
                }

                markerSDU -> {
                    showBottomTypeRadioButtonDialog(marker.title, R.drawable.sdu_icon)
                }

                markerRoyalPetrol -> {
                    showBottomTypeRadioButtonDialog(marker.title, R.drawable.royal_petrol_icon)
                }
            }
            true
        }


    }

    fun showBottomTypeRadioButtonDialog(title: String?, imageResId: Int) {
        val dialogBinding = BottomSheetDialogMapsBinding.inflate(LayoutInflater.from(this))
        val bottomTypeSheetDialog = BottomSheetDialog(this)
        bottomTypeSheetDialog.setContentView(dialogBinding.root) // Установка содержимого для BottomSheetDialog
        dialogBinding.titleTv.text = title
        dialogBinding.imageView.setImageResource(imageResId)
        bottomTypeSheetDialog.show()
    }


}


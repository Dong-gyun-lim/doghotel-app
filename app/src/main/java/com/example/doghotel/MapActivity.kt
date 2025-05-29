package com.example.doghotel

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private var currentLocationMarker: Marker? = null
    private var hotelsRendered = false
    private lateinit var hotelList: List<Hotel>
    private lateinit var selectedUser: DogUser

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        selectedUser = intent.getParcelableExtra("selectedUser") ?: return
        Log.d("MapActivity", "선택된 유저: ${selectedUser.ownerName}, ${selectedUser.dogName}, ${selectedUser.dogAge}")
        Toast.makeText(this, "${selectedUser.ownerName}의 반려견 ${selectedUser.dogName} (${selectedUser.dogAge}살)", Toast.LENGTH_LONG).show()

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        hotelList = readHotelsFromAssets()

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_fragment) as? MapFragment
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_fragment, it).commit()
            }

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map

        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.uiSettings.isLocationButtonEnabled = true

        val cameraUpdate = CameraUpdate.zoomTo(14.0)
        naverMap.moveCamera(cameraUpdate)

        naverMap.addOnLocationChangeListener { location ->
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)

                if (currentLocationMarker == null) {
                    currentLocationMarker = Marker().apply {
                        position = currentLatLng
                        captionText = "현재 위치"
                    }
                    currentLocationMarker?.map = naverMap
                } else {
                    currentLocationMarker?.position = currentLatLng
                }

                if (!hotelsRendered) {
                    hotelList.forEach { hotel ->
                        val latLng = generateNearbyLocation(location.latitude, location.longitude)

                        val marker = Marker().apply {
                            position = latLng
                            captionText = hotel.name
                        }

                        marker.map = naverMap

                        marker.setOnClickListener {
                            showHotelBottomSheet(hotel)
                            true
                        }
                    }
                    hotelsRendered = true
                }

                naverMap.moveCamera(CameraUpdate.scrollTo(currentLatLng))
            }
        }
    }

    private fun showHotelBottomSheet(hotel: Hotel) {
        val bottomSheet = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_hotel, null)

        view.findViewById<TextView>(R.id.hotelNameText).text = hotel.name
        view.findViewById<TextView>(R.id.hotelRatingText).text = hotel.rating
        view.findViewById<TextView>(R.id.hotelPriceText).text = hotel.price
        view.findViewById<TextView>(R.id.hotelDescriptionText).text = hotel.description
        view.findViewById<TextView>(R.id.hotelAddressText).text = hotel.address
        view.findViewById<ImageView>(R.id.hotelImageView).setImageResource(R.mipmap.ic_launcher)

        // ✅ '목록 보기' 버튼 클릭 시 MainActivity로 이동 + 사용자 정보 전달
        findViewById<Button>(R.id.viewListButton).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("selectedUser", selectedUser)
            startActivity(intent)
        }

        bottomSheet.setContentView(view)
        bottomSheet.show()
    }

    private fun readHotelsFromAssets(): List<Hotel> {
        val hotels = mutableListOf<Hotel>()
        val inputStream = assets.open("hotels.txt")
        inputStream.bufferedReader().useLines { lines ->
            lines.forEach { line ->
                val parts = line.split("|")
                if (parts.size == 7) {
                    hotels.add(
                        Hotel(
                            name = parts[0],
                            rating = parts[1],
                            distance = parts[2],
                            price = parts[3],
                            imageResId = parts[4].toIntOrNull() ?: 0,
                            description = parts[5],
                            address = parts[6]
                        )
                    )
                }
            }
        }
        return hotels
    }

    private fun generateNearbyLocation(baseLat: Double, baseLng: Double): LatLng {
        val randomLat = baseLat + (Math.random() - 0.5) * 0.01
        val randomLng = baseLng + (Math.random() - 0.5) * 0.01
        return LatLng(randomLat, randomLng)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)
            } else {
                Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

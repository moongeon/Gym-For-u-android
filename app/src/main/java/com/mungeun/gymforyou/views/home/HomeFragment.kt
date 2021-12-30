package com.mungeun.gymforyou.views.home


import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mungeun.domain.model.gym.Address
import com.mungeun.domain.model.gym.Gym
import com.mungeun.domain.model.gym.Location
import com.mungeun.gymforyou.R
import com.mungeun.gymforyou.databinding.FragmentHomeBinding
import com.mungeun.gymforyou.utilities.EventObserver
import com.mungeun.gymforyou.utilities.autoCleared
import com.mungeun.gymforyou.utilities.preference.PreferenceManger
import com.mungeun.gymforyou.widget.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(), MapView.MapViewEventListener, MapView.POIItemEventListener,
    MapView.CurrentLocationEventListener {
    private val viewModel: HomeViewModel by viewModels()
    private var mBinding by autoCleared<FragmentHomeBinding>()
    private lateinit var mapView: MapView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private lateinit var currentMapPoint: MapPoint
    private lateinit var mapViewContainer: ViewGroup

    private var permissinLocation = false

    // 의존성 주입
    @Inject
    lateinit var preferenceManger: PreferenceManger


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var adapter = HomeAdapter()
        mBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@HomeFragment
            vm = viewModel
            recyclerviewGym.adapter = adapter
        }

        with(mBinding) {
            button.setOnClickListener {
                mBinding.hasCardView = false
                BottomSheetBehavior.from(mBinding.bottomSheet).state =
                    BottomSheetBehavior.STATE_HALF_EXPANDED
            }
            appbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_home_alarm -> {
                        findNavController().navigate(R.id.alarmFragment)
                        true
                    }
                    R.id.action_my_location -> {
//                        permissinLocation = !permissinLocation

                        if (permissinLocation) {

                        }
//                                MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading else
//                                MapView.CurrentLocationTrackingMode.TrackingModeOff
                        true
                    }
                    else -> throw IndexOutOfBoundsException()
                }
            }
        }

        with(viewModel) {
            goGymDetail.observe(viewLifecycleOwner, EventObserver {
                if (it) {
                    val direction = HomeFragmentDirections.actionHomeFragmentToGymDetailFragment(
                        mBinding.gym as Gym
                    )
                    findNavController().navigate(direction)
                }
            })

            gymList.observe(viewLifecycleOwner, {
                adapter.submitList(it)
                showMapViewMarker(it)
            })


        }

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = MapView(activity)
        mapViewContainer = mBinding.mapView as ViewGroup


        //mapView.setMapCenterPoint(currentMapPoint, true)
        mapViewContainer.addView(mapView)
        // 현재위치 이동

        enableMyLocation(true)
        if (permissinLocation) mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading

        bottomSheetBehavior = BottomSheetBehavior.from(mBinding.bottomSheet)

        val marker = MapPOIItem()
        marker.apply {
            itemName = "서울헬스장"
            mapPoint = MapPoint.mapPointWithGeoCoord(
                37.565949,
                126.978023
            )
            markerType = MapPOIItem.MarkerType.RedPin
        }
        mapView.addPOIItem(marker)

        // kakao map 관련 리스너 등록록
        mapView.setMapViewEventListener(this)
        mapView.setPOIItemEventListener(this)
        mapView.setCurrentLocationEventListener(this)


    }

    override fun onStop() {
        mapViewContainer.removeView(mapView)
        BottomSheetBehavior.from(mBinding.bottomSheet).state =
            BottomSheetBehavior.STATE_HIDDEN
        super.onStop()
    }

    override fun onCurrentLocationUpdate(
        mapView: MapView?,
        currentLocation: MapPoint,
        accuracyInMeters: Float
    ) {
        val mapPointGeo = currentLocation.mapPointGeoCoord
        currentMapPoint = MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude)
        //이 좌표로 지도 중심 이동

    }

    override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {

    }

    override fun onCurrentLocationUpdateFailed(p0: MapView?) {

    }

    override fun onCurrentLocationUpdateCancelled(p0: MapView?) {

    }

    override fun onMapViewInitialized(p0: MapView?) {
        Log.d("", "")
    }

    override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {
        Log.d("", "")
    }

    override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {
        Log.d("", "")
    }

    override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {
        Log.d("", "")
    }

    override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {
        Log.d("", "")
    }

    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {
        Log.d("", "")
    }

    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {
        mBinding.hasCardView = false
    }

    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {
        Log.d("", "")
    }

    override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {
        Log.d("", "")
    }

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        mBinding.hasCardView = true
        if (p1 != null) {
            mBinding.gym = Gym(
                _id = "",
                address = Address("", "", ""),
                description = "",
                homepage = "",
                location = Location(0.toDouble(), 0.toDouble()),
                name = p1.itemName,
                thumbnail = "헬스, 스쿼시가 가능한 전문 스포츠 센터입니다!!",
            )
        }

    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
        Log.d("", "")
    }

    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?,
    ) {
        Log.d("", "")
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
        Log.d("", "")
    }

    private fun showMapViewMarker(gymList: List<Gym>) {
        // 맵뷰 객체 생성
        gymList.forEach {

            val marker = MapPOIItem()
            marker.apply {
                itemName = it.name
                mapPoint = MapPoint.mapPointWithGeoCoord(
                    it.location.latitude,
                    it.location.longitude
                )
                markerType = MapPOIItem.MarkerType.RedPin
            }
            mapView.addPOIItem(marker)


        }

    }

    // 권한 확인 권한 없을 시 권한 요청
    private fun enableMyLocation(requestPermission: Boolean = false) {
        val context = context ?: return
        when {
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED -> {
                permissinLocation = true
            }
            requestPermission -> requestLocationPermission()
            else -> {
            }
        }
    }

    private fun requestLocationPermission() {
        val context = context ?: return
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        // 권한이 필요한 이유를 설명
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            MyLocationRationaleFragment()
                .show(childFragmentManager, "my_location_rationale")
            return
        }
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            1
        )
    }

    class MyLocationRationaleFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return MaterialAlertDialogBuilder(requireContext())
                .setMessage("ssasa")
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    requireParentFragment().requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        1
                    )
                }
                .setNegativeButton(android.R.string.cancel, null) // Give up
                .create()
        }
    }

}
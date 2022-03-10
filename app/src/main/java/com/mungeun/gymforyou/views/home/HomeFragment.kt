package com.mungeun.gymforyou.views.home


import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.mungeun.domain.model.gym.Gym
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


//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//    }
//
//    override fun onStart() {
//        super.onStart()
//    }
//
//    override fun onResume() {
//        super.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//    }
//
//
//    override fun onDestroyView() {
//        super.onDestroyView( )
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//    }


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
            //appbar 클릭 이벤트리스너
            appbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_home_alarm -> {
                        val directions = HomeFragmentDirections.actionHomeFragmentToAlarmFragment()
                        findNavController().navigate(directions)
                        true
                    }
                    R.id.action_my_location -> {
                        if (permissinLocation) {
                            this@HomeFragment.mapView.setMapCenterPoint(currentMapPoint, true)
                        } else {
                            enableMyLocation(true)
                        }
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

        mapView = MapView(activity)
        mapViewContainer = mBinding.mapView


        mapViewContainer.addView(mapView)
        // 현재위치 이동
        enableMyLocation(true)
        onTrackingMode()

        bottomSheetBehavior = BottomSheetBehavior.from(mBinding.bottomSheet)

        // kakao map 관련 리스너 등록록
        mapView.setMapViewEventListener(this)
        mapView.setPOIItemEventListener(this)
        mapView.setCurrentLocationEventListener(this)


        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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

    // 마커 클릭 시
    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        var list = viewModel.gymList.value
        if (p1 != null) {
            if (list != null) {
                if (list.find { it.name == p1.itemName } != null) {
                    var gym = list?.let { it -> it.find { it.name == p1.itemName } } as Gym
                    mBinding.hasCardView = true
                    with(gym) {
                        mBinding.gym = Gym(
                            _id = _id,
                            address = address,
                            description = description,
                            homepage = homepage,
                            location = location,
                            name = name,
                            thumbnail = thumbnail,
                        )
                    }
                    return
                }

            }

        }
        mBinding.hasCardView = false
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

    private fun onTrackingMode(){
        if (permissinLocation) mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
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
                markerType = MapPOIItem.MarkerType.CustomImage
                customImageResourceId = R.drawable.ic_marker_location
                setCustomImageAnchor(0.5f, 1.0f)
                marker.isShowCalloutBalloonOnTouch = false
            }

            mapView.addPOIItem(marker)


        }

    }

    // 권한 확인 권한 없을 시 권한 요청
    private fun enableMyLocation(requestPermission: Boolean = false) {
        val context = context ?: return
        if (REQUIRED_PERMISSIONS.all { permission ->
                ContextCompat.checkSelfPermission(context, permission) ==
                        PackageManager.PERMISSION_GRANTED
            }) {
//            Snackbar.make(mBinding.root, "권한 승인됨", Snackbar.LENGTH_SHORT)
//                .show()
            permissinLocation = true
        } else {
            requestPermissionLauncher.launch(REQUIRED_PERMISSIONS) // requestLocationPermission()
        }
    }

    /**
     * activity:1.2.0 이후의 권한 획득 방법
     **/
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach { permission ->
                when {
                    permission.value -> {
                        Snackbar.make(mBinding.root, "권한 승인됨", Snackbar.LENGTH_SHORT)
                            .show()
                        permissinLocation = true
                        onTrackingMode()
                    }
                    shouldShowRequestPermissionRationale(permission.key) -> {
                        Snackbar.make(
                            mBinding.root,
                            "앱 기동을 위해서는 권한이 필요합니다.",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        enableMyLocation(false)
                    }
                    else -> {
                        Snackbar.make(mBinding.root, "Permission denied", Snackbar.LENGTH_SHORT)
                            .show()
                        openSettings()
                    }

                }
            }
        }

    private fun openSettings() {
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            data = Uri.fromParts("package", requireContext().packageName, null)
        }.run(::startActivity)
    }


//    private fun requestLocationPermission() {
//        val context = context ?: return
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
//            PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        // 권한이 필요한 이유를 설명
//        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
//            MyLocationRationaleFragment()
//                .show(childFragmentManager, "my_location_rationale")
//            return
//        }
//        requestPermissions(
//            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//            1
//        )
//    }


    class MyLocationRationaleFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return MaterialAlertDialogBuilder(requireContext())
                .setMessage("권한요청")
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

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    }
}
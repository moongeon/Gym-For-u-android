package com.mungeun.gymforyou.views.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kakao.util.maps.helper.Utility
import com.mungeun.gymforyou.R
import com.mungeun.gymforyou.databinding.FragmentHomeBinding
import com.mungeun.gymforyou.domain.model.gym.Address
import com.mungeun.gymforyou.domain.model.gym.Gym
import com.mungeun.gymforyou.domain.model.gym.Location
import com.mungeun.gymforyou.utilities.EventObserver
import com.mungeun.gymforyou.utilities.preference.PreferenceManger
import com.mungeun.gymforyou.widget.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), MapView.MapViewEventListener, MapView.POIItemEventListener {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mapView: MapView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    @Inject
    lateinit var preferenceManger: PreferenceManger

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        var adapter = HomeAdapter()
        mBinding.apply {
            lifecycleOwner = this@HomeFragment
            vm = viewModel
            recyclerviewGym.adapter = adapter
        }

        val a = preferenceManger.refreshToken

        mBinding.button.setOnClickListener {
            mBinding.hasCardView = false
            BottomSheetBehavior.from(mBinding.bottomSheet).state =
                BottomSheetBehavior.STATE_HALF_EXPANDED
        }
        with(viewModel) {
            goAlarm.observe(viewLifecycleOwner, EventObserver {
                if (it) {
                    findNavController().navigate(R.id.alarmFragment)
                }
            })

            goGymDetail.observe(viewLifecycleOwner, EventObserver {
                if (it) {
                    findNavController().navigate(R.id.gymDetailFragment)
                }
            })


            gymList.observe(viewLifecycleOwner, EventObserver {
                adapter.submitList(it)
                showMapViewMarker(it)

            })


        }


        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = MapView(activity)
        val mapViewContainer = mBinding.mapView as ViewGroup
        mapViewContainer.addView(mapView)


        val marker = MapPOIItem()
        marker.apply {
            itemName = "서울시청"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.568117, 126.976578)
            markerType = MapPOIItem.MarkerType.RedPin
        }
        mapView.addPOIItem(marker)


        bottomSheetBehavior = BottomSheetBehavior.from(mBinding.bottomSheet)


//        val markerBehavior = object : MapView.POIItemEventListener {
//            override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
//                Log.d("", "")
//            }
//
//            override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
//                Log.d("", "")
//            }
//
//            override fun onCalloutBalloonOfPOIItemTouched(
//                p0: MapView?,
//                p1: MapPOIItem?,
//                p2: MapPOIItem.CalloutBalloonButtonType?,
//            ) {
//                Log.d("", "")
//            }
//
//            override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
//                Log.d("", "")
//            }
//
//
//        }
        mapView.setMapViewEventListener(this)
        mapView.setPOIItemEventListener(this)


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
        Log.d("", "")
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

}
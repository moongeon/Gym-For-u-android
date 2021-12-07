package com.mungeun.gymforyou.views.gym_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mungeun.gymforyou.R
import com.mungeun.gymforyou.databinding.FragmentGymDetailBinding
import com.mungeun.gymforyou.views.gym_detail.home.GymDetailHomeFragment
import com.mungeun.gymforyou.views.gym_detail.review.ReviewFragment
import com.mungeun.gymforyou.views.see_more.SeeMoreFragment
import dagger.hilt.android.AndroidEntryPoint

data class test(val a: String)

@AndroidEntryPoint
class GymDetailFragment
    : Fragment() {

    private lateinit var mBinding: FragmentGymDetailBinding
    private val viewModel: GymDetailViewModel by viewModels()
    private val gymDetail: GymDetailFragmentArgs by navArgs()
    private val dataSet = arrayListOf<test>().apply {
        add(test("https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1638101071/noticon/gpr07ptl1x6evhew7li7.png"))
        add(test("https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1593397832/noticon/xmudzlguiuwsxfi3wjkj.png"))

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var gymImagePagerAdapter = GymImagePagerAdapter()
        mBinding = FragmentGymDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@GymDetailFragment
            vm = viewModel
            vpViewpager.adapter = gymImagePagerAdapter

        }
        mBinding.tvViewpagerNum.text = (1).toString() + "/" + dataSet.size.toString()
        mBinding.vpViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.tvViewpagerNum.text = (position + 1).toString() + "/" + dataSet.size.toString()

            }
        })
        gymImagePagerAdapter.submitList(dataSet)

        with(mBinding) {
            viewpager.offscreenPageLimit = INFO_PAGES.size
            viewpager.adapter = InfoAdapter(this@GymDetailFragment)

            TabLayoutMediator(tabs, viewpager) { tab, position ->
                tab.text = resources.getString(INFO_TITLES[position])
            }.attach()

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }

        return mBinding.root
    }

    /**
     * Adapter that builds a page for each info screen.
     */
    inner class InfoAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
//    override fun createFragment(position: Int) = INFO_PAGES[position].invoke()

        override fun createFragment(position: Int): Fragment {
            if (position == 0) {
                var fragment = GymDetailHomeFragment()
                fragment.arguments = Bundle().apply {
                    putString("gymDescription", gymDetail.gymData.description)

                }
                return fragment
            } else if (position == 1) {
                return SeeMoreFragment()
            } else if (position == 2) {
                return ReviewFragment()
            } else {
                return SeeMoreFragment()
            }

        }


        override fun getItemCount() = INFO_PAGES.size

    }

    companion object {

        private val INFO_TITLES = arrayOf(
            R.string.home_title,
            R.string.notice_title,
            R.string.review_title
        )
        private val INFO_PAGES = arrayOf(
            { GymDetailHomeFragment() },
            { SeeMoreFragment() },
            { ReviewFragment() }
        )
    }


}
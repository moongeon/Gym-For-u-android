package com.mungeun.gymforyou.views.gym_detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mungeun.gymforyou.R
import com.mungeun.gymforyou.databinding.FragmentGymDetailBinding
import com.mungeun.gymforyou.utilities.autoCleared
import com.mungeun.gymforyou.views.gym_detail.chat.ChatFragment
import com.mungeun.gymforyou.views.gym_detail.home.GymDetailHomeFragment
import com.mungeun.gymforyou.views.gym_detail.review.ReviewFragment
import dagger.hilt.android.AndroidEntryPoint

data class test(val a: String)

@AndroidEntryPoint
class GymDetailFragment
    : Fragment() {

    private var mBinding by autoCleared<FragmentGymDetailBinding>()
    private val viewModel: GymDetailViewModel by viewModels()
    private val gymDetail: GymDetailFragmentArgs by navArgs()
    private val dataSet = arrayListOf<test>().apply {
        add(test("https://s3.us-west-2.amazonaws.com/secure.notion-static.com/995f78de-9aeb-4a2d-adb8-11eec1575cb4/KakaoTalk_20211211_000045154.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211210%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211210T150545Z&X-Amz-Expires=86400&X-Amz-Signature=9e272af307c323a3ebe00c811a5fde50da18c89778f3e8191dc9b61e08b656e8&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22KakaoTalk_20211211_000045154.jpg%22&x-id=GetObject"))
        add(test("https://s3.us-west-2.amazonaws.com/secure.notion-static.com/51f80268-9ea4-4493-af37-db9ca9faed01/KakaoTalk_20211210_235831362.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211210%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211210T150149Z&X-Amz-Expires=86400&X-Amz-Signature=2809f70d6f38e7877b4c80d40179329dd7edae483578c9f1ca4132ccb2a3c64b&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22KakaoTalk_20211210_235831362.jpg%22&x-id=GetObject"))
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
            var isToolbarShown = false
            // scroll change listener begins at Y = 0 when image is fully collapsed
            scrollviewGymDetail.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                    // User scrolled past image to height of toolbar and the title text is
                    // underneath the toolbar, so the toolbar should be shown.
                    val shouldShowToolbar = scrollY > toolbar.height

                    // The new state of the toolbar differs from the previous state; update
                    // appbar and toolbar attributes.
                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar

                        // Use shadow animator to add elevation if toolbar is shown
                        appbar.isActivated = shouldShowToolbar

                        // Show the plant name if toolbar is shown
                        toolbarLayout.isTitleEnabled = shouldShowToolbar
                    }
                }
            )
        }

        gymImagePagerAdapter.submitList(dataSet)

        with(mBinding) {
            viewpager.offscreenPageLimit = INFO_PAGES.size
            viewpager.adapter = InfoAdapter(this@GymDetailFragment)

            TabLayoutMediator(tabs, viewpager) { tab, position ->
                tab.text = resources.getString(INFO_TITLES[position])
            }.attach()


            tvViewpagerNum.text = (1).toString() + "/" + dataSet.size.toString()
            vpViewpager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    tvViewpagerNum.text =
                        (position + 1).toString() + "/" + dataSet.size.toString()

                }
            })


            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_share -> {
                        createShareIntent()
                        true
                    }
                    else -> false

                }

            }


        }

        return mBinding.root
    }

    private fun createShareIntent() {
        val shareText = viewModel.gymName.value.let {
            if (it == null) {
                ""
            } else {
                getString(R.string.share_gym_detail, it)
            }
        }
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(shareIntent)


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
                return ChatFragment()
            } else if (position == 2) {
                return ReviewFragment()
            } else {
                throw IndexOutOfBoundsException()
            }

        }


        override fun getItemCount() = INFO_PAGES.size

    }

    companion object {

        private val INFO_TITLES = arrayOf(
            R.string.home_title,
            R.string.chat_title,
            R.string.review_title
        )
        private val INFO_PAGES = arrayOf(
            { GymDetailHomeFragment() },
            { ChatFragment() },
            { ReviewFragment() }
        )
    }


}
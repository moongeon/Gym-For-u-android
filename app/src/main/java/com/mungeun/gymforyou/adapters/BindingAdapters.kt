package com.mungeun.gymforyou.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mungeun.gymforyou.R

@BindingAdapter("imageCircleFromUrl")
fun bindImageCircleFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(view)

    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .apply(RequestOptions.fitCenterTransform())
            .into(view)

    }
}




@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (!isGone) View.GONE else View.VISIBLE
}

// 약관 동의 여부에 따른 배경색
@BindingAdapter("onAgreementBackground")
fun TextView.setOnNextBackground(isCompleted: Boolean) {
    if (!isCompleted) {
        background =
            ContextCompat.getDrawable(context, R.drawable.bg_btn_solid_light_gray_ebebeb_radius_8dp)
        setTextColor(ContextCompat.getColor(context, R.color.white_FFFFFF))
    } else {
        background =
            ContextCompat.getDrawable(context, R.drawable.bg_btn_normal_orange_radius_8dp)
        setTextColor(ContextCompat.getColor(context, R.color.white_FFFFFF))
    }
}
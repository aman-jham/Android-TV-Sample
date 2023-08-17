package com.android.tv.sample.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.android.tv.sample.R
import com.android.tv.sample.data.local.CastResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CastItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {

        val view = LayoutInflater.from(parent!!.context)
            .inflate(R.layout.cast_item_view, parent, false)

      /*  val params = view.layoutParams
        params.width = getWidthInPercent(parent.context, 15)
        params.height = getHeightInPercent(parent.context, 21)*/

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val content = item as CastResponse.Cast

        val imageview = viewHolder!!.view.findViewById<ImageView>(R.id.cast_img)

        val path = "https://www.themoviedb.org/t/p/w780" + content.profile_path
        Glide.with(viewHolder.view.context)
            .load(path)
            .apply(RequestOptions.circleCropTransform())
            .into(imageview)

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }
}
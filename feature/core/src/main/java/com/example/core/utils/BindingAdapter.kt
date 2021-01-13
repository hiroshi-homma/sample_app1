package com.example.core.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kotlin.project.data.model.MyNewsStatus

@BindingAdapter("visibleGone")
fun visibleGone(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleInvisible")
fun visibleInvisible(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.VISIBLE
}

@BindingAdapter("isRefresh")
fun SwipeRefreshLayout.isRefresh(timeLineStatus: MyNewsStatus?) {
    timeLineStatus ?: return
    isRefreshing = timeLineStatus == MyNewsStatus.RELOADING
}

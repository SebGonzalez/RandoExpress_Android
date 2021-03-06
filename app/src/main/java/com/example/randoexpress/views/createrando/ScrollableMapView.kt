package com.example.randoexpress.views.createrando

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapView

/**
 * Custom map view that supports scrolling inside ScrollView
 */
class ScrollableMapView : MapView {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}
    constructor(context: Context?, options: GoogleMapOptions?) : super(context, options) {}

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        /**
         * Request all parents to relinquish the touch events
         */
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(ev)
    }


}
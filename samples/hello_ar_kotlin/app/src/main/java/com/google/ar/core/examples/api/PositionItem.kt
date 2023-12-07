/*

    Author: Alex Mariano
    Dr. Dave Johannsen
    CS 4750 Mobile Application Development

 */

package com.broncospace.android.starvis.api

import android.net.Uri
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

private const val API_KEY = "89PAUZ-9G5ZB6-49ZGHR-55XX"
@JsonClass(generateAdapter = true)
data class PositionItem(
    val satlatitude: Float,
    val satlongitude: Float,
    val sataltitude: Float,
    val azimuth: Float,
    val elevation: Float,
    val ra: Float,
    val dec: Float,
    val timestamp: Float,
)
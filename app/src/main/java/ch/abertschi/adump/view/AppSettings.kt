package ch.abertschi.adump.view

import android.content.Context
import android.graphics.Typeface

/**
 * Created by abertschi on 21.04.17.
 */

class AppSettings private constructor(val context: Context) {

    var typeFace: Typeface = Typeface.createFromAsset(context.assets, "fonts/Raleway-ExtraLight.ttf")

    companion object {
        val AD_FREE_RESOURCE_ADRESS: String = "https://github.com/abertschi/ad-free-resources/blob/master/"

        var _instance: AppSettings? = null

        fun instance(context: Context): AppSettings {
            if (_instance == null) {
                _instance = AppSettings(context)
            }
            return _instance!!
        }
    }
}

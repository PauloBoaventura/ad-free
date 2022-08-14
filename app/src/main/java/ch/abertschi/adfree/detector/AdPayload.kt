/*
 * Ad Free
 * Copyright (c) 2017 by abertschi, www.abertschi.ch
 * See the file "LICENSE" for the full license governing this code.
 */

package ch.abertschi.adfree.detector

import android.service.notification.StatusBarNotification

/**
 * Created by abertschi on 15.04.17.
 */

data class AdPayload(val statusbarNotification: StatusBarNotification) {

    /**
     * Keys set by implementations of AdDetectable to exclude tracks from being matched as ad
     */
    val ignoreKeys: ArrayList<String> = ArrayList()

    val matchedPackageKeys: ArrayList<String> = ArrayList()
}
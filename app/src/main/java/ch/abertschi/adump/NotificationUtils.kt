package ch.abertschi.adump

import android.app.IntentService
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


/**
 * Created by abertschi on 16.04.17.
 */
class NotificationUtils : AnkoLogger {

    companion object {
        val actionDismiss = "actionDismiss"
        val blockingNotificationId = 1

        private val actionDismissCallables: ArrayList<() -> Unit> = ArrayList()

    }

    fun showBlockingNotification(context: Context, dismissCallable: () -> Unit) {

// As long as NotificationActionDetector works reliably, no need to filter out false positives
//        val ignoreIntent = Intent(context
//                , NotificationInteractionService::class.java).setAction(actionIgnore).putExtra(ignoreIntentExtraKey, ignoreKeys)
//
//        val ignoreAction = NotificationCompat.Action.Builder(0, "Do not block this again",
//                PendingIntent.getService(context, 0, ignoreIntent
//                        , PendingIntent.FLAG_ONE_SHOT)).build()

        val dismissIntent = PendingIntent
                .getService(context, 0, Intent(context
                        , NotificationInteractionService::class.java).setAction(actionDismiss)
                        , PendingIntent.FLAG_ONE_SHOT)


        val notification = NotificationCompat.Builder(context)
                .setContentTitle("Blocking advertisement")
                .setContentText("Touch to unmute")
                .setSmallIcon(R.mipmap.icon)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(dismissIntent)
                .build()

        synchronized(actionDismissCallables) {
            actionDismissCallables.add(dismissCallable)
        }
        val manager = NotificationManagerCompat.from(context)
        manager.notify(blockingNotificationId, notification)
    }

    fun hideBlockingNotification(context: Context) {
        val manager = NotificationManagerCompat.from(context)
        manager.cancel(blockingNotificationId)
    }

    class NotificationInteractionService : IntentService(NotificationInteractionService::class.simpleName), AnkoLogger {
        init {
            info("NotificationInteractionService created")
        }

        override fun onHandleIntent(intent: Intent?) {
            if (intent == null || intent.action == null) {
                return
            }
            val actionKey: String = intent!!.action
            if (actionKey.equals(NotificationUtils.actionDismiss)) {
                synchronized(NotificationUtils.actionDismissCallables) {
                    actionDismissCallables.forEach {
                        it()
                        info { "CALLING DISMISS CALLABLES" }
                    }
                    actionDismissCallables.clear()
                }
            }
        }

    }

//
//    else if (actionKey.equals(NotificationUtils.actionIgnore)) {
//        AudioController.instance.unmuteMusicStream(this)
//        utils.hideBlockingNotification(this)
//
//        val ignoreKeys: List<String>? = intent.getStringArrayListExtra(NotificationUtils.ignoreIntentExtraKey)
//        if (ignoreKeys != null && ignoreKeys.size > 0) {
//            ignoreKeys.forEach {
//                trackRepository.addTrack(it)
//            }
//        }
//    }
}



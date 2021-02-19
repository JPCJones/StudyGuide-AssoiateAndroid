package com.example.studyguide_assoiateandroid.AndroidCore;

public class ApplicationFundamentals {
    //more info - https://developer.android.com/guide/components/fundamentals
    /*
    There are four different types of app components:

        Activities -
        Services(consider using jobScheduler instead) - https://developer.android.com/guide/components/services
        Broadcast receivers - https://developer.android.com/reference/android/content/BroadcastReceiver
        Content providers - https://developer.android.com/guide/topics/providers/content-providers

        two kinds of services - started services(think music work), bound services(the system or another app can request to use)

        ************************************************************************************************************
        Activating components

    intent - An intent is created with an Intent object,
        which defines a message to activate either a specific component
        (explicit intent) or a specific type of component (implicit intent).

        There are separate methods for activating each type of component:

   activity - You can start an activity or give it something new to do by passing an Intent to startActivity()
         or startActivityForResult() (when you want the activity to return a result).

   services - With Android 5.0 (API level 21) and later, you can use the JobScheduler class to schedule actions.
        For earlier Android versions, you can start a service (or give new instructions to an ongoing service)
        by passing an Intent to startService(). You can bind to the service by passing an Intent to bindService().

   broadcast - You can initiate a broadcast by passing an Intent to methods such as
         sendBroadcast(), sendOrderedBroadcast(), or sendStickyBroadcast().

   content provider - You can perform a query to a content provider by calling query() on a ContentResolver.

             ********************************************************************************************************
             Manifest

   You must declare all app components using the following elements:

<activity> elements for activities.
<service> elements for services.
<receiver> elements for broadcast receivers.
<provider> elements for content providers.

When you declare an activity in your app's manifest, you can optionally include intent filters that declare the capabilities
 of the activity so it can respond to intents from other apps. You can declare an intent filter for your component
  by adding an <intent-filter> element as a child of the component's declaration element.
         </intent-filter>
            <action android:name="android.intent.action.SEND" /> - the intents the activity will respond to
                <data android:type="TYPE OF DATA HERE" /> - the type of data
                <category android:name="android.intent.category.DEFAULT" />
          </intent-filter>

         ***** intent and intent filter info - https://developer.android.com/guide/components/intents-filters ********

         ********************
         Declaring app requirements

         declare that the app needs a camera in manifest
         <uses-feature android:name="android.hardware.camera.any" android:required="true" />
     */
}

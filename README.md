# Runtime-Resource-overlay-Target-Package
Simple implementation of Sony's Runtime Resource overlay framework for android

This is a target package. you can find the overlay package [here](https://github.com/pratik98/Runtime-Resource-overlay-Overlay-Package)

[Read More about Sony's RRO] (http://developer.sonymobile.com/2014/04/22/sony-contributes-runtime-resource-overlay-framework-to-android-code-example/)


 Step to install and uninstall overlay packages.
 ====================================================================================


 * Download target package apk file.

 * Install Target APK file. (will be installed in /data/app/)

 * connect the phone via USB cable

 * run ADB devices to see whether the Device is detected by ADB or not.

 * run "ADB root"

 * run "ADB remount" (would remount the system partition)

 * download overlay package apk file from [here](https://github.com/pratik98/Runtime-Resource-overlay-Overlay-Package/blob/master/dev-world-overlay.apk)

 * run ADB push "overlay_package_name.apk" /system/vendor/overlay (this will install overlay package)

 * restart the phone

 * you will be able to see the changes

 * now uninstall the overlay package from mobile. (changes are still not reverting )

 * run "rm overlay_package_name.apk" from adb shell

 * the changes would be reverted.

note: you would not be able to install overlay package to system partition if you don't have root access.
OpenXC - Dash
==============

Library to provide Dash measurements via the [OpenXC](https://github.com/openxc/openxc-android) project.  This project provides
an implementation of the `OpenXC` interface [VehicleDataSource](https://github.com/openxc/openxc-android/blob/master/openxc/src/com/openxc/sources/VehicleDataSource.java)
which provides [Measurement](https://github.com/openxc/openxc-android/blob/master/openxc/src/com/openxc/measurements/Measurement.java)
values for diagnostic information produced by a running Dash service.

Compilation
-----------

Just like [openxc-android](https://github.com/openxc/openxc-android), the build requires [Maven](http://maven.apache.org/download.html)
v3.0.3+ and the [Android SDK](http://developer.android.com/sdk/index.html)
to be installed in your development environment. In addition you'll need to set the `ANDROID_HOME` environment variable to the location of your SDK:

    export ANDROID_HOME=/opt/tools/android-sdk

After satisfying those requirements, run the standard maven command:

    mvn clean install

Setup
------



Usage
------

To use the `DashDataSource` install it like any other `OpenXC` [VehicleDataSource](https://github.com/openxc/openxc-android/blob/master/openxc/src/com/openxc/sources/VehicleDataSource.java) implementation.
See the [OpenXC API Guide](http://openxcplatform.com/android/api-guide.html) for more information.
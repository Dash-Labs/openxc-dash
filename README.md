OpenXC - Dash
==============

Library to provide Dash measurements via the [OpenXC](https://github.com/openxc/openxc-android) project.  This project provides
an implementation of the `OpenXC` interface [VehicleDataSource](https://github.com/openxc/openxc-android/blob/master/openxc/src/com/openxc/sources/VehicleDataSource.java)
which provides [Measurement](https://github.com/openxc/openxc-android/blob/master/openxc/src/com/openxc/measurements/Measurement.java)
values for diagnostic information produced by a running Dash service.

Compilation
-----------

Dash uses [ply](http://github.com/blangel/ply) as its build tool, ensure you have it installed. Then run the following
command:

    $ ply clean install

Setup
------



Usage
------

To use the `DashDataSource` install it like any other `OpenXC` [VehicleDataSource](https://github.com/openxc/openxc-android/blob/master/openxc/src/com/openxc/sources/VehicleDataSource.java) implementation.
See the [OpenXC API Guide](http://openxcplatform.com/android/api-guide.html) for more information.
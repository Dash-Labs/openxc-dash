package com.openxc.dash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.openxc.remote.RawMeasurement;
import com.openxc.sources.BaseVehicleDataSource;
import com.openxc.sources.ContextualVehicleDataSource;
import com.openxc.sources.SourceCallback;

/**
 * User: blangel
 * Date: 3/11/13
 * Time: 1:34 PM
 *
 * Listens to broadcasts from the {@literal Dash} service as a {@link com.openxc.sources.VehicleDataSource} and
 * processes them through the {@literal OpenXC} pipeline.  The values will be sent as
 * {@literal com.openxc.measurements.DiagnosticTroubleCode} measurements.
 *
 * Users must request permission
 * <pre>
 *     <uses-permission android:name="com.dashlabs.dash.permission.DASH_BROADCAST"/>
 * </pre>
 * within their {@literal AndroidManifest.xml} file.
 */
public final class DashDataSource extends ContextualVehicleDataSource {

    /**
     * Listens for broadcasts initiated by the {@literal Dash} system.
     */
    private class Receiver extends BroadcastReceiver {

        @Override public void onReceive(Context context, Intent intent) {
            if (DIAGNOSTICS_INTENT.equals(intent.getAction())) {
                handleDTCValues(intent);
            }
        }

        private void handleDTCValues(Intent intent) {
            String[] dtcs = intent.getStringArrayExtra(EXTRA_STATE_NAME);
            if (dtcs == null) {
                return;
            }
            for (String dtc : dtcs) {
                handleMessage(new RawMeasurement("diagnostic_trouble_code", dtc));
            }
        }
    }

    /**
     * Intent name to receive dash broadcasts related to measurement reading/results.
     */
    private static final String DIAGNOSTICS_INTENT = "com.dashlabs.dash.android.DIAGNOSTICS";

    /**
     * Permission necessary to receive broadcasts for {@link Receiver}
     */
    private static final String PERMISSION = "com.dashlabs.dash.permission.DASH_BROADCAST";

    /**
     * Extra name within {@link Intent} which holds the dash measurement value.
     */
    private static final String EXTRA_STATE_NAME = "STATE";

    private Receiver receiver;

    public DashDataSource(Context context) {
        super(context);
    }

    public DashDataSource(SourceCallback callback, Context context) {
        super(callback, context);
    }

    @Override public void setCallback(SourceCallback callback) {
        super.setCallback(callback);
        start();
    }

    @Override public void stop() {
        super.stop();
        unregister();
    }

    private void unregister() {
        if (receiver != null) {
            getContext().unregisterReceiver(receiver);
        }
    }

    private void start() {
        unregister();
        IntentFilter broadcastReceiverIntent = new IntentFilter();
        broadcastReceiverIntent.addAction(DIAGNOSTICS_INTENT);
        receiver = new Receiver();
        getContext().registerReceiver(receiver, broadcastReceiverIntent, PERMISSION, new Handler());
    }
}

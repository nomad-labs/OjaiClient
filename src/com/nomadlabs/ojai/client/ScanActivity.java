package com.nomadlabs.ojai.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public abstract class ScanActivity extends Activity implements ScannerClientInterface {

	protected final String TAG = ScanActivity.class.getSimpleName();
	protected ScanServiceConnection connection;
    protected boolean serviceBound;

    private ScannerServiceListenerStub getScannerServiceListener() {
        return new ScannerServiceListenerStub(ScanActivity.this, this);
    }

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

        connection = (ScanServiceConnection) getLastNonConfigurationInstance();
        if (connection == null) {
            startService();
        } else {
            connection.setScannerServiceListener(getScannerServiceListener());
        }
        Log.d(TAG, "ScanActivity onCreate");
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();

        Log.d(TAG, "ScanActivity onDestroy");

		connection.releaseListener();
        if (isFinishing()) {
		    getApplicationContext().unbindService(connection);
        }
		connection = null;
	}

    @Override
    public Object onRetainNonConfigurationInstance() {
        return connection;
    }

    @Override
	protected void onResume() {
		super.onResume();

        Log.d(TAG, "ScanActivity onResume");

		connection.attachListener();
	}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("serviceBound", serviceBound);
    }

    @Override
	protected void onStart() {
		super.onStart();

        Log.d(TAG, "ScanActivity onStart");
	}

	@Override
	protected void onStop() {
		super.onStop();

        Log.d(TAG, "ScanActivity onStop");
		connection.releaseListener();
	}
	
	protected void startService() {

        connection = new ScanServiceConnection(getScannerServiceListener());
        Intent i = new Intent("com.nomadlabs.ojai.service.SCANNER");
        getApplicationContext().bindService(i, connection, BIND_AUTO_CREATE);
        Log.d(TAG, "ScanActivity starting ScanService");

	}
	
	@Override
	public void onScan(String data, Bundle extras) {
		Log.d(TAG, "onScan called in ScanActivity");
	}
	@Override
	public void onScannerAvailable() {
		
	}
	@Override
	public void onScannerUnavailable() {
		
	}
	@Override
	public void onConfigurationChanged(Bundle extras) {
		
	}

}

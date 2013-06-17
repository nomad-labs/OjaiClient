package com.nomadlabs.ojai.client;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;

import com.nomadlabs.ojai.ScannerServiceListener.Stub;

public class ScannerServiceListenerStub extends Stub {

	private static final String TAG = ScannerServiceListenerStub.class.getSimpleName();
	private Activity activity;
	private ScannerClientInterface iface;

	public ScannerServiceListenerStub(Activity activity, ScannerClientInterface iface) {
		this.activity = activity;
		this.iface = iface;
	}
	@Override
	public void onScan(final char[] data, final Bundle extras) throws RemoteException {

		byte[] bytes = new byte[data.length*2];
		for(int i=0; i < data.length; i++) {
		   bytes[i*2] = (byte) (data[i] >> 8);
		   bytes[i*2+1] = (byte) data[i];
		}
		
		extras.putByteArray("EXTRAS_SCAN_BYTES", bytes);

		activity.runOnUiThread(new Runnable(){
			@Override
			public void run(){
                String sData = new String(data);
				iface.onScan(sData, extras);
			}
		});
		
	}

	@Override
	public void onScannerAvailable() throws RemoteException {
		
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run(){
				iface.onScannerAvailable();
			}
		});
	}

	@Override
	public void onScannerUnavailable() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConfigurationChanged(Bundle extras) throws RemoteException {
		// TODO Auto-generated method stub

	}

}

package com.nomadlabs.ojai.client;

import com.nomadlabs.ojai.IScannerService;
import com.nomadlabs.ojai.ScannerMessage;
import com.nomadlabs.ojai.ScannerServiceListener;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
	 * This class represents the actual service connection. It casts the bound
	 * stub implementation of the service to the AIDL interface.
	 */
	public class ScanServiceConnection implements ServiceConnection {
		
		private final String TAG = ScanServiceConnection.class.getSimpleName();

		private IScannerService service;
		private ScannerServiceListener listener;
		
		
		public ScanServiceConnection(ScannerServiceListener listener){
			this.listener = listener;
		}

		public void onServiceConnected(ComponentName name, IBinder boundService) {
			service = IScannerService.Stub.asInterface((IBinder) boundService);
			attachListener();
			Log.d(TAG, "onServiceConnected() connected");
		}

		private void addListener(ScannerServiceListener lListener) {
			try {
				service.addListener(lListener);
			} catch (Throwable t) {
				Log.e(TAG, "Remote exception", t);
			}
		}
		
		public void attachListener(){
			if (service != null) {
				addListener(listener);
                Log.d(TAG, "Listener attached");
			}
		}
		
		public void releaseListener() {
			if (service != null) {
				removeListener(listener);
                Log.d(TAG, "Listener released");
			}
		}
		
		private void removeListener(ScannerServiceListener lListener) {
			try {
				service.removeListener(lListener);
			} catch (Throwable t) {
				Log.e(TAG, "Remote exception", t);
			}
		}

		public void onServiceDisconnected(ComponentName name) {
			service = null;
			Log.d(TAG, "onServiceDisconnected() - Service connection lost");
		}
		
		public void simulateScan(){
            simulateScan("", "");
		}

        public void simulateScan(String data, String symbology) {
            if (service != null) {
                try {
                    service.simulateScan(data.toCharArray(), symbology);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

		public void sendMessage(ScannerMessage message) throws RemoteException {
			service.sendMessage(message);
		}

        public void setScannerServiceListener(ScannerServiceListener listener) {
            releaseListener();
            this.listener = listener;
            attachListener();

        }
	}
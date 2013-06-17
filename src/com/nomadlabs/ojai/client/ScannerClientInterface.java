package com.nomadlabs.ojai.client;

import android.os.Bundle;

public interface ScannerClientInterface {
	void onScan(String data, Bundle extras);
	void onScannerAvailable();
	void onScannerUnavailable();
	void onConfigurationChanged(Bundle extras);
}

package com.nomadlabs.ojai;

interface ScannerServiceListener {
    void onScan(in char[] data, in Bundle extras);
    void onScannerAvailable();
    void onScannerUnavailable();
    void onConfigurationChanged(in Bundle extras);
}
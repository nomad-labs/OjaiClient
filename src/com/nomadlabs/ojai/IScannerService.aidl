package com.nomadlabs.ojai;

import com.nomadlabs.ojai.ScannerMessage;
import com.nomadlabs.ojai.ScannerServiceListener;

interface IScannerService {
    void addListener(ScannerServiceListener listener);
    void removeListener(ScannerServiceListener listener);
    void sendMessage(in ScannerMessage message);
    void simulateScan(in char[] data, in String symbology);
}
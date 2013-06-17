package com.nomadlabs.ojai.client;

public class Confirmation {

    public static final int BAD = 0;
    public static final int GOOD = 1;
	
	public static final int MODE_DEVICE = 1; //ISktScanProperty.values.confirmationMode.kSktScanDataConfirmationModeDevice;	
	public static final int MODE_SERVICE = 2; //ISktScanProperty.values.confirmationMode.kSktScanDataConfirmationModeScanAPI;	
	public static final int MODE_APP = 3; //ISktScanProperty.values.confirmationMode.kSktScanDataConfirmationModeApp;
	
	public static final int ACTION_BEEP = 0x1 << 0; //ISktScanProperty.values.localDecodeAction.kSktScanLocalDecodeActionBeep;
	public static final int ACTION_FLASH = 0x1 << 1; //ISktScanProperty.values.localDecodeAction.kSktScanLocalDecodeActionFlash;
	public static final int ACTION_RUMBLE = 0x1 << 2; //ISktScanProperty.values.localDecodeAction.kSktScanLocalDecodeActionRumble;

}

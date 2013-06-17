package com.nomadlabs.ojai.client;

import java.util.ArrayList;

import android.os.RemoteException;

import com.nomadlabs.ojai.ScannerMessage;


public class ScannerHelper {
	
	private static final boolean ENABLE = true;
	private static final boolean DISABLE = false;
	
	private ArrayList<ScannerMessage> messageQueue;
	private ScanServiceConnection connection;
	
	public ScannerHelper(ScanServiceConnection connection) {
		this.connection = connection;
		messageQueue = new ArrayList<ScannerMessage>();
	}
	public ScannerHelper disableSymbology(int index) {
		messageQueue.add(new ScannerMessage(ScannerMessage.SYMBOLOGY, index, false));
		return this;
	}
	public ScannerHelper enableSymbology(int index) {
		messageQueue.add(new ScannerMessage(ScannerMessage.SYMBOLOGY, index, true));
		return this;
	}
	public void flush() {
		for (ScannerMessage message : messageQueue) {
			try {
				connection.sendMessage(message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	private ScannerHelper setConfirmationAction(int action, boolean bool) {
		messageQueue.add(new ScannerMessage(ScannerMessage.CONFIRMATION_ACTION, action, bool));
		return this;
	}
	public ScannerHelper setConfirmationMode(int value){
		messageQueue.add(new ScannerMessage(ScannerMessage.CONFIRMATION_MODE, -1, value));
		return this;
	}
	public ScannerHelper setBeepOff() {
		return setConfirmationAction(Confirmation.ACTION_BEEP, DISABLE);
	}
	public ScannerHelper setBeepOn() {
		return setConfirmationAction(Confirmation.ACTION_BEEP, ENABLE);
	}
	public ScannerHelper setFlashOff() {
		return setConfirmationAction(Confirmation.ACTION_FLASH, DISABLE);
	}
	public ScannerHelper setFlashOn() {
		return setConfirmationAction(Confirmation.ACTION_FLASH, ENABLE);
	}
	public ScannerHelper setPostamble(String value) {
		messageQueue.add(new ScannerMessage(ScannerMessage.POSTAMBLE, value));
		return this;
	}
	public ScannerHelper setRumbleOff() {
		return setConfirmationAction(Confirmation.ACTION_RUMBLE, DISABLE);
	}
	public ScannerHelper setRumbleOn() {
		return setConfirmationAction(Confirmation.ACTION_RUMBLE, ENABLE);
	}
    public void confirmScan(int type) {
        messageQueue.add(new ScannerMessage(ScannerMessage.CONFIRM, -1, type));
    }
}

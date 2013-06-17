package com.nomadlabs.ojai;

import android.os.Parcel;
import android.os.Parcelable;

public class ScannerMessage implements Parcelable {

	public static final int METHOD_GET = 0;
	public static final int METHOD_SET = 1;
	
	// Gettable
	public static final int BLUETOOTH_ADDRESS = 0x1 << 0;
	public static final int FIRMWARE_VERSION = 0x1 << 1;
	public static final int RUMBLE_SUPPORT = 0x1 << 2;
	public static final int TYPE = 0x1 << 3;
	public static final int BATTERY_LEVEL = 0x1 << 4;
    public static final int CONFIRM = 0x1 << 5;
	
	// Settable
	public static final int NAME = 0x1 << 16;
	public static final int CONFIRMATION_ACTION = 0x1 << 17;
	public static final int CONFIRMATION_MODE = 0x1 << 18;
	public static final int POSTAMBLE = 0x1 << 19;
	public static final int SYMBOLOGY = 0x1 << 20;
	
	public static final int SCAN_API_VERSION = -0x1 << 2;
	
	private int method;
	private int property;
	private int subProperty;
	private boolean bValue;
	private int iValue;
	private String sValue;
	
	public static final Parcelable.Creator<ScannerMessage> CREATOR = new Parcelable.Creator<ScannerMessage>() {

		@Override
		public ScannerMessage createFromParcel(Parcel source) {
			return new ScannerMessage(source);
		}

		@Override
		public ScannerMessage[] newArray(int size) {
			return new ScannerMessage[size];
		}
	};
	
	public ScannerMessage(int property, int subProperty, boolean bool) {
		this.method = METHOD_SET;
		this.property = property;
		this.subProperty = subProperty;
		this.bValue = bool;
		
		this.sValue = "";
	}
	
	public ScannerMessage(int property, int subProperty, int value) {
		this.method = METHOD_SET;
		this.property = property;
		this.iValue = value;
		
		this.subProperty = -1;
		this.bValue = false;
	}
	
	public ScannerMessage(int property, String value) {
		this.method = METHOD_SET;
		this.property = property;
		this.sValue = value;
		
		this.subProperty = -1;
		this.bValue = false;
	}
	
	private ScannerMessage(Parcel source) {
		readFromParcel(source);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void readFromParcel(Parcel source) {
		method = source.readInt();
		property = source.readInt();
		subProperty = source.readInt();
		bValue = source.readByte() == 1;
		iValue = source.readInt();
		sValue = source.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(method);
		dest.writeInt(property);
		dest.writeInt(subProperty);
		dest.writeByte((byte) (bValue ? 1 : 0));
		dest.writeInt(iValue);
		dest.writeString(sValue);
	}

	public int getMethod() {
		return method;
	}
	
	public int getProperty() {
		return property;
	}

	public int getSubProperty() {
		return subProperty;
	}

	public boolean getBool() {
		return bValue;
	}
	
	public int getInt() {
		return iValue;
	}
	
	public String getString() {
		return sValue;
	}
}

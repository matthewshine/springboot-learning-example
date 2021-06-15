package com.kouryoushine.autoconfigdemo;

public enum HornetQMode {

	/**
	 * Connect to a broker using the native HornetQ protocol (i.e. netty).
	 */
	NATIVE,

	/**
	 * Embed (i.e. start) the broker in the application.
	 */
	EMBEDDED

}
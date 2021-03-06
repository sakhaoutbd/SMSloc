package de.pfeufferweb.android.whereru;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.telephony.SmsManager;

class LocationSender {
	private final Context context;
	private final Settings settings;

	public LocationSender(Context context) {
		this.context = context;
		settings = new Settings(context);
	}

	public void send(Location location, String phoneNumber) {
		sendSMS(format(location, R.string.locationResponse), phoneNumber);
	}

	public void sendNetwork(Location location, String phoneNumber) {
		sendSMS(format(location, R.string.locationResponseNetwork), phoneNumber);
	}

	public void sendNoLocation(String phoneNumber) {
		sendSMS(context.getString(R.string.locationUnknown), phoneNumber);
	}

	private void sendSMS(String message, final String receiver) {
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				new Intent(context, SmsReceiver.class), 0);
		SmsManager.getDefault().sendTextMessage(receiver, null, message,
				intent, null);
	}

	private String format(Location location, int text) {
		return context.getString(text,
				formatDegreeIfWanted(location.getLatitude()),
				formatDegreeIfWanted(location.getLongitude()),
				location.getAccuracy(), location.getSpeed(), getAge(location));
	}

	private String formatDegreeIfWanted(double d) {
		if (settings.getGoogleMaps()) {
			return new DegreeFormatter().format(d);
		} else {
			return Double.toString(d);
		}
	}

	private long getAge(Location location) {
		return (System.currentTimeMillis() - location.getTime()) / 1000;
	}

}

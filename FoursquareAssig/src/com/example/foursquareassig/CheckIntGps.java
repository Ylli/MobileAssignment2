package com.example.foursquareassig;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckIntGps
{
    private boolean haveConnectedWifi = false;
    private boolean haveConnectedMobile = false;
    
	public CheckIntGps() {
		// TODO Auto-generated constructor stub
	}
	
    public boolean isNetworkConnected(){
    	
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

	private ConnectivityManager getSystemService(String connectivityService) {
		// TODO Auto-generated method stub
		return null;
	}
}

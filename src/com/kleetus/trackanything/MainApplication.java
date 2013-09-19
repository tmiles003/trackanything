package com.kleetus.trackanything;


import android.app.Application;

import java.util.Date;

public class MainApplication extends Application {

    public static final boolean DEBUG = true;

    public static int getTime() {
        return (int) new Date().getTime();
    }


}

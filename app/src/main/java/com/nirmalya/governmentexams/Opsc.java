package com.nirmalya.governmentexams;

import android.app.Application;
import android.content.Context;

public class Opsc extends Application {

    private static Opsc opsc;
    private static Context context;

    public Opsc() {
    }

    public static Opsc getInstance() {
        if (opsc == null) {
            opsc = new Opsc();
        }
        return opsc;
    }
}

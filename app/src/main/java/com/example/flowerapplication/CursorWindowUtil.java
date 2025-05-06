package com.example.flowerapplication;

import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.CursorWindow;

public class CursorWindowUtil {
    public static void cw(Cursor cursor) {
        CursorWindow cw = new CursorWindow("test", 900000000);
        AbstractWindowedCursor ac = (AbstractWindowedCursor) cursor;
        ac.setWindow(cw);
    }
}

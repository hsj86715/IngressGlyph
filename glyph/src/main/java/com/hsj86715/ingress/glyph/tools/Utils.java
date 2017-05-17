package com.hsj86715.ingress.glyph.tools;

import android.graphics.Color;
import android.support.annotation.ColorInt;

/**
 * Created by hushujun on 2017/5/16.
 */

public class Utils {
    @ColorInt
    public static int stringToColor(String str) {
        final int nameH = str.hashCode();
        final int b = 255;
        final int g = b * 255;
        final int r = g * 255;
        final int a = r * 255;
        int ca = Math.abs(nameH / a);
        int cr = Math.abs(nameH % a / r);
        int cg = Math.abs(nameH % a % r / g);
        int cb = Math.abs(nameH % a % r % g / b);
//        Log.i("Utils", "stringToColor:name=$str,hashCode=$nameH,ca=$ca,cr=$cr,cg=$cg,cb=$cb")
        if (ca < 128) {
            ca += 128;
        }
        if (cb < 128) {
            cb = (int) (Math.sqrt(cb) * 16);
        }
        if (cr < 128) {
            cr = (int) (Math.sqrt(cr) * 16);
        }
        if (cg < 128) {
            cg = (int) (Math.sqrt(cg) * 16);
        }

        String sa = Integer.toHexString(ca);
        if (sa.length() < 2) {
            sa = "0" + sa;
        }
        String sr = Integer.toHexString(cr);
        if (sr.length() < 2) {
            sr = "0" + sr;
        }
        String sg = Integer.toHexString(cg);
        if (sg.length() < 2) {
            sg = "0" + sg;
        }
        String sb = Integer.toHexString(cb);
        if (sb.length() < 2) {
            sb = "0" + sb;
        }
        String colorStr = "#" + sa + sr + sg + sb;
//        Log.i("Utils", "stringToColor:colorStr=$colorStr")
        return Color.parseColor(colorStr);
    }

}

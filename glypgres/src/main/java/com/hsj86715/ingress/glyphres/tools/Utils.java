package com.hsj86715.ingress.glyphres.tools;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hushujun on 2017/5/16.
 */

public class Utils {
    static final Map<String, Integer> sColorCache = new HashMap<>();

    @ColorInt
    public static int stringToColor(String str) {
        if (TextUtils.isEmpty(str)) {
            return Color.BLUE;
        }
        if (sColorCache.containsKey(str)) {
            return sColorCache.get(str);
        }
        byte[] strB = str.getBytes();
//        System.out.println(str + ": " + Arrays.toString(strB));
        final int nameH = str.hashCode() * countBytes(strB);
        final int halfColor = 128;
        final int b = 255;
        final int g = b * 255;
        final int r = g * 255;
        final int a = r * 255;
        int ca = Math.abs(nameH / a);
        int cr = Math.abs(nameH % a / r);
        int cg = Math.abs(nameH % a % r / g);
        int cb = Math.abs(nameH % a % r % g / b);
        if (ca < halfColor) {
            ca = (int) (Math.sqrt(ca + halfColor) * 16);
        }
        if (cb < halfColor) {
            cb = (int) (Math.sqrt(cb + halfColor) * 16);
        }
        if (cr < halfColor) {
            cr = (int) (Math.sqrt(cr + halfColor) * 16);
        }
        if (cg < halfColor) {
            cg = (int) (Math.sqrt(cg + halfColor) * 16);
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
        int color = Color.parseColor(colorStr);
        sColorCache.put(str, color);
        System.out.println(str + " color: " + colorStr);
        return color;
    }

    private static int countBytes(byte[] strBytes) {
        int sum = 0;
        for (byte b : strBytes) {
            sum += b;
        }
        return sum;
    }

    public static String arrayToString(int[] array) {
        if (array == null || array.length == 0) {
            return "";
        }
        StringBuilder b = new StringBuilder();
        int max = array.length - 1;
        for (int i = 0; ; i++) {
            b.append(array[i]);
            if (i == max) {
                return b.toString();
            }
            b.append(", ");
        }
    }
}

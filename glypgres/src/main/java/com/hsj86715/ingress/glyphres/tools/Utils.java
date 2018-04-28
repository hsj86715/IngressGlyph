package com.hsj86715.ingress.glyphres.tools;

import android.text.TextUtils;

/**
 * Created by hushujun on 2017/5/16.
 */

public class Utils {

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
            b.append(",");
        }
    }

    public static int[] stringToArray(String path) {
        if (TextUtils.isEmpty(path) || !path.contains(",")) {
            return null;
        }
        String[] numbers = path.split(",");
        int[] arrayPath = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            try {
                arrayPath[i] = Integer.valueOf(numbers[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return arrayPath;
    }
}

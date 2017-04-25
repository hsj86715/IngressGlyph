package com.hsj86715.ingress.glyph.tools

import android.graphics.Color
import android.support.annotation.ColorInt
import android.util.Log

/**
 * Created by hushujun on 2017/4/25.
 */

object Utils {
    @ColorInt
    fun stringToColor(str: String): Int {
        val nameH = str.hashCode()
        val b = 255
        val g = b * 255
        val r = g * 255
        val a = r * 255
        var ca = Math.abs(nameH / a)
        var cr = Math.abs(nameH % a / r)
        var cg = Math.abs(nameH % a % r / g)
        var cb = Math.abs(nameH % a % r % g / b)
        Log.i("Utils", "stringToColor:name=$str,hashCode=$nameH,ca=$ca,cr=$cr,cg=$cg,cb=$cb")
        if (ca < 128) {
            ca += 128
        }
        if (cb < 128) {
            cb = (Math.sqrt(cb.toDouble()) * 16).toInt()
        }
        if (cr < 128) {
            cr = (Math.sqrt(cr.toDouble()) * 16).toInt()
        }
        if (cg < 128) {
            cg = (Math.sqrt(cg.toDouble()) * 16).toInt()
        }

        var sa = Integer.toHexString(ca)
        if (sa.length < 2) {
            sa = "0" + sa
        }
        var sr = Integer.toHexString(cr)
        if (sr.length < 2) {
            sr = "0" + sr
        }
        var sg = Integer.toHexString(cg)
        if (sg.length < 2) {
            sg = "0" + sg
        }
        var sb = Integer.toHexString(cb)
        if (sb.length < 2) {
            sb = "0" + sb
        }
        val colorStr = "#" + sa + sr + sg + sb
        Log.i("Utils", "stringToColor:colorStr=$colorStr")
        return Color.parseColor(colorStr)
    }
}

package com.hsj86715.ingress.glyphres.data;

import android.support.annotation.NonNull;

import com.hsj86715.ingress.glyphres.BuildConfig;

import java.util.Arrays;

import cn.com.farmcode.utility.tools.Logger;

/**
 * Created by hushujun on 2018/2/13.
 */

public class HackList extends LearnAndPractise implements Comparable<HackList> {
    private GlyphInfo head;
    private int length;
    private GlyphInfo[] sequences;

    public GlyphInfo getHead() {
        return head;
    }

    protected void setHead(GlyphInfo head) {
        this.head = head;
    }

    public int getLength() {
        return length;
    }

    protected void setLength(int length) {
        if (sequences != null && sequences.length != length) {
            String msg = "Set hack list with wrong length, current sequences.length is " +
                    sequences.length + ", but the given length is " + length;
            if (BuildConfig.DEBUG) {
                throw new RuntimeException(msg);
            } else {
                Logger.e(msg);
            }
        }
        this.length = length;
    }

    public GlyphInfo[] getSequences() {
        return sequences;
    }

    protected void setSequences(GlyphInfo[] sequences) {
        if (length != 0 && sequences.length != length) {
            String msg = "Set hack list with wrong sequences, current length is " + length +
                    ", but the given sequences.length is " + sequences.length;
            if (BuildConfig.DEBUG) {
                throw new RuntimeException(msg);
            } else {
                Logger.e(msg);
            }
        }
        this.sequences = sequences;
    }

    @Override
    public String toString() {
        return "HackList{" +
                "head=" + head.getName() +
                ", length=" + length +
                ", sequences=" + Arrays.toString(sequences) +
                '}';
    }

    @Override
    public int compareTo(@NonNull HackList o) {
        return head.getName().compareTo(o.getHead().getName());
    }
}

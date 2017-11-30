package com.hsj86715.ingress.glyphres.data;

import android.support.annotation.NonNull;

import java.util.Arrays;

/**
 * Created by hushujun on 2017/11/21.
 */

public class GlyphBean implements Comparable<GlyphBean> {
    private int id;
    private String name;
    private String alias;
    private String alias1;
    private String alias2;
    private String alias3;
    private int[] path;

    protected int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias1() {
        return alias1;
    }

    public void setAlias1(String alias1) {
        this.alias1 = alias1;
    }

    public String getAlias2() {
        return alias2;
    }

    public void setAlias2(String alias2) {
        this.alias2 = alias2;
    }

    public String getAlias3() {
        return alias3;
    }

    public void setAlias3(String alias3) {
        this.alias3 = alias3;
    }

    public int[] getPath() {
        return path;
    }

    public void setPath(String[] pathStrs) {
        if (pathStrs != null) {
            this.path = new int[pathStrs.length];
            int length = pathStrs.length;
            for (int i = 0; i < length; i++) {
                this.path[i] = Integer.valueOf(pathStrs[i]);
            }
        }
    }

    public void setPath(int[] path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlyphBean glyphBean = (GlyphBean) o;

        if (id != glyphBean.id) return false;
        if (!name.equals(glyphBean.name)) return false;
        return Arrays.equals(path, glyphBean.path);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + Arrays.hashCode(path);
        return result;
    }

    @Override
    public String toString() {
        return "GlyphBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", alias1='" + alias1 + '\'' +
                ", alias2='" + alias2 + '\'' +
                ", alias3='" + alias3 + '\'' +
                ", path=" + Arrays.toString(path) +
                '}';
    }

    @Override
    public int compareTo(@NonNull GlyphBean o) {
        return name.compareTo(o.name);
    }
}

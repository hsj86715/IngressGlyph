package com.hsj86715.ingress.glyphres.data;

import java.util.Arrays;

public class Path {
    private int id;
    private int[] path;
    private int[] path1;
    private int[] path2;
    private int[] path3;
    private int[] path4;

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public int[] getPath() {
        return path;
    }

    protected void setPath(int[] path) {
        this.path = path;
    }

    public int[] getPath1() {
        return path1;
    }

    protected void setPath1(int[] path1) {
        this.path1 = path1;
    }

    public int[] getPath2() {
        return path2;
    }

    protected void setPath2(int[] path2) {
        this.path2 = path2;
    }

    public int[] getPath3() {
        return path3;
    }

    protected void setPath3(int[] path3) {
        this.path3 = path3;
    }

    public int[] getPath4() {
        return path4;
    }

    protected void setPath4(int[] path4) {
        this.path4 = path4;
    }

    @Override
    public String toString() {
        return "Path{" +
                "id=" + id +
                ", path='" + Arrays.toString(path) + '\'' +
                ", path1='" + Arrays.toString(path1) + '\'' +
                ", path2='" + Arrays.toString(path2) + '\'' +
                ", path3='" + Arrays.toString(path3) + '\'' +
                ", path4='" + Arrays.toString(path4) + '\'' +
                '}';
    }
}

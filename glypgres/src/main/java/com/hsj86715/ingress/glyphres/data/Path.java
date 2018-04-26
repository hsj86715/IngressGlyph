package com.hsj86715.ingress.glyphres.data;

import android.os.Build;

import java.util.Arrays;
import java.util.Objects;

public class Path {
    private int id;
    private String path;
    private String path1;
    private String path2;
    private String path3;
    private String path4;

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    protected void setPath(String path) {
        this.path = path;
    }

    public String getPath1() {
        return path1;
    }

    protected void setPath1(String path1) {
        this.path1 = path1;
    }

    public String getPath2() {
        return path2;
    }

    protected void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getPath3() {
        return path3;
    }

    protected void setPath3(String path3) {
        this.path3 = path3;
    }

    public String getPath4() {
        return path4;
    }

    protected void setPath4(String path4) {
        this.path4 = path4;
    }

    @Override
    public String toString() {
        return "Path{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", path1='" + path1 + '\'' +
                ", path2='" + path2 + '\'' +
                ", path3='" + path3 + '\'' +
                ", path4='" + path4 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path1 = (Path) o;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return id == path1.id &&
                    Objects.equals(path, path1.path);
        } else {
            return id == path1.id && (
                    (path == path1.path) || (path != null && path.equals(path1.path)));
        }
    }

    @Override
    public int hashCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.hash(id, path);
        } else {
            return Arrays.hashCode(new Object[]{id, path});
        }
    }
}

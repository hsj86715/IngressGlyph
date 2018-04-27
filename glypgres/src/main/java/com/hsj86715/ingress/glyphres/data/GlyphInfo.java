package com.hsj86715.ingress.glyphres.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by hushujun on 2017/11/21.
 */

public class GlyphInfo extends LearnAndPractise implements Parcelable {
    public static final Creator<GlyphInfo> CREATOR = new Creator<GlyphInfo>() {
        @Override
        public GlyphInfo createFromParcel(Parcel in) {
            return new GlyphInfo(in);
        }

        @Override
        public GlyphInfo[] newArray(int size) {
            return new GlyphInfo[size];
        }
    };
    private int id;
    private String name;
    private int nameId;
    private String category;
    private int[] path;
    private int pathId;

    protected GlyphInfo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        nameId = in.readInt();
        category = in.readString();
        path = in.createIntArray();
        pathId = in.readInt();
        learnCount = in.readInt();
        practiseCount = in.readInt();
        practiseCorrect = in.readInt();
    }

    public GlyphInfo() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(nameId);
        dest.writeString(category);
        dest.writeIntArray(path);
        dest.writeInt(pathId);
        dest.writeInt(learnCount);
        dest.writeInt(practiseCount);
        dest.writeInt(practiseCorrect);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public int getNameId() {
        return nameId;
    }

    protected void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public String getCategory() {
        return category;
    }

    protected void setCategory(String category) {
        this.category = category;
    }

    public int[] getPath() {
        return path;
    }

    protected void setPath(int[] path) {
        this.path = path;
    }

    public int getPathId() {
        return pathId;
    }

    protected void setPathId(int pathId) {
        this.pathId = pathId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GlyphInfo glyphBean = (GlyphInfo) o;

        if (id != glyphBean.id) {
            return false;
        }
        if (!name.equals(glyphBean.name)) {
            return false;
        }
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
        return "GlyphInfo{" +
                "id=" + id +
                ", name=" + name +
                ", category='" + category + '\'' +
                ", path='" + Arrays.toString(path) + '\'' +
                '}';
    }
}

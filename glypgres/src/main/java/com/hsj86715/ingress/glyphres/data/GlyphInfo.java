package com.hsj86715.ingress.glyphres.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by hushujun on 2017/11/21.
 * @author hushujun
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
    private long id;
    private String name;
    private long nameId;
    private String category;
    private int[] path;
    private long pathId;

    protected GlyphInfo(Parcel in) {
        id = in.readLong();
        name = in.readString();
        nameId = in.readLong();
        category = in.readString();
        path = in.createIntArray();
        pathId = in.readLong();
        learnCount = in.readInt();
        practiseCount = in.readInt();
        practiseCorrect = in.readInt();
    }

    public GlyphInfo() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeLong(nameId);
        dest.writeString(category);
        dest.writeIntArray(path);
        dest.writeLong(pathId);
        dest.writeInt(learnCount);
        dest.writeInt(practiseCount);
        dest.writeInt(practiseCorrect);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public long getNameId() {
        return nameId;
    }

    protected void setNameId(long nameId) {
        this.nameId = nameId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int[] getPath() {
        return path;
    }

    protected void setPath(int[] path) {
        this.path = path;
    }

    public long getPathId() {
        return pathId;
    }

    protected void setPathId(long pathId) {
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
        int result = (int) id;
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

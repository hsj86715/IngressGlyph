package com.hsj86715.ingress.glyphres.data;

/**
 * Created by hushujun on 2017/11/21.
 */

public class GlyphInfo extends LearnAndPractise {
    private int id;
    private String name;
    private int nameId;
    private String category;
    private String path;
    private int pathId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNameId() {
        return nameId;
    }

    public String getCategory() {
        return category;
    }

    public String getPath() {
        return path;
    }

    public int getPathId() {
        return pathId;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setNameId(int nameId) {
        this.nameId = nameId;
    }

    protected void setCategory(String category) {
        this.category = category;
    }

    protected void setPath(String path) {
        this.path = path;
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
        return path.equals(glyphBean.path);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GlyphInfo{" +
                "id=" + id +
                ", name=" + name +
                ", category='" + category + '\'' +
                ", pathForward='" + path + '\'' +
                '}';
    }
}

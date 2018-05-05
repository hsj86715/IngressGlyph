package com.hsj86715.ingress.glyphres.data;

/**
 * @author hushujun
 */
public class EditTemp {
    private long id = -1;
    private long glyphId;
    private String alias;
    private String alias1;
    private String alias2;
    private String alias3;
    private String catName;
    private String path1;
    private String path2;
    private String path3;
    private String path4;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGlyphId() {
        return glyphId;
    }

    public void setGlyphId(long glyphId) {
        this.glyphId = glyphId;
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

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getPath1() {
        return path1;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getPath3() {
        return path3;
    }

    public void setPath3(String path3) {
        this.path3 = path3;
    }

    public String getPath4() {
        return path4;
    }

    public void setPath4(String path4) {
        this.path4 = path4;
    }

    public void copyFromName(Name name) {
        if (name == null) {
            return;
        }
        alias = name.getAlias();
        alias1 = name.getAlias1();
        alias2 = name.getAlias2();
        alias3 = name.getAlias3();
    }

    @Override
    public String toString() {
        return "EditTemp{" +
                "id=" + id +
                ", glyphId=" + glyphId +
                ", alias='" + alias + '\'' +
                ", alias1='" + alias1 + '\'' +
                ", alias2='" + alias2 + '\'' +
                ", alias3='" + alias3 + '\'' +
                ", catName='" + catName + '\'' +
                ", path1='" + path1 + '\'' +
                ", path2='" + path2 + '\'' +
                ", path3='" + path3 + '\'' +
                ", path4='" + path4 + '\'' +
                '}';
    }
}

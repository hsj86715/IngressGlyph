package com.hsj86715.ingress.glyphres.data;

/**
 * @author hushujun
 */
public class Category {
    private long id;
    private String name;

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

    public String toFullString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return name;
    }
}

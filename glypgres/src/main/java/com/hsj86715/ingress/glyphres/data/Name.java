package com.hsj86715.ingress.glyphres.data;

/**
 * Created by hushujun on 2018/2/13.
 */

public class Name {
    private int id;
    private String name;
    private String alias;
    private String alias1;
    private String alias2;
    private String alias3;

    public Name() {
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

    public String getAlias() {
        return alias;
    }

    protected void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias1() {
        return alias1;
    }

    protected void setAlias1(String alias1) {
        this.alias1 = alias1;
    }

    public String getAlias2() {
        return alias2;
    }

    protected void setAlias2(String alias2) {
        this.alias2 = alias2;
    }

    public String getAlias3() {
        return alias3;
    }

    protected void setAlias3(String alias3) {
        this.alias3 = alias3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Name name1 = (Name) o;

        if (id != name1.id) {
            return false;
        }
        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Name{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", alias1='" + alias1 + '\'' +
                ", alias2='" + alias2 + '\'' +
                ", alias3='" + alias3 + '\'' +
                '}';
    }
}

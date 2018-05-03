package com.hsj86715.ingress.glyphres.data;

import android.text.TextUtils;

/**
 * Created by hushujun on 2018/2/13.
 *
 * @author hushujun
 */

public class Name {
    private long id;
    private String name;
    private String alias;
    private String alias1;
    private String alias2;
    private String alias3;

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

    public String getAlisaString() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(alias)) {
            sb.append(alias);
        }
        if (!TextUtils.isEmpty(alias1)) {
            if (sb.length() > 0) {
                sb.append("/");
            }
            sb.append(alias1);
        }
        if (!TextUtils.isEmpty(alias2)) {
            if (sb.length() > 0) {
                sb.append("/");
            }
            sb.append(alias2);
        }
        if (!TextUtils.isEmpty(alias3)) {
            if (sb.length() > 0) {
                sb.append("/");
            }
            sb.append(alias3);
        }
        if (sb.length() <= 0) {
            return null;
        } else {
            return sb.toString();
        }
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
        int result = (int) id;
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

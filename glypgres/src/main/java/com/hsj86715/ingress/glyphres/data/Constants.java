package com.hsj86715.ingress.glyphres.data;

import android.support.annotation.StringDef;

public class Constants {
    public static final String C_ALL = "all";
    public static final String C_HUMAN = "human";
    public static final String C_ACTION = "action";
    public static final String C_THOUGHT = "thought";
    public static final String C_FLU_DIRE = "fluctuation/direction";
    public static final String C_TIME_SPACE = "time/space";
    public static final String C_COND_ENV = "condition/environment";

    @StringDef({C_ALL, C_HUMAN, C_ACTION, C_THOUGHT, C_FLU_DIRE, C_TIME_SPACE, C_COND_ENV/*, C_NEW_ADDED,
            C_NO_CAREGORY*/})
    public @interface Category {

    }
}

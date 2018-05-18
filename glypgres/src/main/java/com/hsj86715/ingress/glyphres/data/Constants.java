package com.hsj86715.ingress.glyphres.data;

import android.support.annotation.StringDef;

public class Constants {
    public static final String C_ALL = "All";
    public static final String C_HUMAN = "Human";
    public static final String C_ACTION = "Action";
    public static final String C_THOUGHT = "Thought";
    public static final String C_FLU_DIRE = "Fluctuation/Direction";
    public static final String C_TIME_SPACE = "Time/Space";
    public static final String C_COND_ENV = "Condition/Environment";

    @StringDef({C_ALL, C_HUMAN, C_ACTION, C_THOUGHT, C_FLU_DIRE, C_TIME_SPACE, C_COND_ENV/*, C_NEW_ADDED,
            C_NO_CAREGORY*/})
    public @interface Category {

    }

    public static final int X_ANIMATE_DURATION = 250;
    public static final int Y_ANIMATE_DURATION = X_ANIMATE_DURATION * 5;
}

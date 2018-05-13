package com.hsj86715.ingress.glyphres.data;

public class LearnAndPractise {
    protected int learnCount;
    protected int practiseCount;
    protected int practiseCorrect;
    protected long practiseBest;

    public int getLearnCount() {
        return learnCount;
    }

    public void setLearnCount(int learnCount) {
        this.learnCount = learnCount;
    }

    public int getPractiseCount() {
        return practiseCount;
    }

    public void setPractiseCount(int practiseCount) {
        this.practiseCount = practiseCount;
    }

    public int getPractiseCorrect() {
        return practiseCorrect;
    }

    public void setPractiseCorrect(int practiseCorrect) {
        this.practiseCorrect = practiseCorrect;
    }

    public void setPractiseBest(long best) {
        this.practiseBest = best;
    }

    public long getPractiseBest() {
        return practiseBest;
    }
}

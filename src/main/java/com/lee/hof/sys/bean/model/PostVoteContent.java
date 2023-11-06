package com.lee.hof.sys.bean.model;

public class PostVoteContent {

    private int voteType;

    private String firstOption;

    private String secondOption;

    private int firstVoteCnt;

    private int secondVoteCnt;


    public int getVoteType() {
        return voteType;
    }

    public void setVoteType(int voteType) {
        this.voteType = voteType;
    }

    public String getFirstOption() {
        return firstOption;
    }

    public void setFirstOption(String firstOption) {
        this.firstOption = firstOption;
    }

    public String getSecondOption() {
        return secondOption;
    }

    public void setSecondOption(String secondOption) {
        this.secondOption = secondOption;
    }

    public int getFirstVoteCnt() {
        return firstVoteCnt;
    }

    public void setFirstVoteCnt(int firstVoteCnt) {
        this.firstVoteCnt = firstVoteCnt;
    }

    public int getSecondVoteCnt() {
        return secondVoteCnt;
    }

    public void setSecondVoteCnt(int secondVoteCnt) {
        this.secondVoteCnt = secondVoteCnt;
    }
}

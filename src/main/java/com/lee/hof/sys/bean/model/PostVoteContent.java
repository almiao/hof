package com.lee.hof.sys.bean.model;

import java.util.List;

public class PostVoteContent {

    private int voteType;

    private List<PostOption> options;


    public int getVoteType() {
        return voteType;
    }

    public void setVoteType(int voteType) {
        this.voteType = voteType;
    }


    public List<PostOption> getOptions() {
        return options;
    }

    public void setOptions(List<PostOption> options) {
        this.options = options;
    }
}

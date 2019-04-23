package com.serenidad;

import java.io.Serializable;

public class JournalThoughts implements Serializable{

    private int thoughtId;
    private String userName;
    private String emotion;
    private String feelings;
    private String thoughtDate;
    private String situatationWhom;
    private String situationWhen;
    private String situationWhere;
    private String behaviourAfterThought;
    private String getBehaviourReaction;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getFeelings() {
        return feelings;
    }

    public void setFeelings(String feelings) {
        this.feelings = feelings;
    }

    public String getThoughtDate() {
        return thoughtDate;
    }

    public void setThoughtDate(String thoughtDate) {
        this.thoughtDate = thoughtDate;
    }

    public String getSituatationWhom() {
        return situatationWhom;
    }

    public void setSituatationWhom(String situatationWhom) {
        this.situatationWhom = situatationWhom;
    }

    public String getSituationWhen() {
        return situationWhen;
    }

    public void setSituationWhen(String situationWhen) {
        this.situationWhen = situationWhen;
    }

    public String getSituationWhere() {
        return situationWhere;
    }

    public void setSituationWhere(String situationWhere) {
        this.situationWhere = situationWhere;
    }

    public String getBehaviourAfterThought() {
        return behaviourAfterThought;
    }

    public void setBehaviourAfterThought(String behaviourAfterThought) {
        this.behaviourAfterThought = behaviourAfterThought;
    }

    public String getGetBehaviourReaction() {
        return getBehaviourReaction;
    }

    public void setGetBehaviourReaction(String getBehaviourReaction) {
        this.getBehaviourReaction = getBehaviourReaction;
    }

    public int getThoughtId() {
        return thoughtId;
    }

    public void setThoughtId(int thoughtId) {
        this.thoughtId = thoughtId;
    }
}

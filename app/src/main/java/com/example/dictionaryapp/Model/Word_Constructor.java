package com.example.dictionaryapp.Model;

public class Word_Constructor {
    private int id;

    public Word_Constructor(int id, String word, String mean, int have_learn) {
        this.id = id;
        this.have_learn = have_learn;
        this.word = word;
        this.mean = mean;
    }

    private int have_learn;
    private String word,mean;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHave_learn() {
        return have_learn;
    }

    public void setHave_learn(int have_learn) {
        this.have_learn = have_learn;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }


}

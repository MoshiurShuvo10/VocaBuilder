package com.example.vocabuilder.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ApiModel(description = "Details about the word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The unique id of the word")
    private long wordId ;

    @ApiModelProperty(notes = "The word")
    private String word ;

    @ApiModelProperty(notes = "Meaning of the word")
    private String meaning ;

    @ApiModelProperty(notes = "A sentence using the word")
    private String sentence ;

    public Word() {}

    public Word(String word, String meaning, String sentence) {
        this.word = word;
        this.meaning = meaning;
        this.sentence = sentence;
    }

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}

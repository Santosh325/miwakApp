package com.example.miwakapp;

public class Word {
    private String defaultTranslation;
    private String miwokTranslation;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    public static final int NO_IMAGE_PROVIDED = -1;
    private int audioResourseId;
    public Word(String defaultTranslation, String miwokTranslation,int imageResourceId, int audioResourseId) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceId = imageResourceId;
        this.audioResourseId = audioResourseId;

    }

    public Word(String defaultTranslation, String miwokTranslation,int audioResourseId) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.audioResourseId = audioResourseId;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }
    public int getAudioResourseId() {
        return audioResourseId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "defaultTranslation='" + defaultTranslation + '\'' +
                ", miwokTranslation='" + miwokTranslation + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", audioResourseId=" + audioResourseId +
                '}';
    }
}

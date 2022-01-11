package com.example.live.model.compteBourse;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public abstract class CompteBourseHasAction {
    public abstract Double getPrixachat();
    public abstract LocalDate getDateachat();
    public abstract  int getQuantite();
}

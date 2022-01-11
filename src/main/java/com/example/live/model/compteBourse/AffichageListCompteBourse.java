package com.example.live.model.compteBourse;

import com.example.live.model.compteBourse.action.Action;

public class AffichageListCompteBourse {
    private Action action;
    private CompteBourseHasAction compteBourseHasAction;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public CompteBourseHasAction getCompteBourseHasAction() {
        return compteBourseHasAction;
    }

    public void setCompteBourseHasAction(CompteBourseHasAction compteBourseHasAction) {
        this.compteBourseHasAction = compteBourseHasAction;
    }

    public AffichageListCompteBourse(Action action, CompteBourseHasAction compteBourseHasAction) {
        this.action = action;
        this.compteBourseHasAction = compteBourseHasAction;
    }



    @Override
    public String toString() {
        return "AffichageListCompteBourse{" +
                "action=" + action +
                ", compteBourseHasAction=" + compteBourseHasAction +
                '}';
    }
}

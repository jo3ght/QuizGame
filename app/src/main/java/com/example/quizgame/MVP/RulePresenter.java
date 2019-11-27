package com.example.quizgame.MVP;

public class RulePresenter {
    RuleView ruleView;

    public RulePresenter(RuleView ruleView) {
        this.ruleView = ruleView;
    }

    public void backMainfRule() {
        ruleView.backMainfRule();
    }
}

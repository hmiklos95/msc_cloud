package hu.pannon.api.models;

import hu.pannon.models.TranslationLog;

public class TranslationLogApiModel {
    public String from;

    public String target;

    public String text;

    public TranslationLogApiModel(TranslationLog translationLog) {
        this.from = translationLog.source;
        this.target = translationLog.target;
        this.text = translationLog.text;
    }

    public TranslationLogApiModel() {
    }
}

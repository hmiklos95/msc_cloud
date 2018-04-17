package hu.pannon.api.models;

import com.google.cloud.translate.Translation;

public class TranslateResult {
    private String translatedText;

    public TranslateResult() {

    }

    public TranslateResult(Translation translation) {
        this.translatedText = translation.getTranslatedText();
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }
}

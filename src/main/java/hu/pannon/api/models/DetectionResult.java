package hu.pannon.api.models;

import com.google.cloud.translate.Detection;

public class DetectionResult {
    public DetectionResult() {

    }

    public DetectionResult(Detection detection) {
        this.lang = detection.getLanguage();
        this.confidence = detection.getConfidence();
    }

    private String lang;
    private double confidence;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}

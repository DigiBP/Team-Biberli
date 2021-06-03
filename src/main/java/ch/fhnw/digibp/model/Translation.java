package ch.fhnw.digibp.model;

public class Translation {
    private String detected_source_language;
    private String text;

    public String getDetected_source_language() {
        return detected_source_language;
    }

    public void setDetected_source_language(String detected_source_language) {
        this.detected_source_language = detected_source_language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

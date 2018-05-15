package hu.pannon.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TranslationLog {
    @GeneratedValue
    @Id
    public Long id;

    public String source;

    public String target;

    public String text;

    public TranslationLog(String source, String to, String text) {
        this.source = source;
        this.target = to;
        this.text = text;
    }

    public TranslationLog() {
    }
}

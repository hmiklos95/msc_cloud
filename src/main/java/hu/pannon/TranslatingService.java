package hu.pannon;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.*;
import com.google.cloud.translate.Translate.LanguageListOption;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.common.collect.ImmutableList;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

public class TranslatingService {

    private final Translate translate;
    @Inject
    public TranslatingService() {
        translate = createTranslateService();
    }

    /**
     * Detect the language of input text.
     *
     * @param sourceText source text to be detected for language
     */
    public List<Detection> detectLanguage(String sourceText) {
        List<Detection> detections = translate.detect(ImmutableList.of(sourceText));

        return detections;
    }

    /**
     * Translates the source text in any language to English.
     *
     * @param sourceText source text to be translated
     * @param out print stream
     */
    public void translateText(String sourceText, PrintStream out) {
        Translation translation = translate.translate(sourceText);
        out.printf("Source Text:\n\t%s\n", sourceText);
        out.printf("Translated Text:\n\t%s\n", translation.getTranslatedText());
    }

    /**
     * Translate the source text from source to target language.
     * Make sure that your project is whitelisted.
     *  @param sourceText source text to be translated
     * @param sourceLang source language of the text
     * @param targetLang target language of translated text
     * @param out print stream
     */
    public Translation translateTextWithOptionsAndModel(
            String sourceText,
            String sourceLang,
            String targetLang) {

        TranslateOption srcLang = TranslateOption.sourceLanguage(sourceLang);
        TranslateOption tgtLang = TranslateOption.targetLanguage(targetLang);

        // Use translate `model` parameter with `base` and `nmt` options.
        TranslateOption model = TranslateOption.model("nmt");

        Translation translation = translate.translate(sourceText, srcLang, tgtLang, model);
        return translation;
    }


    /**
     * Translate the source text from source to target language.
     *
     * @param sourceText source text to be translated
     * @param sourceLang source language of the text
     * @param targetLang target language of translated text
     * @param out print stream
     */
    public void translateTextWithOptions(
            String sourceText,
            String sourceLang,
            String targetLang,
            PrintStream out) {

        Translate translate = createTranslateService();
        TranslateOption srcLang = TranslateOption.sourceLanguage(sourceLang);
        TranslateOption tgtLang = TranslateOption.targetLanguage(targetLang);

        Translation translation = translate.translate(sourceText, srcLang, tgtLang);
        out.printf("Source Text:\n\tLang: %s, Text: %s\n", sourceLang, sourceText);
        out.printf("TranslatedText:\n\tLang: %s, Text: %s\n", targetLang,
                translation.getTranslatedText());
    }

    /**
     * Displays a list of supported languages and codes.
     *
     * @param out print stream
     * @param tgtLang optional target language
     */
    public void displaySupportedLanguages(PrintStream out, Optional<String> tgtLang) {
        Translate translate = createTranslateService();
        LanguageListOption target = LanguageListOption.targetLanguage(tgtLang.orElse("en"));
        List<Language> languages = translate.listSupportedLanguages(target);

        for (Language language : languages) {
            out.printf("Name: %s, Code: %s\n", language.getName(), language.getCode());
        }
    }

    /**
     * Create Google Translate API Service.
     *
     * @return Google Translate Service
     */
    private Translate createTranslateService() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        try {
            return TranslateOptions.newBuilder().setProjectId("msc-cloud-parttime-2")
                    .setCredentials(
                            ServiceAccountCredentials
                                    .fromStream(classloader
                                            .getResourceAsStream("MSc-Cloud-parttime-2-b14768842490.json")))
                    .build().getService();
        } catch (IOException e) {

        }

        return null;
    }
}

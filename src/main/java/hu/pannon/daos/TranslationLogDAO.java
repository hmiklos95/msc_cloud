package hu.pannon.daos;

import hu.pannon.models.TranslationLog;

import javax.inject.Inject;

public class TranslationLogDAO extends GenericDAO<TranslationLog> {

    @Inject
    public TranslationLogDAO() {
        super(TranslationLog.class);
    }
}

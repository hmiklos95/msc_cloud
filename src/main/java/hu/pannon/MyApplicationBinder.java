package hu.pannon;

import hu.pannon.daos.TranslationLogDAO;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(TranslatingService.class).to(TranslatingService.class);
        bind(TranslationLogDAO.class).to(TranslationLogDAO.class);
    }
}

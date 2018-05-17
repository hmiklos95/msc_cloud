package hu.pannon.api;

import com.google.cloud.translate.Detection;
import hu.pannon.TranslatingService;
import hu.pannon.api.models.*;
import hu.pannon.daos.TranslationLogDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("translating")
@Api(value = "translating", description = "Translating")
@Singleton

public class TranslatingAPI {

    @Inject
    TranslatingService translatingService;

    @Inject
    TranslationLogDAO translationLogDAO;

    @Path("/detect/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Detect language", httpMethod = "POST" , response = List.class,
            consumes = "application/json", produces = "application/json")
    public List<DetectionResult> detectLanguage(DetectionQuery detectionQuery) {
        List<Detection> detections = translatingService.detectLanguage(detectionQuery.getText());

        return detections.stream().map(DetectionResult::new).collect(Collectors.toList());
    }

    @Path("/translate/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Translate text", httpMethod = "POST" , response = TranslateResult.class,
            consumes = "application/json", produces = "application/json")
    public TranslateResult translate(TranslateQuery translateQuery) {
        return new TranslateResult(translatingService.translateTextWithOptionsAndModel(translateQuery.getTextToTranslate(),
                translateQuery.getSourceLang(), translateQuery.getTargetLang()));
    }

    @Path("/previousTranslations/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Find previous translations", httpMethod = "GET" , response = List.class,
            produces = "application/json")
    public List<TranslationLogApiModel> findPreviousTranslations() {
        return translationLogDAO.findAll().stream().map(TranslationLogApiModel::new).collect(Collectors.toList());
    }
}

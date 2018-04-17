package hu.pannon.api;

import com.google.cloud.translate.Detection;
import hu.pannon.TranslatingService;
import hu.pannon.api.models.DetectionQuery;
import hu.pannon.api.models.DetectionResult;
import hu.pannon.api.models.TranslateQuery;
import hu.pannon.api.models.TranslateResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("translating")
@Api(value = "translating", description = "Translating")
public class TranslatingAPI {

    @Inject
    TranslatingService translatingService;

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
}

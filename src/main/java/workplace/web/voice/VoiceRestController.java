package workplace.web.voice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import workplace.AuthorizedUser;
import workplace.View;
import workplace.model.Voice;
import workplace.service.VoiceService;
import workplace.to.VoiceTo;
import workplace.util.VoiceUtil;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static workplace.util.ValidationUtil.assureIdConsistent;
import static workplace.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoiceRestController.REST_VOICE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoiceRestController {
    public static final String REST_VOICE_URL = "/rest/voices";
    private static final Logger log = LoggerFactory.getLogger(VoiceRestController.class);

    private final VoiceService service;

    @Autowired
    public VoiceRestController(VoiceService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Voice> createWithLocation(@Validated(View.Web.class) @RequestBody VoiceTo voiceTo, @AuthenticationPrincipal AuthorizedUser user) {
        checkNew(voiceTo);
        log.info("create {} for user {}", voiceTo, user);
        Voice created = service.create(voiceTo, user.getId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_VOICE_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody VoiceTo voiceTo, @PathVariable int id, @AuthenticationPrincipal AuthorizedUser user){
        log.info("update voice by id {}", id);
        assureIdConsistent(voiceTo, id);
        service.update(voiceTo, user.getId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser user){
        log.info("delete voice by id {}", id);
        service.delete(id, user.getId());
    }

    @GetMapping("/{id}")
    public VoiceTo get(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser user){
        log.info("get voice by id {}", id);
        return VoiceUtil.asTo(service.get(id, user.getId()));
    }

    @GetMapping
    public List<VoiceTo> getAll(@AuthenticationPrincipal AuthorizedUser user){
        log.info("get all voices by user {}", user);
        return service.getAll(user.getId()).stream().map(VoiceUtil::asTo).collect(Collectors.toList());
    }

}

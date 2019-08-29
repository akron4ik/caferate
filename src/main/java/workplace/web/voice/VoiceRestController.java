package workplace.web.voice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import workplace.View;
import workplace.model.Voice;
import workplace.service.VoiceService;
import workplace.to.VoiceTo;
import workplace.web.SecurityUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

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
    public ResponseEntity<Voice> createWithLocation(@Validated(View.Web.class) @RequestBody VoiceTo voiceTo) {
        int userId = SecurityUtil.authUserId();
        checkNew(voiceTo);
        log.info("create {} for user {}", voiceTo, userId);
        Voice created = service.create(voiceTo, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_VOICE_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody VoiceTo voiceTo, @PathVariable int id){
        int userId = SecurityUtil.authUserId();
        log.info("update voice by id {}", id);
        assureIdConsistent(voiceTo, id);
        service.update(voiceTo, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        int userId = SecurityUtil.authUserId();
        log.info("delete voice by id {}", id);
        service.delete(id, userId);
    }

    @GetMapping("/{id}")
    public Voice get(@PathVariable int id){
        int userId = SecurityUtil.authUserId();
        log.info("get voice by id {}", id);
        return service.get(id, userId);
    }

    @GetMapping("/all")
    public List<Voice> getAll(){
        int userId = SecurityUtil.authUserId();
        log.info("get all voices by user id {}", userId);
        return service.getAll(userId);
    }

}

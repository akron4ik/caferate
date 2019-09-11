package workplace.util;

import workplace.model.Voice;
import workplace.to.VoiceTo;

public class VoiceUtil {

    public static VoiceTo asTo(Voice voice) {
        return new VoiceTo(voice.getId(), voice.getLocalDate(), voice.getRestaurant().getId());
    }
}

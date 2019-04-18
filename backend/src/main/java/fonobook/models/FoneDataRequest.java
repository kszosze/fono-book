package fonobook.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoneDataRequest {

    private final String TOKEN = "d3bd900eb8759047b24a9d3f4c05100eed9b4a9a53260d99";
    @Builder.Default
    private int limit = 5;
    private String device;
}

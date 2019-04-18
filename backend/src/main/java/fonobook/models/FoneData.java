package fonobook.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoneData {

    @JsonProperty("DeviceName")
    private String deviceName;
    @JsonProperty("brand")
    private String brand;
    private String technology;
    @JsonProperty("_2g_bands")
    private String bands2g;
    @JsonProperty("_3g_bands")
    private String bands3g;
    @JsonProperty("_4g_bands")
    private String bands4g;
}

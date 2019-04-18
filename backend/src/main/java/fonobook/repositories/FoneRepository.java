package fonobook.repositories;

import fonobook.models.FoneData;
import fonobook.models.FoneDataRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="foneRepository", url="https://fonoapi.freshpixl.com/v1/getdevice")
@Repository
public interface FoneRepository {

    @PostMapping(consumes = "application/json")
    @Cacheable("deviceData")
    List<FoneData> getFoneData(FoneDataRequest foneDataRequest);
}

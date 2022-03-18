package kea.group4.backend.api;

import kea.group4.backend.dto.HobbyInfoRequest;
import kea.group4.backend.dto.HobbyInfoResponse;
import kea.group4.backend.services.HobbyInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hobby-infos")
public class HobbyInfoController {

    HobbyInfoService hobbyInfoService;

    public HobbyInfoController(HobbyInfoService hobbyInfoService) {
        this.hobbyInfoService = hobbyInfoService;
    }

    @GetMapping
    public List<HobbyInfoResponse> getHobbyInfos() {
        return hobbyInfoService.getHobbyInfos();
    }

    @GetMapping("/{id}")
    public HobbyInfoResponse getHobbyInfo(@PathVariable long id){
        return hobbyInfoService.getHobbyInfo(id);
    }

    @PostMapping
    public HobbyInfoResponse addHobbyInfo(@RequestBody HobbyInfoRequest body) {
        return hobbyInfoService.addHobbyInfo(body);
    }

    @DeleteMapping("/{id}")
    public void deleteHobbyInfo(@PathVariable long id){
        hobbyInfoService.deleteHobbyInfo(id);
    }
}

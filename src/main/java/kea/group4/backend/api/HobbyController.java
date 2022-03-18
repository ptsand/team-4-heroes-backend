package kea.group4.backend.api;

import kea.group4.backend.dto.HobbyRequest;
import kea.group4.backend.dto.HobbyResponse;
import kea.group4.backend.services.HobbyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hobbeis")
public class HobbyController {
    HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @GetMapping
    public List<HobbyResponse> getHobbies() {
        return hobbyService.getHobbies();
    }

    @GetMapping("/{id}")
    public HobbyResponse getHobby(@PathVariable long id){
        return hobbyService.getHobby(id);
    }

    @PostMapping
    public HobbyResponse addHobby(@RequestBody HobbyRequest body) {
        return hobbyService.addHobby(body);
    }

    @PutMapping("/{id}")
    public HobbyResponse editHobby(@RequestBody HobbyRequest body, @PathVariable long id){
        return hobbyService.editHobby(body,id);
    }

    @DeleteMapping("/{id}")
    public void deleteHobby(@PathVariable long id){
        hobbyService.deleteHobby(id);
    }



}

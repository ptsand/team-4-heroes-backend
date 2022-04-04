package kea.group4.backend.services;
import kea.group4.backend.dto.HobbyInfoRequest;
import kea.group4.backend.dto.HobbyInfoResponse;
import kea.group4.backend.entities.HobbyInfo;
import kea.group4.backend.error.Client4xxException;
import kea.group4.backend.error.ResourceNotFoundException;
import kea.group4.backend.repositories.HobbyInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HobbyInfoService {

    HobbyInfoRepository hobbyInfoRepository;

    public HobbyInfoService(HobbyInfoRepository hobbyInfoRepository){
        this.hobbyInfoRepository = hobbyInfoRepository;
    }

    public List<HobbyInfoResponse> getHobbyInfos(){
        List<HobbyInfo> hobbyInfos = hobbyInfoRepository.findAll();
        return HobbyInfoResponse.getHobbyInfosFromEntities(hobbyInfos);
    }

    public HobbyInfoResponse getHobbyInfo(long id){
        HobbyInfo hobbyInfo = hobbyInfoRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format("HobbyInfo with id %s not found", id)));
        return new HobbyInfoResponse(hobbyInfo);
    }

    public HobbyInfoResponse addHobbyInfo(HobbyInfoRequest body){
        HobbyInfo hobbyInfo = hobbyInfoRepository.save(new HobbyInfo(body));
        return new HobbyInfoResponse(hobbyInfo);
    }

    public void deleteHobbyInfo(long hobbyInfoId){
        hobbyInfoRepository.deleteById(hobbyInfoId);
    }

    public List<HobbyInfoResponse> getHobbyInfosByHobby(String hobby) {
        List<HobbyInfo> hobbyInfos = hobbyInfoRepository.findAll().stream()
                .filter(hi->hi.getHobby().getName().contains(hobby)).collect(Collectors.toList());
        return HobbyInfoResponse.getHobbyInfosFromEntities(hobbyInfos);
    }
}

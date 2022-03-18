package kea.group4.backend.services;
import kea.group4.backend.dto.HobbyInfoRequest;
import kea.group4.backend.dto.HobbyInfoResponse;
import kea.group4.backend.entities.HobbyInfo;
import kea.group4.backend.repositories.HobbyInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyInfoService extends RuntimeException{

    HobbyInfoRepository hobbyInfoRepository;

    public HobbyInfoService(HobbyInfoRepository hobbyInfoRepository){
        this.hobbyInfoRepository = hobbyInfoRepository;
    }

    public List<HobbyInfoResponse> getHobbyInfos(){
        List<HobbyInfo> hobbyInfos = hobbyInfoRepository.findAll();
        return HobbyInfoResponse.getHobbyInfosFromEntities(hobbyInfos);
    }

    public HobbyInfoResponse getHobbyInfo(long id){
        HobbyInfo hobbyInfo = hobbyInfoRepository.findById(id).orElseThrow();
        return new HobbyInfoResponse(hobbyInfo);
    }

    public HobbyInfoResponse addHobbyInfo(HobbyInfoRequest body){
        HobbyInfo hobbyInfo = hobbyInfoRepository.save(new HobbyInfo(body));
        return new HobbyInfoResponse(hobbyInfo);
    }

    public void deleteHobbyInfo(long hobbyInfoId){
        hobbyInfoRepository.deleteById(hobbyInfoId);
    }
}

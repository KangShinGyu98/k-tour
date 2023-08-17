package fivestar.kTour.service;

import fivestar.kTour.domain.Tourlist;
import fivestar.kTour.domain.User;
import fivestar.kTour.dto.*;
import fivestar.kTour.repository.TourlistRepository;
import fivestar.kTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlannerServiceImpl implements PlannerService{

    private final TourlistRepository tourlistRepository;
    private final UserRepository userRepository;
    @Override //userEmail => List<Tourlist>
    public GetMyTourlistsResDto GetMyTourlists(GetMyTourlistsDto dto) {
        List<Tourlist> tourlists =  tourlistRepository.findAllByUserEmail(dto.getUserEmail());
        GetMyTourlistsResDto response = new GetMyTourlistsResDto(tourlists.toString());
        return response;
    }

//    @Override
//    public GetOthersTourlistsResDto GetOthersTourlists(GetOthersTourlistsDto dto) {
//        List<Tourlist> tourlists =  tourlistRepository.findAllByUserEmailNot(dto.getUserEmail());
//        GetOthersTourlistsResDto response = new GetOthersTourlistsResDto(tourlists.toString());
//        return response;
//    }

    @Override
    public ResponseDto AddNewTourlist(AddNewTourlistDto dto) {
        if(!userRepository.existsById(dto.getUserEmail())) {
            throw new IllegalArgumentException("등록되지 않은 사용자 입니다.");
        }
        Tourlist newTourlist = new Tourlist();
        User user = userRepository.findById(dto.getUserEmail()).get();
        newTourlist.setTourlist(dto.getTourlist());
        newTourlist.setUser(user);

        tourlistRepository.save(newTourlist);

        return new ResponseDto(true);
    }

    @Override
    public ResponseDto TogglePassedTourlist(TogglePassedTourlistDto dto) {
        return null;
    }

}

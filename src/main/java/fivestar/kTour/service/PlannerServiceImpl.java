package fivestar.kTour.service;

import fivestar.kTour.domain.Place;
import fivestar.kTour.domain.Plan;
import fivestar.kTour.domain.User;
import fivestar.kTour.Dto.*;
import fivestar.kTour.repository.PlaceRepository;
import fivestar.kTour.repository.PlanRepository;
import fivestar.kTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlannerServiceImpl implements PlannerService{

    private final PlaceRepository placeRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    @Override //userEmail => plans{String planName, List<String> places}
    public GetMyPlansResDto GetMyPlans(GetMyPlansDto dto) {
        //빈 response dto 생성
        GetMyPlansResDto response = new GetMyPlansResDto();

        //사용자 email 체크
        if(!userRepository.existsById(dto.getUserEmail())) {
            throw new IllegalArgumentException("등록되지 않은 사용자 입니다.");
        }

        //email 에 맞는 plans 받아오기
        List<Plan> plans = planRepository.findAllByUser_UserEmail(dto.getUserEmail());

        //plans list 를 순회하며 places 및 response dto 설정
        plans.forEach(plan->{
            //places 를 placeNames 로 변환함
            List<String> placesNames = (List<String>) placeRepository.findAllByPlaceName(plan.getPlanName()).stream().map(place -> place.getPlaceName());
            //plan, places pair 를 response에 추가함
            response.getPlanPlacePairs().add(new GetMyPlansResDto.PlanPlacePair(plan.getPlanName(), placesNames));
        });

        return response;
    }

//    @Override
//    public GetOthersTourlistsResDto GetOthersTourlists(GetOthersTourlistsDto dto) {
//        List<Tourlist> tourlists =  tourlistRepository.findAllByUserEmailNot(dto.getUserEmail());
//        GetOthersTourlistsResDto response = new GetOthersTourlistsResDto(tourlists.toString());
//        return response;
//    }

    @Override
    public GlobalResponseDto AddNewPlan(AddNewPlanDto dto) {
        if(!userRepository.existsById(dto.getUserEmail())) {
            throw new IllegalArgumentException("등록되지 않은 사용자 입니다.");
        }
        User user = userRepository.findById(dto.getUserEmail()).get();
        Plan newPlan = new Plan();
        newPlan.setUser(user);
        planRepository.save(newPlan);
        dto.getPlaces().forEach(p -> {
            Place newPlace = new Place();
            newPlace.setPlaceName(p);
            newPlace.setPlan(newPlan);
            placeRepository.save(newPlace);
        });

        return new GlobalResponseDto(true);
    }

    @Override
    public GlobalResponseDto TogglePassedPlans(TogglePassedPlansDto dto) {
        if(!userRepository.existsById(dto.getUserEmail())) {
            throw new IllegalArgumentException("등록되지 않은 사용자 입니다.");
        }
//        User user = userRepository.findById(dto.getUserEmail()).get();
        dto.getPlanIdPassedPairs().forEach(pair->{
            planRepository.updatePassedStatus(pair.getPlanId(), pair.isPassed());
        });

        return new GlobalResponseDto(true);
    }
}

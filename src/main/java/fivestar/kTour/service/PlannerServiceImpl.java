package fivestar.kTour.service;

import fivestar.kTour.domain.Place;
import fivestar.kTour.domain.Plan;
import fivestar.kTour.domain.User;
import fivestar.kTour.Dto.*;
import fivestar.kTour.repository.PlaceRepository;
import fivestar.kTour.repository.PlanRepository;
import fivestar.kTour.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlannerServiceImpl implements PlannerService{
    private static final Logger logger = LoggerFactory.getLogger(PlannerServiceImpl.class);
    private final PlaceRepository placeRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    @Override //userEmail => plans{Long planId, List<String> places}
    public GetMyPlansResDto GetMyPlans(GetMyPlansDto dto) {
        //빈 response dto 생성
        GetMyPlansResDto response = new GetMyPlansResDto(new ArrayList<>());

        //사용자 email 체크
        if(!userRepository.existsById(dto.getUserEmail())) {
            throw new IllegalArgumentException("등록되지 않은 사용자 입니다.");
        }

        //email 에 맞는 plans 받아오기
        List<Plan> plans = planRepository.findAllByUser_UserEmail(dto.getUserEmail());
        logger.info(plans.toString());
        //plans list 를 순회하며 places 및 response dto 설정
        plans.forEach(plan->{
            //places 를 placeNames 로 변환함
            List<String> placeNames = placeRepository.findAllByPlan_PlanId(plan.getPlanId()).stream().map(place -> place.getPlaceName()).collect(Collectors.toList());;
            //plan, places pair 를 response에 추가함
            logger.info(plan.getPlanName());
            logger.info(placeNames.toString());
            response.getPlanPlacePairs().add(new GetMyPlansResDto.PlanPlacePair(plan.getPlanId(), placeNames));
        });
//        plan1 + List<Place>()
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
    @Transactional
    public GlobalResponseDto TogglePassedPlans(TogglePassedPlansDto dto) {
        if(!userRepository.existsById(dto.getUserEmail())) {
            throw new IllegalArgumentException("등록되지 않은 사용자 입니다.");
        }
//        User user = userRepository.findById(dto.getUserEmail()).get();
        logger.info(dto.toString());

        dto.getPlanIdPassedPairs().forEach(pair->{
            planRepository.updatePassedStatus(pair.getPlanId(), pair.isPassed());
        });

        return new GlobalResponseDto(true);
    }
}

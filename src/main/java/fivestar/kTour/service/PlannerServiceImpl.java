package fivestar.kTour.service;
import java.util.NoSuchElementException;
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
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class PlannerServiceImpl implements PlannerService{
    private static final Logger logger = LoggerFactory.getLogger(PlannerServiceImpl.class);
    private final PlaceRepository placeRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Override //userEmail => plans{Long planId, List<String> places}
    @Transactional
    public List<GetMyPlansResDto> GetMyPlans(GetMyPlansDto dto) {
        //빈 response dto 생성
        List<GetMyPlansResDto> response = new ArrayList<>();

        //사용자 email 체크
        if(!userRepository.existsById(dto.userEmail())) {
            throw new IllegalArgumentException("등록되지 않은 사용자 입니다.");
        }

        //email 에 맞는 plans 받아오기
        List<Plan> plans = planRepository.findAllByUser_UserEmail(dto.userEmail());
        logger.info(plans.toString());
        //plans list 를 순회하며 places 및 response dto 설정
        plans.forEach(plan->{
            //places 를 placeNames 로 변환함
            //place name만 줄까? 그냥 다 줄까?
            //name 만 주는 경우
            //List<Place> places = placeRepository.findAllByPlan_PlanId(plan.getPlanId()).stream().map(place -> place.getPlaceName()).collect(Collectors.toList());
            List<Place> places = placeRepository.findAllByPlan_PlanId(plan.getPlanId());

            //plan, places pair 를 response에 추가함
            logger.info(plan.getPlanName());
            logger.info(places.toString());
            response.add(new GetMyPlansResDto(plan.getPlanId(), plan.getPlanName(), places));
        });
        return response;
    }

    @Override
    @Transactional
    public GlobalResponseDto AddNewPlan(AddNewPlanDto dto) {
        //사용자 email이 존재하는지 체크
        if(!userRepository.existsById(dto.userEmail())) {
            throw new IllegalArgumentException("등록되지 않은 사용자 입니다.");
        }

        User user = userRepository.findById(dto.userEmail()).orElseThrow(() -> new NoSuchElementException("User not found"));

        //plan table save
        Plan newPlan = new Plan(dto.planName(),dto.planNote(),user);
        planRepository.save(newPlan);

        //place table save
        dto.places().forEach(place -> {
            Place newPlace = Place.builder()
                    .placeName(place.getPlaceName())
                    .placeNote(place.getPlaceNote()) // placeNote가 null이어도 무시되지 않음
                    .plan(newPlan)       // somePlan이 null이어도 무시되지 않음
                    .xPos(place.getXPos())
                    .yPos(place.getYPos())
                    .passed(false)
                    .build();
            placeRepository.save(newPlace);
        });
        //build를 사용한 이유는
        // 1. 생성자를 일일이 생성해주지 않고, null을 받아서
        // 2. plan을 주입하기 위해서

        return new GlobalResponseDto(true);
    }

    @Override
    @Transactional
    public GlobalResponseDto TogglePassedPlans(TogglePassedPlansDto dto) {
//        @NotBlank
//        String userEmail,
//        List<Plan> plans

        if(!userRepository.existsById(dto.userEmail())) {
            throw new IllegalArgumentException("등록되지 않은 사용자 입니다.");
        }
//        User user = userRepository.findById(dto.getUserEmail()).get();
        logger.info(dto.toString());
        dto.plans().forEach(plan ->
                    //plan id 로 plan을 찾아서 passed 를 dto 에서 제공하는 passed 값으로 변경함
                planRepository
                    .findById(plan.getPlanId())
                    .ifPresent(p -> p.setPassed(plan.getPassed())
                    )
            );

        return new GlobalResponseDto(true);
    }
}

package fivestar.kTour.controller;

import fivestar.kTour.Dto.AddNewPlanDto;

import fivestar.kTour.Dto.GetMyPlansDto;
import fivestar.kTour.Dto.GetMyPlansResDto;
import fivestar.kTour.Dto.GlobalResponseDto;
import fivestar.kTour.Dto.TogglePassedPlansDto;
import fivestar.kTour.security.SecurityUser;
import fivestar.kTour.service.PlannerServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PlannerController {
    private final PlannerServiceImpl plannerServiceImpl;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * @AuthenticationPrincipal -> 해당 애노테이션을 적용 로그인 유저 정보를 사용한다.
     * @param user -> 시큐리티 필터를 통과하고 Context 에 세팅된 로그인 유저의 정보
     *             UserDetailsService 를 구현한 jpaUserDetailsService 에서 DB 커넥트를 통해 세팅해놈
     */
    @GetMapping("/v1/plans")
    public List<GetMyPlansResDto> getMyPlans(@AuthenticationPrincipal SecurityUser user) {

        List<GetMyPlansResDto> result = plannerServiceImpl.GetMyPlansV1(user);
        return result;
    }

    @GetMapping("/plans")//내 플렌 조회 figma page4
    public List<GetMyPlansResDto> getMyPlans(@Validated @RequestBody GetMyPlansDto dto) {
        List<GetMyPlansResDto> result = plannerServiceImpl.GetMyPlans(dto);
        return result;
    }

    @PostMapping("/plans/new") // figma page 1 button
    public GlobalResponseDto AddNewPlan(@Validated @RequestBody AddNewPlanDto dto) {
        GlobalResponseDto result = plannerServiceImpl.AddNewPlan(dto);
        return result;
    }

    @PatchMapping("/plans/update")//준비중인 여행 <=> 지난 여행 토글버튼 figma page 4
    public GlobalResponseDto TogglePassedPlan(@Validated @RequestBody TogglePassedPlansDto dto) {
        GlobalResponseDto result = plannerServiceImpl.TogglePassedPlans(dto);
        return result;
    }
}

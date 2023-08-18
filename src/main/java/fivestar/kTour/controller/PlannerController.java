package fivestar.kTour.controller;

import fivestar.kTour.Dto.AddNewPlanDto;

import fivestar.kTour.Dto.GetMyPlansDto;
import fivestar.kTour.Dto.GetMyPlansResDto;
import fivestar.kTour.Dto.GlobalResponseDto;
import fivestar.kTour.Dto.TogglePassedPlansDto;
import fivestar.kTour.service.PlannerServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class PlannerController {
    private final PlannerServiceImpl plannerServiceImpl;


    @GetMapping("/plans")//내 플렌 조회 figma page4
    public GetMyPlansResDto getMyPlans(@RequestBody GetMyPlansDto dto){
        GetMyPlansResDto result = plannerServiceImpl.GetMyPlans(dto);
        return result;
    }
//    @GetMapping("/plans/detail") //플렌 디테일 페이지 필요?
//    public G
    @PostMapping("/plans/new") // figma page 1 button
    public GlobalResponseDto AddNewPlan(@RequestBody AddNewPlanDto dto){
        GlobalResponseDto result = plannerServiceImpl.AddNewPlan(dto);
        return result;
    }
    @PatchMapping("/plans/update")//준비중인 여행 <=> 지난 여행 토글버튼 figma page 4
    public GlobalResponseDto TogglePassedPlan(@RequestBody TogglePassedPlansDto dto){
        GlobalResponseDto result = plannerServiceImpl.TogglePassedPlans(dto);
        return result;
    }
}

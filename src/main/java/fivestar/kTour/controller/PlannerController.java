package fivestar.kTour.controller;

import fivestar.kTour.dto.*;
import fivestar.kTour.repository.UserRepository;
import fivestar.kTour.service.PlannerServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class PlannerController {
    private final PlannerServiceImpl plannerServiceImpl;
    private final UserRepository userRepository;

    @PostMapping("/plans/new") // figma page 1 button
    public ResponseDto AddNewTourlist(@RequestBody AddNewTourlistDto dto){
        ResponseDto result = plannerServiceImpl.AddNewTourlist(dto);
        return result;
    }

    @GetMapping("/plans")//내 플렌 조회 figma page4
    public GetMyTourlistsResDto getMyTourlists(@RequestBody GetMyTourlistsDto dto){
        GetMyTourlistsResDto result = plannerServiceImpl.GetMyTourlists(dto);
        return result;
    }
//    @GetMapping("/plans/detail") //플렌 디테일 페이지 필요?
//    public G

    @PatchMapping("/plans/update")//준비중인 여행 <=> 지난 여행 토글버튼 figma page 4
    public ResponseDto TogglePassedTourlist(@RequestBody TogglePassedTourlistDto dto){
        ResponseDto result = plannerServiceImpl.TogglePassedTourlist(dto);
        return result;
    }
}

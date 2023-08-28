package fivestar.kTour.service;

import fivestar.kTour.Dto.*;

import java.util.List;

public interface PlannerService {
    List<GetMyPlansResDto> GetMyPlans(GetMyPlansDto dto);
    GlobalResponseDto AddNewPlan(AddNewPlanDto dto);
    GlobalResponseDto TogglePassedPlans(TogglePassedPlansDto dto);
}

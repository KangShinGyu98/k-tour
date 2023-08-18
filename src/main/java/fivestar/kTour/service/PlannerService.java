package fivestar.kTour.service;

import fivestar.kTour.Dto.*;

public interface PlannerService {
    GetMyPlansResDto GetMyPlans(GetMyPlansDto dto);
    GlobalResponseDto AddNewPlan(AddNewPlanDto dto);
    GlobalResponseDto TogglePassedPlans(TogglePassedPlansDto dto);
}

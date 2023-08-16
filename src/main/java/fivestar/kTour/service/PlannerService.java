package fivestar.kTour.service;

import fivestar.kTour.dto.GetMyTourlistsReqDto;
import fivestar.kTour.dto.GetMyTourlistsResDto;
import fivestar.kTour.dto.GetOthersTourlistsReqDto;
import fivestar.kTour.dto.GetOthersTourlistsResDto;

public interface PlannerService {
    GetMyTourlistsResDto GetMyTourlists(GetMyTourlistsReqDto dto);
    GetOthersTourlistsResDto GetOthersTourlists(GetOthersTourlistsReqDto dto);
}

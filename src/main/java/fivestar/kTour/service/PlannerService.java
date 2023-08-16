package fivestar.kTour.service;

import fivestar.kTour.dto.*;

public interface PlannerService {
    GetMyTourlistsResDto GetMyTourlists(GetMyTourlistsDto dto);
    GetOthersTourlistsResDto GetOthersTourlists(GetOthersTourlistsDto dto);
    ResponseDto AddNewTourlist(AddNewTourlistDto dto);
    ResponseDto UpdateTourlist(UpdateTourlistDto dto);
}

package fivestar.kTour.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOthersTourlistsResDto {
    //user > plan > places 계층 구조를 가지기 떄문에 inner class 사용
    private String userEmail;
    private List<PlanPlacePair> planPlacePairs;

    public class PlanPlacePair{
        private String planName;
        private List<String> places;

        PlanPlacePair(String planName, List<String>places){
            this.planName=planName;
            this.places = places;
        }
    }
}

//{
//        "userEmail": "user@example.com",
//        "plans": [
//        {
//        "planName": "Plan 1",
//        "places": ["Place A", "Place B"]
//        },
//        {
//        "planName": "Plan 2",
//        "places": ["Place C", "Place D"]
//        }
//        ]
//        }
package fivestar.kTour.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMyPlansResDto {
    private List<PlanPlacePair> planPlacePairs;
    public static class PlanPlacePair{
        private String planName;
        private List<String> places;
        public PlanPlacePair(String planName, List<String> places){
            this.planName=planName;
            this.places = places;
        }

    }
}
// 이너클레스를 사용하면, 호출 할 때 List<GetMyPlansResDto>처럼 List를 반환할 필요없이 GetMyPlansResDto를 직접적으로 호출 할 수 있다.
// 이너클레스를 호출할 수 있도록 public우로 생성한다.
//{
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

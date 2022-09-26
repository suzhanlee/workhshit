package vo;

import lombok.*;

import java.sql.Timestamp;


@AllArgsConstructor
@Data
@NoArgsConstructor


public class HistoryVo {

    private String id;
    private Double lat;
    private Double lnt;
    private String date;
}
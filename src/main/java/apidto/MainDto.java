package apidto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class MainDto {

    @SerializedName("list_total_count")
    private int totalcount;

    @SerializedName("RESULT")
    private ResultDto result;

    @SerializedName("row")
    private List<RowDto> rowDtos;

    public List<RowDto> getRowDtos() {
        return rowDtos;
    }
}

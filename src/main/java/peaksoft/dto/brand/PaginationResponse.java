package peaksoft.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PaginationResponse {
     private List<BrandResponse> brandResponseList;
     private int currentPage;
     private int pageSize;

}

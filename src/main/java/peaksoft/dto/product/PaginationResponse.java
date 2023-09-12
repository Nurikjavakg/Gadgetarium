package peaksoft.dto.product;

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
     private List<ProductResponse> productResponseList;
     private int currentPage;
     private int pageSize;

}

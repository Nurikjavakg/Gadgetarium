package peaksoft.dto.favorite;

import lombok.Getter;
import lombok.Setter;
import peaksoft.entities.User;

import java.util.List;

@Getter
@Setter
public class FavoriteResponse {
    private Long favoriteId;
    private String productName;
    private String userFirstName;


    public FavoriteResponse(Long favoriteId, String productName, String userFirstName) {
        this.favoriteId = favoriteId;
        this.productName = productName;
        this.userFirstName = userFirstName;
    }
}

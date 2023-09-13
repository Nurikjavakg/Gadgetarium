package peaksoft.dto.favorite;

import lombok.Builder;
import peaksoft.entities.Comment;
import peaksoft.entities.Favorite;

public record FavoriteRequest(Long id) {
    public Favorite build() {
        Favorite favorite = new Favorite();
        favorite.setId(this.id);
        return favorite;
    }
}

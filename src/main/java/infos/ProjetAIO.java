package infos;

import com.bootcamp.entities.Media;
import com.bootcamp.helpers.ProjetWS;

import java.util.List;

public class ProjetAIO {
    private ProjetWS projet;
    private List<Media> medias;

    public ProjetAIO(ProjetWS projet, List<Media> medias) {
        this.projet = projet;
        this.medias = medias;
    }

    public ProjetWS getProjet() {
        return projet;
    }

    public void setProjet(ProjetWS projet) {
        this.projet = projet;
    }

    public List<Media> getMedias() {
        return medias;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }
}

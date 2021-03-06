package bussinesslogic;

import domain.Constancy;
import java.util.ArrayList;

/**
 *
 * @author Antonio de Jesús Dominguez García
 */
public interface IConstancyDAO {
    public ArrayList<Constancy> consultConstancyList();
    public Constancy checkConstancy(String RecognitionType);
    public Constancy generateConstancy(Constancy constancy);
}

import java.util.List;
import java.util.Map;

/*Domain class used as a container for our persistence data.
 *Used by the PendingDAOImpl class to get necessary user data.
 */
public class Pending {
    private List<Map<Pending, Pending>> list;

    public void setList(List<Map<Pending, Pending>> list) {
        this.list = list;
    }

    public List<Map<Pending, Pending>> getList() {
        return list;
    }

    public String toString() {
        return "Pending{" +
                "list=" + list +
                '}';
    }
}

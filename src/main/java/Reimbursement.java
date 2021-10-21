import java.util.List;
import java.util.Map;

public class Reimbursement {
    private List<Map<Reimbursement, Reimbursement>> list;

    public void setList(List<Map<Reimbursement, Reimbursement>> list) {
        this.list = list;
    }

    public List<Map<Reimbursement, Reimbursement>> getList() {
        return list;
    }

    public String toString() {
        return "Reimbursement{" +
                "list=" + list +
                '}';
    }
}

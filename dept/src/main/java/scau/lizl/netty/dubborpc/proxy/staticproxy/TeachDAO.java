package scau.lizl.netty.dubborpc.proxy.staticproxy;

public class TeachDAO implements ITeachDAO {
    @Override
    public void teach() {
        System.out.println("TeachDAO.teach()");
    }
}

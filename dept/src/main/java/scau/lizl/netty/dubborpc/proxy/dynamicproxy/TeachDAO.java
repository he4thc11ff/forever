package scau.lizl.netty.dubborpc.proxy.dynamicproxy;

public class TeachDAO implements ITeachDAO {
    @Override
    public void teach() {
        System.out.println("TeachDAO.teach()");
    }

    @Override
    public void miao() {
        System.out.println("miao");
    }
}

package scau.lizl.netty.dubborpc.proxy.staticproxy;

public class TeachDAOProxy implements ITeachDAO {

    private final TeachDAO targe;

    public TeachDAOProxy(TeachDAO targe) {
        this.targe = targe;
    }

    @Override
    public void teach() {
        //功能增强
        System.out.println("强了");
        targe.teach();
    }
}

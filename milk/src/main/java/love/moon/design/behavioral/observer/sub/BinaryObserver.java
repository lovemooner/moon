package love.moon.design.behavioral.observer.sub;

/**
 * @auther lovemooner
 * @date 2019/11/23 21:33
 * @describe
 */
public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}

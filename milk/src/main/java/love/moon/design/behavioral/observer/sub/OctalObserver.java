package love.moon.design.behavioral.observer.sub;

/**
 * @auther dongnan
 * @date 2019/11/23 21:34
 * @describe
 */
public class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}

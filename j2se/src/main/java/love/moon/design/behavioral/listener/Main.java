package love.moon.design.behavioral.listener;

import love.moon.design.behavioral.listener.event.ClickEvent;
import love.moon.design.behavioral.listener.event.DblClickEvent;
import love.moon.design.behavioral.listener.event.Event;
import love.moon.design.behavioral.listener.listener.ClickEventHandler;
import love.moon.design.behavioral.listener.listener.DblClickEventHandler;
import love.moon.design.behavioral.listener.source.Button;

/**
 * @author : ndong
 * date : 2020/11/4 14:28
 * desc :
 */
public class Main {

    public static void main(String[] args) {
        Button button = new Button();
        button.addEventListener(new ClickEventHandler() {
            @Override
            public void handleEvent(ClickEvent event) {
                System.out.println("Button was clicked!");
            }
        });
        button.addEventListener(new DblClickEventHandler() {
            @Override
            public void handleEvent(DblClickEvent event) {
                System.out.println("Button was double clicked!");
            }
        });

        Event clickEvent = new ClickEvent();
        button.notifyListeners(clickEvent);

        Event  dblClickEvent = new DblClickEvent();
        button.notifyListeners(dblClickEvent);
    }

}

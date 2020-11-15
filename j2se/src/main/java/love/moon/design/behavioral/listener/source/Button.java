package love.moon.design.behavioral.listener.source;

import love.moon.design.behavioral.listener.event.Event;
import love.moon.design.behavioral.listener.listener.EventListener;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : ndong
 * date : 2017/5/6 15:20
 * desc : 事件源。事件发生的地方，由于事件源的某项属性或状态发生了改变(比如BUTTON被单击、TEXTBOX的值发生改变等等)
 * 导致某项事件发生。生成了相应的事件对象
 */
public class Button implements EventSource {

    protected List<EventListener<? extends Event>> listeners = new LinkedList<>();

    @Override
    public void addEventListener(EventListener<? extends Event> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeEventListener(EventListener<? extends Event> listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListeners(Event event) {
        for (EventListener listener : listeners) {
            try {
                listener.handleEvent(event);
            } catch (ClassCastException e) {
            }
        }
    }

}

package love.moon.design.behavioral.listener.source;

import love.moon.design.behavioral.listener.event.Event;
import love.moon.design.behavioral.listener.listener.EventListener;

public interface EventSource {

    void addEventListener(EventListener<? extends Event> listener);

    void removeEventListener(EventListener<? extends Event> listener);

    void notifyListeners(Event event);

}
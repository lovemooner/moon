package love.moon.design.behavioral.listener.listener;

import love.moon.design.behavioral.listener.event.Event;

import java.util.EventObject;

/**
 * Created by lovemooner on 2017/5/6.
 */
public interface EventListener<T extends Event> {

     void handleEvent(T event);

}
package com.ando.base.eventbus;

import org.greenrobot.eventbus.EventBus;

public class EvenBusUtils {

    public static <T> void register(T event) {
        if (!EventBus.getDefault().isRegistered(event)) {
            EventBus.getDefault().register(event);
        }
    }

    public static <T > void unregister(T event) {
        if (EventBus.getDefault().isRegistered(event)) {
            EventBus.getDefault().unregister(event);
        }
    }

    public static <T extends EventBusMedium> void post(T event) {
        EventBus.getDefault().post(event);
    }

    public static <T extends EventBusMedium> void postSticky(T event) {
        EventBus.getDefault().postSticky(event);
    }

    public static void removeAllStickyEvents() {
        EventBus.getDefault().removeAllStickyEvents();
    }
}

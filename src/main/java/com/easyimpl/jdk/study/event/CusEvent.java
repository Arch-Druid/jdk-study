package com.easyimpl.jdk.study.event;

import java.util.EventObject;

/**
 * CusEvent
 *
 * @author yupeng10@baidu.com
 * @since 1.0
 */
public class CusEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CusEvent(Object source) {
        super(source);
    }
}

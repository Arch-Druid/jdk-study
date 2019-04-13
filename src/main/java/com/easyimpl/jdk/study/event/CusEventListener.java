package com.easyimpl.jdk.study.event;

import java.util.EventListener;

/**
 * CusEventListener
 *
 * @author yupeng10@baidu.com
 * @since 1.0
 */
public class CusEventListener implements EventListener {
    public void fireCusEvent(CusEvent e){
        Object source = e.getSource();
    }
}

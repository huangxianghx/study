package com.canal.disruptor;

import com.lmax.disruptor.RingBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @Autowired
    private DisruptorController disruptorController;
    /**
     * 发布事件
     */
    @RequestMapping("/send")
    public void send(@RequestParam long message){
        RingBuffer<LongEvent> ringBuffer = disruptorController.getDisruptor().getRingBuffer();
        long sequence = ringBuffer.next();//请求下一个事件序号；

        try {
            LongEvent event = ringBuffer.get(sequence);//获取该序号对应的事件对象；
            event.setValue(message);
        } finally{
            ringBuffer.publish(sequence);//发布事件；
        }
    }
}

package cn.calendo.tcmdistribution.controller.log;

import cn.calendo.tcmdistribution.common.TailfLogThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.InputStream;

/**
 * websocket控制层
 */
@RestController
@Slf4j
@ServerEndpoint("/log")
public class LogQuery {

    private Process process;

    private InputStream inputStream;

    @Value("${constants.logLocation}")
    private String logLocation;

    /**
     * 开启新websocket请求
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        try {
            process = Runtime.getRuntime().exec(logLocation);
            inputStream = process.getInputStream();
            TailfLogThread thread = new TailfLogThread(inputStream, session);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭websocket请求
     */
    @OnClose
    public void onClose(){
        try {
            if (inputStream!=null){
                inputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (process != null){
            process.destroy();
        }
    }

    @OnError
    public void onError(Throwable throwable){
        throwable.printStackTrace();
    }

}

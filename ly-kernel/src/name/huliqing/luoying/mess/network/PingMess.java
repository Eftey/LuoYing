/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.luoying.mess.network;

import com.jme3.network.serializing.Serializable;
import name.huliqing.luoying.mess.BaseMess;

/**
 * 向服务端发送Ping消息测试Ping值,这个消息不需要使用TCP方式，只要udp就可以.
 * @author huliqing
 */
 @Serializable
public class PingMess extends BaseMess {
    
    public PingMess() {
        super(false);
    }

    // remove201611xx
//    @Override
//    public void applyOnServer(GameServer gameServer, HostedConnection source) {
//        super.applyOnServer(gameServer, source);
//        // 服务端接收到这个消息后直接返回就可以，不需要作任何其它处理，也不要更
//        // 改任何MessPing中的数据。
//        source.send(this);
//    }
    
}

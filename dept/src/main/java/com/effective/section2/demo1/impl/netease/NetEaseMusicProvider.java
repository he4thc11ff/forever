package com.effective.section2.demo1.impl.netease;

import com.effective.section2.demo1.MusicApp;
import com.effective.section2.demo1.MusicProvider;


/**
 * 网易云音乐服务提供者实现
 * @author Administrator
 *
 */
public class NetEaseMusicProvider implements MusicProvider {
    @Override
    public MusicApp getMusicApp() {
        //逻辑代码，判断客户端
        boolean isDeviceAndroid = false;

        if (isDeviceAndroid) {
            return new NetEaseMusicApp();
        }else {
            return new NetEaseMusicPC();
        }
    }

}

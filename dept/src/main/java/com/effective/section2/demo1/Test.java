package com.effective.section2.demo1;

import com.effective.section2.demo1.impl.netease.NetEaseMusicProvider;
import com.effective.section2.demo1.impl.qq.QqMusicProvider;

public class Test {
    public static void main(String[] args) {
        //音乐服务商给AppStore注册他们的APP
        AppStore.registerProvider("NetEase", new NetEaseMusicProvider());
        AppStore.registerProvider("Qq", new QqMusicProvider());

        //用户通过AppStore下载APP获得实例
        MusicApp musicApp = AppStore.installApp("NetEase");
        musicApp.play();
        MusicApp musicAppQq = AppStore.installApp("Qq");
        musicAppQq.play();


    }
}

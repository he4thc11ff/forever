package com.effective.section2.demo1.impl.netease;

import com.effective.section2.demo1.MusicApp;

/**
 * 网易云音乐PC端实现
 * @author Administrator
 *
 */
public class NetEaseMusicPC implements MusicApp {
    @Override
    public void play() {
        System.out.println("网易云音乐，听见好时光~来自PC客户端");
    }

}

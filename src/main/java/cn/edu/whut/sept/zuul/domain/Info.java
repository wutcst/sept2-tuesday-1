package cn.edu.whut.sept.zuul.domain;

import cn.edu.whut.sept.zuul.game.Room;
import lombok.Data;

@Data
public class Info {
    private String res;
    private boolean wantQuit;
    private Room curRoom;

    public Info(String res, boolean wantQuit,Room cur) {
        this.res = res;
        this.wantQuit = wantQuit;
        this.curRoom = cur;
    }
}

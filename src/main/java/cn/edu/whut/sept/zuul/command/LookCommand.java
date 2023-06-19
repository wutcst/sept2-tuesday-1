package cn.edu.whut.sept.zuul.command;

import cn.edu.whut.sept.zuul.domain.Info;
import cn.edu.whut.sept.zuul.game.Room;

@Cmd("look")
/**
 * look命令的具体实现
 */
public class LookCommand implements Command{
    private String commandWord;//命令
    private String secondWord;//命令参数

    public LookCommand(String commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public LookCommand() {
    }

    public void setCommandWord(String commandWord) {
        this.commandWord = commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }

    @Override
    public Object processCommand(Object... args) {
        StringBuilder sb = new StringBuilder();
        Room room = (Room) args[0];
        sb.append(room.getLongDescription());
        sb.append("there are ");
        for (Object o:room.getGoods()){
            sb.append(o+",");
        }
        sb.append("in this room\n");
        return new Info(sb.toString(),false,null);
    }
}

package cn.edu.whut.sept.zuul.command;

import cn.edu.whut.sept.zuul.domain.Info;
import cn.edu.whut.sept.zuul.game.Room;

@Cmd("go")
/**
 * go命令的具体实现
 */
public class GoCommand implements Command{
    private String commandWord;//命令
    private String secondWord;//命令参数


    public GoCommand() {
    }

    public GoCommand(String commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    public String getCommandWord() {
        return commandWord;
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
        Room curRoom = null;
        StringBuilder sb = new StringBuilder();
        if(args!=null){
            curRoom = (Room) args[0];
            if(this.getSecondWord()==null || "".equals(this.getSecondWord())){
               sb.append("Go where?\n");
            }else{
                String direction = this.getSecondWord().trim();

                // Try to leave current room.
                Room nextRoom = curRoom.getExit(direction);

                if (nextRoom == null) {
                    sb.append("There is no door!\n");
                }
                else {
                    curRoom = nextRoom;
                   sb.append(curRoom.getLongDescription());
                }
            }
        }
        Info info = new Info(sb.toString(),false,curRoom);
        return info;
    }
}

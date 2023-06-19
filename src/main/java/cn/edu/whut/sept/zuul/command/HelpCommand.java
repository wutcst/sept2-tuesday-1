package cn.edu.whut.sept.zuul.command;

import cn.edu.whut.sept.zuul.domain.Info;
import cn.edu.whut.sept.zuul.game.CommandWords;
import cn.edu.whut.sept.zuul.game.Room;

@Cmd("help")
/**
 * help 命令的具体实现
 */
public class HelpCommand implements Command{
    private String commandWord;//命令
    private String secondWord;//命令参数

    public HelpCommand() {
    }

    public HelpCommand(String commandWord, String secondWord) {
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
        StringBuilder sb = new StringBuilder();
        sb.append("You are lost. You are alone. \n");
        Room cur = null;
        if(args[0]==null ||(cur=(Room) args[0])==null){
            sb.append("You wander around at the university.\n");
        }else{
            sb.append(cur.getLongDescription()+"\n");
        }
        sb.append("Your command words are:\n");
        CommandWords cmd = (CommandWords) args[1];
        sb.append(cmd.showAll());
        return new Info(sb.toString(),false,null);
    }
}

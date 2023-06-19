package cn.edu.whut.sept.zuul.command;

import cn.edu.whut.sept.zuul.domain.Info;
import cn.edu.whut.sept.zuul.game.CommandWords;

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
        sb.append("You are lost. You are alone. You wander\n");
        sb.append("around at the university.\n\n");
        sb.append("Your command words are:\n");
        CommandWords cmd = (CommandWords) args[1];
        sb.append(cmd.showAll()+"\n");
        return new Info(sb.toString(),false,null);
    }
}

package cn.edu.whut.sept.zuul.command;

import cn.edu.whut.sept.zuul.domain.Info;

@Cmd("quit")
/**
 * quit 命令的具体实现
 */
public class QuitCommand implements Command{
    private String commandWord;//命令
    private String secondWord;//命令参数

    public QuitCommand(String commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    public QuitCommand() {
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
        if(this.getSecondWord()!=null && !"".equals(this.getSecondWord())) {
            sb.append("Quit what?");
            return new Info(sb.toString(),false,null);
        }else {
            return new Info(sb.toString(),true,null);
        }
    }
}

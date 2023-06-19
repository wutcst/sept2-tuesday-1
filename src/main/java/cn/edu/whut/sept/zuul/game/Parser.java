package cn.edu.whut.sept.zuul.game;

import cn.edu.whut.sept.zuul.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * 该类负责将用户输入的命令转化为项目中的Command对象
 */
@Component
public class Parser
{
    @Autowired
    private CommandWords commands;//存储着项目中所有的命令

    public Parser()
    {
    }

    /**
     * 将用户输入的命令转化为Command对象
     * @return
     */
    public Command getCommand(String cmdStr)
    {
        String word1 = null;
        String word2 = null;

        word1 = cmdStr.split(" ")[0];
        word2 = cmdStr.replace(word1,"");

        HashMap<String, String> commands = this.commands.getCommands();
        if(commands.containsKey(word1)){
            Class<?> c = null;
            try {
                c = Class.forName(commands.get(word1));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Command cmd = null;
            try {
                cmd = (Command)c.getConstructor(String.class, String.class).newInstance(word1, word2);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return cmd;
        }else{
            System.out.println("invalid command.");
            return null;
        }
    }

    public CommandWords getCommands() {
        return commands;
    }

    public void showCommands()
    {
        commands.showAll();
    }
}

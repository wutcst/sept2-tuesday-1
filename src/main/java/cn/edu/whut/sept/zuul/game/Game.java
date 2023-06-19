/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 * <p>
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 * <p>
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author Michael Kölling and David J. Barnes
 * @version 1.0
 */
package cn.edu.whut.sept.zuul.game;

import cn.edu.whut.sept.zuul.command.Command;
import cn.edu.whut.sept.zuul.domain.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
@Component
public class Game {
    @Autowired
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> stack;
    private Room outside, theater, pub, lab, office;
    /**
     * 创建游戏并初始化内部数据和解析器.
     */
    public Game() {
        createRooms();
        stack = new Stack();
    }

    /**
     * 创建所有房间对象并连接其出口用以构建迷宫.
     */
    private void createRooms() {

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        outside.addGoods(new Item("energy drinks (can restore stamina)", 10, 3));
        theater = new Room("in a lecture theater");
        theater.addGoods(new Item("pain relievers (used to treat injuries)", 15, 4));
        pub = new Room("in the campus pub");
        pub.addGoods(new Item("hammer (can clear obstacles)", 35, 1));
        lab = new Room("in a computing lab");
        lab.addGoods(new Item("backpack (can hold things)", 10, 1));
        office = new Room("in the computing admin office");
        office.addGoods(new Item("food", 5, 10));
        theater.setSpecial(true);

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
    }

    /**
     *  游戏主控循环，直到用户输入退出命令后结束整个程序.
     */
//    public void play(String cmdStr) {
//        printWelcome();
//
//        // Enter the main command loop.  Here we repeatedly read commands and
//        // execute them until the game is over.
//
//        boolean finished = false;
//        while (!finished) {
//            Command command = parser.getCommand(cmdStr);
//            if (command != null) {
//                finished = processCommand(command).isWantQuit();
//            }
//        }
//        System.out.println("Thank you for playing.  Good bye.");
//    }

//    /**
//     * 向用户输出欢迎信息.
//     */
//    private void printWelcome() {
//        System.out.println();
//        System.out.println("Welcome to the World of Zuul!");
//        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
//        System.out.println("Type 'help' if you need help.");
//        System.out.println();
//        System.out.println(currentRoom.getLongDescription());
//    }

    /**
     * 执行用户输入的游戏指令.
     * @param command 待处理的游戏指令，由解析器从用户输入内容生成.
     * @return 如果执行的是游戏结束指令，则返回true，否则返回false.
     */
    public Info processCommand(Command command) {
        Object o = command.processCommand(currentRoom, parser.getCommands(), stack);
        Info infoObj = (Info) o;
        if(infoObj.getCurRoom()!=null){
            stack.add(currentRoom);
            currentRoom = infoObj.getCurRoom();
            if (currentRoom!=null&&currentRoom.isSpecial()) {
                HashMap<String, Room> exits = currentRoom.getExits();
                int size = exits.size();
                int random = (int) (Math.random() * size);
                int i = 0;
                for (Map.Entry<String, Room> en : exits.entrySet()) {
                    if (i == random) {//随机传送到下一个Room
                        currentRoom = exits.get(en.getKey());
                        String content = infoObj.getRes();
                        content+="current room is special you are taken to another Room.";
                        content+=currentRoom.getLongDescription();
                        infoObj.setRes(content);
                        break;
                    }
                    i++;
                }

            }
        }

        return infoObj;
    }
}
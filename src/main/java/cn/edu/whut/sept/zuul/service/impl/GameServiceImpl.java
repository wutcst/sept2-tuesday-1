package cn.edu.whut.sept.zuul.service.impl;

import cn.edu.whut.sept.zuul.domain.Info;
import cn.edu.whut.sept.zuul.domain.Result;
import cn.edu.whut.sept.zuul.game.Game;
import cn.edu.whut.sept.zuul.game.Parser;
import cn.edu.whut.sept.zuul.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private Game game;

    @Autowired
    private Parser parser;

    @Override
    public Result process(String cmd) {
        Info info = game.processCommand(parser.getCommand(cmd));
        if(info.isWantQuit()){
            return Result.success("bye bye!\n");
        }else{
            return Result.success(info.getRes());
        }
    }
}

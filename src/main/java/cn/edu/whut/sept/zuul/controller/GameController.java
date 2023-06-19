package cn.edu.whut.sept.zuul.controller;

import cn.edu.whut.sept.zuul.domain.Result;
import cn.edu.whut.sept.zuul.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;
    @PostMapping("/cmd")
    @ResponseBody
    public Result processCommand(@RequestBody HashMap map){
        return gameService.process((String) map.get("command"));
    }
}

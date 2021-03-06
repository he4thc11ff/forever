package scau.lzl.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import scau.lzl.rest.service.TrackService;

@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    TrackService trackService;

    @PostMapping("/user")
    public void track(@RequestParam long uid,
                      @RequestParam String referer,
                      @RequestParam int position) {

        trackService.sendToKafka(uid, referer, position);
    }
}

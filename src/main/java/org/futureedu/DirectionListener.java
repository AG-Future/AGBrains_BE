package org.futureedu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectionListener {
    private int direction = 8;
    @PostMapping("/set-direction")
    public void setDirection(@RequestParam int direction) {
        this.direction = direction;
    }
    @GetMapping("/get-direction")
    public String getDirection() {
        return "{\"direction\":" + direction + "}";
    }
}

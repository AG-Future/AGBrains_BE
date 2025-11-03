package org.futureedu.controller;

import lombok.RequiredArgsConstructor;
import org.futureedu.dto.Direction;
import org.futureedu.service.DirectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DirectionController {
    private final DirectionService directionService;

    @PostMapping("/set-direction")
    public ResponseEntity<Void> setDirection(@RequestParam String direction) {
        directionService.setDirection(direction);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-direction")
    public ResponseEntity<Direction> getDirection() {
        return ResponseEntity.ok(directionService.getDirection());
    }
}

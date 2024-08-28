package org.futureedu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectionListener {
    private Direction direction = Direction.stay;

    @PostMapping("/set-direction")
    public void setDirection(@RequestParam String direction) {
        this.direction = directionByString(direction);
    }

    @GetMapping("/get-direction")
    public String getDirection() {
        return "{\"direction\":" + direction.direction + "}";
    }

    public static Direction directionByString(String direction) {
        try {
            if (direction.matches("^(-1|[0-8])$")) return Direction.valueOf(Integer.parseInt(direction));
            return Direction.valueOf(direction);
        } catch (Throwable t) {
            return Direction.none;
        }
    }

    public enum Direction {
        up(0),
        up_right(1),
        right(2),
        down_right(3),
        down(4),
        down_left(5),
        left(6),
        up_left(7),
        stay(8),
        none(-1);
        public final int direction;
        Direction(int direction) {
            this.direction = direction;
        }
        public static Direction valueOf(int direction) {
            return switch (direction) {
                case 0 -> up;
                case 1 -> up_right;
                case 2 -> right;
                case 3 -> down_right;
                case 4 -> down;
                case 5 -> down_left;
                case 6 -> left;
                case 7 -> up_left;
                case 8 -> stay;
                default -> none;
            };
        }
    }
}

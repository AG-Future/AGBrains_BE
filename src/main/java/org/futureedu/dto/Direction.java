package org.futureedu.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Direction {
    private Position position;
    private long idlingTimeout;

    public enum Position {
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
        Position(int direction) {
            this.direction = direction;
        }
        public static Position valueOf(int direction) {
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

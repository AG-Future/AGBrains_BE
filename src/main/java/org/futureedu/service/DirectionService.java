package org.futureedu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.futureedu.component.WebSocketHandler;
import org.futureedu.dto.Direction;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Getter
@Service
@RequiredArgsConstructor
public class DirectionService {
    private final Logger logger = Logger.getLogger(DirectionService.class.getName());
    private final WebSocketHandler webSocketHandler;
    private final ObjectMapper objectMapper;
    private final Direction direction = Direction.builder().position(Direction.Position.none).idlingTimeout(0).build();

    public void setDirection(String direction) {
        try {
            Direction.Position position = positionByString(direction);
            if (this.direction.getPosition().equals(position)) return;
            this.direction.setPosition(positionByString(direction));
            this.direction.setIdlingTimeout(System.currentTimeMillis());

            webSocketHandler.sendToAll(objectMapper.writeValueAsString(this.direction));
        } catch (JsonProcessingException e) {
            logger.warning("Failed to send direction: " + e.getMessage());
        }
    }

    public static Direction.Position positionByString(String direction) {
        try {
            if (direction.matches("^(-1|[0-8])$")) return Direction.Position.valueOf(Integer.parseInt(direction));
            return Direction.Position.valueOf(direction);
        } catch (Throwable t) {
            return Direction.Position.none;
        }
    }
}

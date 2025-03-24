package kuke.board.common.outboxmessagerelay;


import kuke.board.common.event.Event;
import kuke.board.common.event.EventPayload;
import kuke.board.common.event.EventType;
import kuke.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutBoxEventPublisher {

    private final Snowflake outboxIdSnowflake = new Snowflake();
    private final Snowflake eventIdSnowflake = new Snowflake();
    private final ApplicationEventPublisher eventPublisher;

    public void publish(EventType eventType, EventPayload payload, Long shardKey) {
        OutBox outBox = OutBox.create(
                outboxIdSnowflake.nextId(),
                eventType,
                Event.of(
                        eventIdSnowflake.nextId(),
                        eventType,
                        payload
                ).toJson(),
                shardKey % MessageRelayConstants.SHARD_COUNT
        );

        eventPublisher.publishEvent(outBox);
    }
}

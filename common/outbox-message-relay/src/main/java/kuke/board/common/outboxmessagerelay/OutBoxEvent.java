package kuke.board.common.outboxmessagerelay;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OutBoxEvent {
    private OutBox outBox;

    public static OutBoxEvent from(OutBox outBox) {
        OutBoxEvent outBoxEvent = new OutBoxEvent();
        outBoxEvent.outBox = outBox;
        return outBoxEvent;
    }
}

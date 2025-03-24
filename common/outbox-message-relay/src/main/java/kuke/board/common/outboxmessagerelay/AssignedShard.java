package kuke.board.common.outboxmessagerelay;

import lombok.Getter;

import java.util.List;
import java.util.stream.LongStream;

/**
 * Created by IntelliJ IDEA.
 * 샤드를 균등하게 할당하는 클래스
 */

@Getter
public class AssignedShard {
    private List<Long> shards;

    public static AssignedShard of(String appId, List<String> appIds, long shardCount) {
        AssignedShard assignedShard = new AssignedShard();
        assignedShard.shards = assign(appId, appIds, shardCount);
        return assignedShard;
    }

    private static List<Long> assign(String appId, List<String> appIds, long shardCount) {
            int appIndex = findAppIndex(appId, appIds);
            if(appIndex == -1) {
                return List.of();
            }

            long start = appIndex * shardCount / appIds.size();
            long end = (appIndex + 1) * shardCount / appIds.size() - 1;

            return LongStream.rangeClosed(start, end).boxed().toList();
    }

    private static int findAppIndex(String appId, List<String> appIds) {
        for(int i=0; i < appIds.size(); i++) {
            if(appIds.get(i).equals(appId)) {
                return i;
            }
        }

        return -1;
    }
}

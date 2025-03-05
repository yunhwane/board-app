package kuke.board.comment.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;



class CommentPathTest {

    @Test
    void createChildCommentTest() {
        createChildCommentTest(CommentPath.create(""), null , "00000");
        createChildCommentTest(CommentPath.create("00000"), null, "0000000000");
        // 00000
        // 00001 생성
        createChildCommentTest(CommentPath.create(""), "00000", "00001");
        // 0000z
        //     abcdz
        //         zzzzz
        //             zzzzz
        //     abcd0
        createChildCommentTest(CommentPath.create("0000z"), "0000zabcdzzzzzzzzzzz", "0000zabce0");
    }

    @Test
    void createChildCommentPathIfMaxDepthTest() {
        assertThatThrownBy(() ->
                CommentPath.create("zzzzz".repeat(5)).createChildCommentPath(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createChildCommentPathIfChunkOverflowTest() {
        // given
        CommentPath commentPath = CommentPath.create("");

        // when, then
        assertThatThrownBy(
                () -> commentPath.createChildCommentPath("zzzzz")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    void createChildCommentTest(CommentPath commentPath, String descendantsTopPath, String expectedChildPath) {
        CommentPath childCommentPath = commentPath.createChildCommentPath(descendantsTopPath);
        assertThat(childCommentPath.getPath()).isEqualTo(expectedChildPath);
    }
}
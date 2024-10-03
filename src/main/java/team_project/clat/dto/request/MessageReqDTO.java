package team_project.clat.dto.request;

import lombok.Data;
import team_project.clat.domain.Memo;

@Data
public class MessageReqDTO {

    private Long chatRoomId;
    private String message;


    public MessageReqDTO(Long chatRoomId, String message) {
        this.chatRoomId = chatRoomId;
        this.message = message;
    }

    public MessageReqDTO() {
    }

    @Data
    public static class MessageAnswerReqDTO {
        private Long messageId;

        private Long chatRoomId;

        private String answer;

        public MessageAnswerReqDTO(Long messageId, Long chatRoomId, String answer) {
            this.messageId = messageId;
            this.chatRoomId = chatRoomId;
            this.answer = answer;
        }

        public MessageAnswerReqDTO() {
        }
    }

    @Data
    public static class MemoUpdateReqDTO {


        private Long memoId;

        private String updateMemoContent;

        public MemoUpdateReqDTO(Memo memo) {
            this.memoId = memo.getId();
            this.updateMemoContent = memo.getMemoContent();

        }

        public MemoUpdateReqDTO() {

        }
    }
}

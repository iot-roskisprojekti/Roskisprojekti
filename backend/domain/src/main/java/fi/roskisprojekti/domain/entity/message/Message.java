package fi.roskisprojekti.domain.entity.message;

import fi.roskisprojekti.domain.common.SingleTimeStamp;
import fi.roskisprojekti.domain.entity.task.TaskId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Message {
    private final TaskId taskId;
    private final MessageId messageId;
    private final SingleTimeStamp sentAt;
    private final MessageStatus messageStatus;
}

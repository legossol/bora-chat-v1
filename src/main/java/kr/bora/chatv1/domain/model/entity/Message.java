package kr.bora.chatv1.domain.model.entity;

import kr.bora.chatv1.common.BaseTime;
import kr.bora.chatv1.domain.reader.MessageEventListener;
import kr.legossol.borachat.common.BaseTime;
import kr.legossol.borachat.domain.reader.MessageEventListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(MessageEventListener.class)
@AttributeOverride(name = "createAt",column = @Column(name = "sended_time"))
public class Message extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    private String message;

    private String sender;



}

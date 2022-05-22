package kr.bora.chatv1.domain.model.entity;

import kr.bora.chatv1.common.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

//    private String users;



}

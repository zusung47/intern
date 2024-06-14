package com.tigensoft.intern.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    //메세지 번호
    private Integer messageId;
    //메세지 제목
    private String title;
    //메세지 내용
    private String content;
    //메세지 등록일
    private Date creationDate;
    //메세지 작성자
    private String creationUser;
    //수령인
    private String receiver;


    public MessageDTO(String title, String content, String receiver){
        this.title = title;
        this.content = content;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "messageId=" + messageId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", creationUser='" + creationUser + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}

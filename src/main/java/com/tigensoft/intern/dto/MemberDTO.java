package com.tigensoft.intern.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String userId;

    private String userPw;

    private String userName;

    private boolean enabled;

    private String auth;

    @Override
    public String toString() {
        return "MemberDTO{" +
                "userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userName='" + userName + '\'' +
                ", enabled=" + enabled +
                ", auth='" + auth + '\'' +
                '}';
    }
}

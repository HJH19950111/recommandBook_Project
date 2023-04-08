package com.project.recommandBook.dto;

import com.project.recommandBook.entity.UserInfo;
import com.project.recommandBook.enums.EnumUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
public class UserInfoDto {

    private Long id;

    @NotBlank(message = "이름을 입력하세요.")
    private String name;

    @NotBlank
    @Size(min = 5, max = 15, message = "5~15자 사이로 입력하세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 4, max = 10, message = "4~10자 사이로 입력하세요.")
    private String pw;

    /**
     * 정규식 사용 / 010,011,016,017,018,019 / \\d : 숫자 0~9
     */
    @NotBlank(message = "휴대전화 번호를 입력하세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력하세요.")
    private String phoneNumber;

    /**
     * LocalDate : yyyy-MM-dd / LocalTime : HH:mm:ss / LocalDateTime : yyyy-MM-ddTHH:mm:ss
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    private EnumUserRole userRole;

    public UserInfo toEntity() {
        return UserInfo.builder()
                .id(id)
                .name(name)
                .userId(userId)
                .pw(pw)
                .phoneNumber(phoneNumber)
                .createdAt(createdAt)
                .userRole(userRole.USER)
                .build();
    }
}

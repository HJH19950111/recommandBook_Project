package com.project.recommandBook.entity;

import com.project.recommandBook.enums.EnumUserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user_info",
        uniqueConstraints = {@UniqueConstraint(columnNames = "userInfo_id")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userInfo_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "user_Id", unique = false, nullable = false)
    private String userId;

    @Column(name = "password", unique = false, nullable = false)
    private String pw;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "phone_Number", unique = false, nullable = false)
    private String phoneNumber;

    @Column(name = "created_At", unique = false, nullable = true)
    @CreationTimestamp
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_Role", unique = false, nullable = false)
    private EnumUserRole userRole;

    @Builder
    public UserInfo(Long id, String userId, String pw, String name, String phoneNumber, Date createdAt, EnumUserRole userRole) {
        this.id = id;
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.userRole = userRole;
    }
}

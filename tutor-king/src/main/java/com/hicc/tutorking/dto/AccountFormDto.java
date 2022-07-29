package com.hicc.tutorking.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigInteger;

@Getter
@Setter
public class AccountFormDto {

    @NotBlank(message="이메일은 필수 입력 값입니다.")
    @Email(message="이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message="비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    @NotBlank(message="이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message="가입하려는 유형을 선택해주세요.")
    private String type;

    @PositiveOrZero(message="숫자를 입력해주세요.")
    @NotBlank(message="전화번호는 필수 입력 값입니다.")
    private String phone_number;
}

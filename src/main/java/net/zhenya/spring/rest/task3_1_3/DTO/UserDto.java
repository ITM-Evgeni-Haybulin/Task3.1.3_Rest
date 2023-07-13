package net.zhenya.spring.rest.task3_1_3.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String firstname;

    private String last_name;

    private long phoneNumber;

    private String password;
}

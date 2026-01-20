package dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private String email;
    private String password;
    private String firstname;
    private String lastname;

}

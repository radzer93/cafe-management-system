package org.radoslawzerek.cafemanagementsystem.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWrapper {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private String status;
}

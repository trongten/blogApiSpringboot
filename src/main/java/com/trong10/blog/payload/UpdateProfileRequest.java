package com.trong10.blog.payload;

import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfileRequest {

    @Null(groups = OptionalField.class)
    @Size(min = 4, max = 40)
    private String name;

    @Null(groups = OptionalField.class)
    @Size(min = 6, max = 20)
    private String password;
}

package com.trong10.blog.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String Content;
}

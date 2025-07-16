package com.furniturestorerestore.dto;

import com.furniturestorerestore.repository.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Status status;
    private Long userId;
}

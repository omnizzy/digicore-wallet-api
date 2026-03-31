package com.digicore.walletapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request object for creating a new wallet")
public class CreateWalletRequest {

    @Schema(
            description = "Unique identifier of the user",
            example = "user_12345",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "User ID must not be empty")
    private String userId;
}
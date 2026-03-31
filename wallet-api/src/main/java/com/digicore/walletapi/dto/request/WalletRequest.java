package com.digicore.walletapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Request object for wallet transactions (deposit/transfer)")
public class WalletRequest {

    @Schema(
            description = "Amount for the wallet transaction. Must be greater than 0.",
            example = "100.50",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;
}
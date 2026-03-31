package com.digicore.walletapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@Schema(description = "Response object representing a user's wallet")
public class WalletResponse {

    @Schema(
            description = "Unique identifier of the wallet",
            example = "wallet_12345",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String id;

    @Schema(
            description = "Unique identifier of the user owning the wallet",
            example = "user_12345",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String userId;

    @Schema(
            description = "Current balance of the wallet",
            example = "2500.75",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private BigDecimal balance;
}
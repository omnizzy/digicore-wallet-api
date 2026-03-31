package com.digicore.walletapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Response object representing a wallet transaction")
public class TransactionResponse {

    @Schema(
            description = "Unique identifier of the transaction",
            example = "txn_98765",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String id;

    @Schema(
            description = "Type of transaction (e.g., DEPOSIT, WITHDRAWAL, TRANSFER)",
            example = "DEPOSIT",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String type;

    @Schema(
            description = "Amount involved in the transaction",
            example = "1500.50",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private BigDecimal amount;

    @Schema(
            description = "Timestamp when the transaction occurred",
            example = "2026-03-19T14:25:30",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LocalDateTime timestamp;
}
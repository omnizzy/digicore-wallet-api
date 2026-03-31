package com.digicore.walletapi.controller;

import com.digicore.walletapi.dto.request.CreateWalletRequest;
import com.digicore.walletapi.dto.request.WalletRequest;
import com.digicore.walletapi.dto.response.TransactionResponse;
import com.digicore.walletapi.dto.response.WalletResponse;
import com.digicore.walletapi.dto.response.ApiResponse;
import com.digicore.walletapi.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
@Tag(name = "Wallet API", description = "Endpoints for managing wallets")
public class WalletController {

    private final WalletService walletService;

    @Operation(summary = "Create a new wallet", description = "Creates a wallet for a user with zero initial balance")
    @PostMapping
    public ResponseEntity<ApiResponse<WalletResponse>> createWallet(
            @Valid @RequestBody CreateWalletRequest request) {
        WalletResponse wallet = walletService.createWallet(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(wallet, "Wallet created successfully"));
    }

    @Operation(summary = "Fund wallet", description = "Add money to an existing wallet")
    @PostMapping("/{id}/fund")
    public ResponseEntity<ApiResponse<WalletResponse>> fundWallet(
            @Parameter(description = "Wallet ID to fund", required = true)
            @PathVariable("id") String walletId,
            @Valid @RequestBody WalletRequest request) {

        WalletResponse wallet = walletService.fundWallet(walletId, request);
        return ResponseEntity.ok(ApiResponse.success(wallet, "Wallet funded successfully"));
    }

    @Operation(summary = "Debit wallet", description = "Withdraw money from an existing wallet")
    @PostMapping("/{id}/debit")
    public ResponseEntity<ApiResponse<WalletResponse>> debitWallet(
            @Parameter(description = "Wallet ID to debit", required = true)
            @PathVariable("id") String walletId,
            @Valid @RequestBody WalletRequest request) {

        WalletResponse wallet = walletService.debitWallet(walletId, request);
        return ResponseEntity.ok(ApiResponse.success(wallet, "Wallet debited successfully"));
    }

    @Operation(summary = "Get wallet details", description = "Retrieve wallet information by wallet ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WalletResponse>> getWallet(
            @Parameter(description = "Wallet ID to retrieve", required = true)
            @PathVariable("id") String walletId) {

        WalletResponse wallet = walletService.getWallet(walletId);
        return ResponseEntity.ok(ApiResponse.success(wallet, "Wallet retrieved successfully"));
    }

    @Operation(summary = "Get wallet transactions", description = "Retrieve all transactions for a wallet")
    @GetMapping("/{id}/transactions")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransactions(
            @Parameter(description = "Wallet ID to fetch transactions for", required = true)
            @PathVariable("id") String walletId) {

        List<TransactionResponse> transactions = walletService.getTransactions(walletId);
        return ResponseEntity.ok(ApiResponse.success(transactions, "Transactions retrieved successfully"));
    }
}
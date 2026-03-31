package com.digicore.walletapi.utils;

import com.digicore.walletapi.dto.response.TransactionResponse;
import com.digicore.walletapi.dto.response.WalletResponse;
import com.digicore.walletapi.entity.Transaction;
import com.digicore.walletapi.entity.Wallet;

import java.util.Objects;

public class WalletMapper {

    private WalletMapper() {
    }
    public static WalletResponse toWalletResponse(Wallet wallet) {
        if (Objects.isNull(wallet)) return null;

        return WalletResponse.builder()
                .id(wallet.getId())
                .userId(wallet.getUserId())
                .balance(wallet.getBalance())
                .build();
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction) {
        if (Objects.isNull(transaction)) return null;

        return TransactionResponse.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .timestamp(transaction.getTimestamp())
                .build();
    }
}
package com.digicore.walletapi.service;


import com.digicore.walletapi.dto.request.CreateWalletRequest;
import com.digicore.walletapi.dto.request.WalletRequest;
import com.digicore.walletapi.dto.response.TransactionResponse;
import com.digicore.walletapi.dto.response.WalletResponse;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {

    WalletResponse createWallet(CreateWalletRequest request);

    WalletResponse fundWallet(String walletId, WalletRequest request);

    WalletResponse debitWallet(String walletId, WalletRequest request);

    WalletResponse getWallet(String walletId);

    List<TransactionResponse> getTransactions(String walletId);
}
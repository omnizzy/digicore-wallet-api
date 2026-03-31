package com.digicore.walletapi.service.impl;

import com.digicore.walletapi.Repository.TransactionRepository;
import com.digicore.walletapi.Repository.WalletRepository;
import com.digicore.walletapi.dto.request.CreateWalletRequest;
import com.digicore.walletapi.dto.request.WalletRequest;
import com.digicore.walletapi.dto.response.TransactionResponse;
import com.digicore.walletapi.dto.response.WalletResponse;
import com.digicore.walletapi.entity.Transaction;
import com.digicore.walletapi.entity.Wallet;
import com.digicore.walletapi.exception.InsufficientBalanceException;
import com.digicore.walletapi.exception.WalletNotFoundException;
import com.digicore.walletapi.service.WalletService;
import com.digicore.walletapi.utils.WalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public WalletResponse createWallet(CreateWalletRequest request) {
        Wallet wallet = new Wallet();
        wallet.setUserId(request.getUserId());
        wallet.setBalance(BigDecimal.ZERO);

        Wallet savedWallet = walletRepository.save(wallet);
        return WalletMapper.toWalletResponse(savedWallet);
    }

    @Override
    public WalletResponse fundWallet(String walletId, WalletRequest request) {
        Wallet wallet = getWalletOrThrow(walletId);
        validateAmount(request.getAmount());

        wallet.setBalance(wallet.getBalance().add(request.getAmount()));
        walletRepository.save(wallet);

        saveTransaction(walletId, "CREDIT", request.getAmount());

        return WalletMapper.toWalletResponse(wallet);
    }

    @Override
    public WalletResponse debitWallet(String walletId, WalletRequest request) {
        Wallet wallet = getWalletOrThrow(walletId);
        validateAmount(request.getAmount());

        if (wallet.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        wallet.setBalance(wallet.getBalance().subtract(request.getAmount()));
        walletRepository.save(wallet);

        saveTransaction(walletId, "DEBIT", request.getAmount());

        return WalletMapper.toWalletResponse(wallet);
    }

    @Override
    public WalletResponse getWallet(String walletId) {
        Wallet wallet = getWalletOrThrow(walletId);
        return WalletMapper.toWalletResponse(wallet);
    }

    @Override
    public List<TransactionResponse> getTransactions(String walletId) {
        List<Transaction> transactions = transactionRepository.findByWalletId(walletId);
        return transactions.stream()
                .map(WalletMapper::toTransactionResponse)
                .collect(Collectors.toList());
    }

    // Helpers

    private Wallet getWalletOrThrow(String id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }

    private void saveTransaction(String walletId, String type, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setWalletId(walletId);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
    }
}
package com.companion.transactions.unit.service;

import com.companion.transactions.model.Wallet;
import com.companion.transactions.repository.WalletRepository;
import com.companion.transactions.service.WalletService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

    private Wallet testWallet;
    private Long testUserId;
    private UUID testWalletId;

    @BeforeEach
    void setUp() {
        testUserId = 1L;
        testWalletId = UUID.randomUUID();
        testWallet = Wallet.builder()
                .id(testWalletId)
                .userId(testUserId)
                .balance(1000)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void getWalletByUserId_WhenWalletExists_ReturnsWallet() {
        // Arrange
        when(walletRepository.findByUserId(testUserId)).thenReturn(Optional.of(testWallet));

        // Act
        Wallet result = walletService.getWalletByUserId(testUserId);

        // Assert
        assertNotNull(result);
        assertEquals(testUserId, result.getUserId());
        assertEquals(testWalletId, result.getId());
        assertEquals(1000, result.getBalance());
        verify(walletRepository, times(1)).findByUserId(testUserId);
    }

    @Test
    void getWalletByUserId_WhenWalletDoesNotExist_ThrowsResourceNotFoundException() {
        // Arrange
        Long nonExistentUserId = 999L;
        when(walletRepository.findByUserId(nonExistentUserId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> walletService.getWalletByUserId(nonExistentUserId)
        );

        assertTrue(exception.getMessage().contains("Wallet not found for user " + nonExistentUserId));
        verify(walletRepository, times(1)).findByUserId(nonExistentUserId);
    }

    @Test
    void updateBalance_WhenWalletExists_UpdatesBalanceSuccessfully() {
        // Arrange
        int delta = 500;
        int expectedBalance = testWallet.getBalance() + delta;
        when(walletRepository.findByUserId(testUserId)).thenReturn(Optional.of(testWallet));
        when(walletRepository.save(any(Wallet.class))).thenReturn(testWallet);

        // Act
        walletService.updateBalance(testUserId, delta);

        // Assert
        assertEquals(expectedBalance, testWallet.getBalance());
        verify(walletRepository, times(1)).findByUserId(testUserId);
        verify(walletRepository, times(1)).save(testWallet);
    }

    @Test
    void updateBalance_WithNegativeDelta_DecreasesBalance() {
        // Arrange
        int delta = -300;
        int expectedBalance = testWallet.getBalance() + delta;
        when(walletRepository.findByUserId(testUserId)).thenReturn(Optional.of(testWallet));
        when(walletRepository.save(any(Wallet.class))).thenReturn(testWallet);

        // Act
        walletService.updateBalance(testUserId, delta);

        // Assert
        assertEquals(expectedBalance, testWallet.getBalance());
        verify(walletRepository, times(1)).findByUserId(testUserId);
        verify(walletRepository, times(1)).save(testWallet);
    }

    @Test
    void updateBalance_WhenWalletDoesNotExist_ThrowsResourceNotFoundException() {
        // Arrange
        Long nonExistentUserId = 999L;
        when(walletRepository.findByUserId(nonExistentUserId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> walletService.updateBalance(nonExistentUserId, 100)
        );
        verify(walletRepository, times(1)).findByUserId(nonExistentUserId);
        verify(walletRepository, never()).save(any(Wallet.class));
    }

    @Test
    void createWallet_CreatesNewWalletSuccessfully() {
        // Arrange
        Wallet newWallet = new Wallet();
        newWallet.setUserId(testUserId);
        newWallet.setId(testWalletId);
        newWallet.setBalance(0);

        when(walletRepository.save(any(Wallet.class))).thenReturn(newWallet);

        // Act
        Wallet result = walletService.createWallet(testUserId);

        // Assert
        assertNotNull(result);
        assertEquals(testUserId, result.getUserId());
        verify(walletRepository, times(1)).save(any(Wallet.class));
    }

    @Test
    void createWallet_SetsCorrectUserId() {
        // Arrange
        Long newUserId = 42L;
        Wallet newWallet = new Wallet();
        newWallet.setUserId(newUserId);

        when(walletRepository.save(any(Wallet.class))).thenReturn(newWallet);

        // Act
        Wallet result = walletService.createWallet(newUserId);

        // Assert
        assertNotNull(result);
        assertEquals(newUserId, result.getUserId());
        verify(walletRepository, times(1)).save(any(Wallet.class));
    }

    @Test
    void deleteWallet_WhenWalletExists_DeletesSuccessfully() {
        // Arrange
        when(walletRepository.findByUserId(testUserId)).thenReturn(Optional.of(testWallet));
        doNothing().when(walletRepository).delete(testWallet);

        // Act
        walletService.deleteWallet(testUserId);

        // Assert
        verify(walletRepository, times(1)).findByUserId(testUserId);
        verify(walletRepository, times(1)).delete(testWallet);
    }

    @Test
    void deleteWallet_WhenWalletDoesNotExist_ThrowsResourceNotFoundException() {
        // Arrange
        Long nonExistentUserId = 999L;
        when(walletRepository.findByUserId(nonExistentUserId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> walletService.deleteWallet(nonExistentUserId)
        );
        verify(walletRepository, times(1)).findByUserId(nonExistentUserId);
        verify(walletRepository, never()).delete(any(Wallet.class));
    }
}
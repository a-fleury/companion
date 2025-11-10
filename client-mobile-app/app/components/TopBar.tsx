import { Ionicons } from '@expo/vector-icons';
import React from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { mockCurrentUser } from '../data/mockData';

interface TopBarProps {
  onWalletClick?: () => void;
  onNotificationsClick?: () => void;
  onMessagesClick?: () => void;
}

export function TopBar({ onWalletClick, onNotificationsClick, onMessagesClick }: TopBarProps = {}) {
  return (
    <View style={styles.container}>
      <View style={styles.content}>
        {/* Logo */}
        <View style={styles.logoContainer}>
          <View style={styles.logoPlaceholder}>
            <Text style={styles.logoText}>C</Text>
          </View>
          <Text style={styles.title}>Companion</Text>
        </View>

        {/* Actions */}
        <View style={styles.actionsContainer}>
          {/* Wallet Balance */}
          <TouchableOpacity 
            onPress={onWalletClick}
            style={styles.walletButton}
            activeOpacity={0.7}
          >
            <View style={styles.coinIcon}>
              <Text style={styles.coinEmoji}>💰</Text>
            </View>
            <Text style={styles.balanceText}>{mockCurrentUser.walletBalance.toLocaleString()}</Text>
          </TouchableOpacity>

          <TouchableOpacity 
            onPress={onNotificationsClick}
            style={styles.iconButton}
            activeOpacity={0.7}
          >
            <Ionicons name="notifications-outline" size={24} color="#000" />
            <View style={[styles.badge, styles.redBadge]} />
          </TouchableOpacity>
          
          <TouchableOpacity 
            onPress={onMessagesClick}
            style={styles.iconButton}
            activeOpacity={0.7}
          >
            <Ionicons name="chatbubble-outline" size={24} color="#000" />
            <View style={[styles.badge, styles.blueBadge]} />
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#fff',
    borderBottomWidth: 1,
    borderBottomColor: '#f3f4f6',
  },
  content: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingHorizontal: 16,
    paddingVertical: 16,
  },
  logoContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 12,
  },
  logoPlaceholder: {
    width: 48,
    height: 48,
    borderRadius: 24,
    backgroundColor: '#3b82f6',
    alignItems: 'center',
    justifyContent: 'center',
  },
  logoText: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#fff',
  },
  title: {
    fontSize: 20,
    fontWeight: '600',
  },
  actionsContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 12,
  },
  walletButton: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    backgroundColor: '#fef3c7',
    paddingHorizontal: 12,
    paddingVertical: 8,
    borderRadius: 20,
    borderWidth: 1,
    borderColor: '#fde68a',
  },
  coinIcon: {
    width: 24,
    height: 24,
    borderRadius: 12,
    alignItems: 'center',
    justifyContent: 'center',
  },
  coinEmoji: {
    fontSize: 16,
  },
  balanceText: {
    fontSize: 14,
    fontWeight: '600',
  },
  iconButton: {
    padding: 8,
    position: 'relative',
  },
  badge: {
    position: 'absolute',
    top: 6,
    right: 6,
    width: 8,
    height: 8,
    borderRadius: 4,
  },
  redBadge: {
    backgroundColor: '#ef4444',
  },
  blueBadge: {
    backgroundColor: '#3b82f6',
  },
});
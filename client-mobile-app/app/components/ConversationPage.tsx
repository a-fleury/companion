import { Ionicons } from '@expo/vector-icons';
import React from 'react';
import { Image, ScrollView, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { categories, mockConversations } from '../data/mockData';
import { Conversation } from '../types';

interface ConversationsPageProps {
  onBack: () => void;
  onSelectConversation: (conversation: Conversation) => void;
}

export function ConversationsPage({ onBack, onSelectConversation }: ConversationsPageProps) {
  const getCategoryInfo = (categoryId: string) => {
    const category = categories.find(c => c.id === categoryId);
    return category || { name: 'Unknown', color: '#6B7280', bgColor: '#F3F4F6', icon: '📍' };
  };

  const formatTimestamp = (timestamp: string) => {
    const date = new Date(timestamp);
    const now = new Date();
    const diffMs = now.getTime() - date.getTime();
    const diffMins = Math.floor(diffMs / 60000);
    const diffHours = Math.floor(diffMs / 3600000);
    const diffDays = Math.floor(diffMs / 86400000);

    if (diffMins < 60) {
      return `${diffMins}m ago`;
    } else if (diffHours < 24) {
      return `${diffHours}h ago`;
    } else if (diffDays < 7) {
      return `${diffDays}d ago`;
    } else {
      return date.toLocaleDateString('en-US', { month: 'short', day: 'numeric' });
    }
  };

  return (
    <View style={styles.container}>
      {/* Header */}
      <View style={styles.header}>
        <View style={styles.headerContent}>
          <TouchableOpacity
            onPress={onBack}
            style={styles.backButton}
            activeOpacity={0.7}
          >
            <Ionicons name="arrow-back" size={24} color="#000" />
          </TouchableOpacity>
          <Text style={styles.headerTitle}>Messages</Text>
        </View>
      </View>

      {/* Content */}
      <ScrollView style={styles.scrollView} contentContainerStyle={styles.scrollContent}>
        {mockConversations.length === 0 ? (
          <View style={styles.emptyContainer}>
            <Text style={styles.emptyIcon}>💬</Text>
            <Text style={styles.emptyTitle}>No messages yet</Text>
            <Text style={styles.emptyDescription}>
              Start chatting with companions to see your conversations here.
            </Text>
          </View>
        ) : (
          <View style={styles.conversationsList}>
            {mockConversations.map((conversation, index) => {
              const categoryInfo = getCategoryInfo(conversation.category || 'romantic');
              
              return (
                <TouchableOpacity
                  key={conversation.id}
                  onPress={() => onSelectConversation(conversation)}
                  style={[
                    styles.conversationItem,
                    conversation.unread && styles.conversationItemUnread,
                    index !== 0 && styles.conversationItemBorder
                  ]}
                  activeOpacity={0.7}
                >
                  {/* Profile Image with Category Border */}
                  <View style={styles.imageContainer}>
                    <View 
                      style={[
                        styles.imageBorder,
                        { backgroundColor: categoryInfo.color }
                      ]}
                    >
                      <View style={styles.imageInnerBorder} />
                    </View>
                    <Image
                      source={{ uri: conversation.companionImage }}
                      style={styles.profileImage}
                    />
                  </View>

                  {/* Content */}
                  <View style={styles.conversationContent}>
                    <View style={styles.conversationHeader}>
                      <Text style={styles.companionName} numberOfLines={1}>
                        {conversation.companionName}
                      </Text>
                      <Text style={styles.timestamp}>
                        {formatTimestamp(conversation.timestamp || conversation.lastMessageTime || '')}
                      </Text>
                    </View>
                    <Text 
                      style={[
                        styles.lastMessage,
                        conversation.unread && styles.lastMessageUnread
                      ]}
                      numberOfLines={1}
                    >
                      {conversation.lastMessage}
                    </Text>
                  </View>

                  {/* Unread indicator */}
                  {conversation.unread && (
                    <View style={styles.unreadIndicator} />
                  )}
                </TouchableOpacity>
              );
            })}
          </View>
        )}
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  header: {
    backgroundColor: '#fff',
    borderBottomWidth: 1,
    borderBottomColor: '#e5e7eb',
    paddingHorizontal: 16,
    paddingVertical: 16,
  },
  headerContent: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 12,
  },
  backButton: {
    padding: 8,
    borderRadius: 12,
  },
  headerTitle: {
    fontSize: 20,
    fontWeight: '600',
  },
  scrollView: {
    flex: 1,
  },
  scrollContent: {
    paddingBottom: 24,
  },
  emptyContainer: {
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: 64,
    paddingHorizontal: 16,
  },
  emptyIcon: {
    fontSize: 60,
    marginBottom: 16,
  },
  emptyTitle: {
    fontSize: 20,
    fontWeight: '600',
    marginBottom: 8,
  },
  emptyDescription: {
    fontSize: 14,
    color: '#6b7280',
    textAlign: 'center',
  },
  conversationsList: {
    flex: 1,
  },
  conversationItem: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 16,
    paddingVertical: 16,
    gap: 12,
  },
  conversationItemUnread: {
    backgroundColor: '#eff6ff',
  },
  conversationItemBorder: {
    borderTopWidth: 1,
    borderTopColor: '#f3f4f6',
  },
  imageContainer: {
    position: 'relative',
    width: 56,
    height: 56,
  },
  imageBorder: {
    position: 'absolute',
    inset: 0,
    borderRadius: 28,
    padding: 2,
  },
  imageInnerBorder: {
    width: '100%',
    height: '100%',
    borderRadius: 26,
    backgroundColor: '#fff',
  },
  profileImage: {
    position: 'absolute',
    width: 56,
    height: 56,
    borderRadius: 28,
  },
  conversationContent: {
    flex: 1,
    minWidth: 0,
  },
  conversationHeader: {
    flexDirection: 'row',
    alignItems: 'baseline',
    justifyContent: 'space-between',
    gap: 8,
    marginBottom: 4,
  },
  companionName: {
    fontSize: 14,
    fontWeight: '500',
    flex: 1,
  },
  timestamp: {
    fontSize: 12,
    color: '#6b7280',
  },
  lastMessage: {
    fontSize: 14,
    color: '#6b7280',
  },
  lastMessageUnread: {
    color: '#111827',
    fontWeight: '500',
  },
  unreadIndicator: {
    width: 8,
    height: 8,
    borderRadius: 4,
    backgroundColor: '#3b82f6',
  },
});

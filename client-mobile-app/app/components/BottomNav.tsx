import { Ionicons } from '@expo/vector-icons';
import React from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';

type NavPage = 'explore' | 'groups' | 'create' | 'favorites' | 'profile';

interface BottomNavProps {
  readonly active: NavPage;
  readonly onNavigate: (page: NavPage) => void;
}

interface NavItem {
  id: NavPage;
  icon: keyof typeof Ionicons.glyphMap;
  label: string;
}

export function BottomNav({ active, onNavigate }: BottomNavProps) {
  const navItems: NavItem[] = [
    { id: 'explore', icon: 'compass-outline', label: 'Explore' },
    { id: 'groups', icon: 'people-outline', label: 'Groups' },
    { id: 'create', icon: 'add-circle-outline', label: 'Create' },
    { id: 'favorites', icon: 'heart-outline', label: 'Favorites' },
    { id: 'profile', icon: 'person-outline', label: 'Profile' },
  ];

  return (
    <View style={styles.container}>
      {navItems.map((item) => {
        const isActive = active === item.id;
        return (
          <TouchableOpacity
            key={item.id}
            onPress={() => onNavigate(item.id)}
            style={styles.navItem}
            activeOpacity={0.7}
          >
            <Ionicons
              name={item.icon}
              size={24}
              color={isActive ? '#3B82F6' : '#9CA3AF'}
            />
            <Text
              style={[
                styles.navLabel,
                { color: isActive ? '#3B82F6' : '#9CA3AF' }
              ]}
            >
              {item.label}
            </Text>
          </TouchableOpacity>
        );
      })}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    flexDirection: 'row',
    backgroundColor: '#fff',
    borderTopWidth: 1,
    borderTopColor: '#F3F4F6',
    paddingBottom: 8,
    paddingTop: 8,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: -2 },
    shadowOpacity: 0.1,
    shadowRadius: 8,
    elevation: 5,
  },
  navItem: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: 8,
  },
  navLabel: {
    fontSize: 12,
    marginTop: 4,
    fontWeight: '500',
  },
});

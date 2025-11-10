import { Ionicons } from '@expo/vector-icons';
import React from 'react';
import { StyleSheet, TextInput, TouchableOpacity, View } from 'react-native';

interface SearchBarProps {
  readonly value: string;
  readonly onChange: (text: string) => void;
  readonly onFilterClick: () => void;
}

export function SearchBar({ value, onChange, onFilterClick }: SearchBarProps) {
  return (
    <View style={styles.container}>
      <View style={styles.searchContainer}>
        <Ionicons name="search" size={20} color="#9CA3AF" style={styles.searchIcon} />
        <TextInput
          style={styles.input}
          placeholder="Search companions..."
          value={value}
          onChangeText={onChange}
          placeholderTextColor="#9CA3AF"
        />
      </View>
      <TouchableOpacity 
        onPress={onFilterClick}
        style={styles.filterButton}
        activeOpacity={0.7}
      >
        <Ionicons name="options-outline" size={24} color="#000" />
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 12,
    paddingHorizontal: 16,
    paddingVertical: 12,
  },
  searchContainer: {
    flex: 1,
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#F3F4F6',
    borderRadius: 12,
    paddingHorizontal: 12,
    paddingVertical: 10,
  },
  searchIcon: {
    marginRight: 8,
  },
  input: {
    flex: 1,
    fontSize: 16,
    color: '#000',
  },
  filterButton: {
    padding: 8,
    backgroundColor: '#F3F4F6',
    borderRadius: 12,
  },
});

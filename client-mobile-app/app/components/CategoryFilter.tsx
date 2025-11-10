import React from 'react';
import { ScrollView, StyleSheet, Text, TouchableOpacity } from 'react-native';
import { categories } from '../data/mockData';
import { CategoryType } from '../types';

interface CategoryFilterProps {
  readonly selectedCategory: CategoryType | 'all';
  readonly onSelectCategory: (category: CategoryType | 'all') => void;
}

export function CategoryFilter({ selectedCategory, onSelectCategory }: CategoryFilterProps) {
  const allCategory = {
    id: 'all' as const,
    name: 'All',
    color: '#6B7280',
    bgColor: '#F3F4F6',
    icon: '🌟',
  };

  const allCategories = [allCategory, ...categories];

  return (
    <ScrollView 
      horizontal 
      showsHorizontalScrollIndicator={false}
      contentContainerStyle={styles.scrollContent}
    >
      {allCategories.map((category) => {
        const isSelected = selectedCategory === category.id;
        return (
          <TouchableOpacity
            key={category.id}
            onPress={() => onSelectCategory(category.id)}
            style={[
              styles.categoryButton,
              { 
                backgroundColor: isSelected ? category.color : category.bgColor,
                borderColor: category.color,
              }
            ]}
            activeOpacity={0.7}
          >
            <Text style={styles.categoryIcon}>{category.icon}</Text>
            <Text 
              style={[
                styles.categoryText,
                { color: isSelected ? '#fff' : category.color }
              ]}
            >
              {category.name}
            </Text>
          </TouchableOpacity>
        );
      })}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  scrollContent: {
    paddingVertical: 4,
    gap: 8,
  },
  categoryButton: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 16,
    paddingVertical: 10,
    borderRadius: 20,
    borderWidth: 1,
    gap: 6,
  },
  categoryIcon: {
    fontSize: 16,
  },
  categoryText: {
    fontSize: 14,
    fontWeight: '600',
  },
});

import React, { useState } from 'react';
import { ScrollView, StyleSheet, Text, View } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { mockPersons } from '../data/mockData';
import type { CategoryType, FilterValues, Person } from '../types';
import { BottomNav } from './BottomNav';
import { CategoryFilter } from './CategoryFilter';
import { FilterSheet } from './FilterSheet';
import { PersonCard } from './PersonCard';
import { SearchBar } from './SearchBar';
import { TopBar } from './TopBar';

interface ExplorePageProps {
  readonly onNavigate: (page: 'explore' | 'groups' | 'create' | 'favorites' | 'profile') => void;
  readonly onOpenShop: () => void;
  readonly onPersonClick: (person: Person) => void;
  readonly onOpenNotifications?: () => void;
  readonly onOpenMessages?: () => void;
}

export function ExplorePage({ onNavigate, onOpenShop, onPersonClick, onOpenNotifications, onOpenMessages }: ExplorePageProps) {
  const [searchQuery, setSearchQuery] = useState('');
  const [selectedCategory, setSelectedCategory] = useState<CategoryType | 'all'>('all');
  const [isFilterOpen, setIsFilterOpen] = useState(false);
  const [filters, setFilters] = useState<FilterValues>({
    availability: [],
    location: '',
    radius: 10,
    priceRange: [0, 1000],
  });

  // Filter persons based on search, category, and filters
  const filteredPersons = mockPersons.filter((person) => {
    const matchesSearch =
      searchQuery === '' ||
      person.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
      person.bio.toLowerCase().includes(searchQuery.toLowerCase()) ||
      person.location.toLowerCase().includes(searchQuery.toLowerCase());

    const matchesCategory =
      selectedCategory === 'all' || person.category === selectedCategory;

    const matchesPrice =
      person.price >= filters.priceRange[0] && person.price <= filters.priceRange[1];

    const matchesLocation =
      filters.location === '' ||
      person.location.toLowerCase().includes(filters.location.toLowerCase());

    return matchesSearch && matchesCategory && matchesPrice && matchesLocation;
  });

  const handleApplyFilters = (newFilters: FilterValues) => {
    setFilters(newFilters);
  };

  return (
    <SafeAreaView style={styles.container}>
      {/* Top Bar */}
      <TopBar 
        onWalletClick={onOpenShop}
        onNotificationsClick={onOpenNotifications}
        onMessagesClick={onOpenMessages}
      />

      {/* Main Content */}
      <ScrollView 
        style={styles.scrollView}
        contentContainerStyle={styles.scrollContent}
      >
        {/* Search Bar */}
        <SearchBar
          value={searchQuery}
          onChange={setSearchQuery}
          onFilterClick={() => setIsFilterOpen(true)}
        />

        {/* Category Filter */}
        <View style={styles.categorySection}>
          <CategoryFilter
            selectedCategory={selectedCategory}
            onSelectCategory={setSelectedCategory}
          />
        </View>

        {/* Results Count */}
        <View style={styles.resultsCount}>
          <Text style={styles.resultsText}>
            {filteredPersons.length} {filteredPersons.length === 1 ? 'companion' : 'companions'} available
          </Text>
        </View>

        {/* Person List */}
        <View style={styles.personList}>
          {filteredPersons.map((person) => (
            <PersonCard
              key={person.id}
              person={person}
              onClick={() => onPersonClick(person)}
            />
          ))}
        </View>

        {/* Empty State */}
        {filteredPersons.length === 0 && (
          <View style={styles.emptyState}>
            <Text style={styles.emptyIcon}>🔍</Text>
            <Text style={styles.emptyTitle}>No companions found</Text>
            <Text style={styles.emptyText}>
              Try adjusting your search or filters to find the perfect companion
            </Text>
          </View>
        )}
      </ScrollView>

      {/* Bottom Navigation */}
      <BottomNav active="explore" onNavigate={onNavigate} />

      {/* Filter Sheet */}
      <FilterSheet
        isOpen={isFilterOpen}
        onClose={() => setIsFilterOpen(false)}
        onApplyFilters={handleApplyFilters}
      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  scrollView: {
    flex: 1,
  },
  scrollContent: {
    paddingBottom: 100,
  },
  categorySection: {
    paddingHorizontal: 16,
    marginBottom: 16,
  },
  resultsCount: {
    paddingHorizontal: 16,
    marginBottom: 16,
  },
  resultsText: {
    fontSize: 14,
    color: '#6B7280',
  },
  personList: {
    paddingHorizontal: 16,
    gap: 16,
  },
  emptyState: {
    flex: 1,
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
  emptyText: {
    fontSize: 16,
    color: '#6B7280',
    textAlign: 'center',
  },
});
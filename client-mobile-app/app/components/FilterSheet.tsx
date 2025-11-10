import { Ionicons } from '@expo/vector-icons';
import React, { useState } from 'react';
import {
    Modal,
    ScrollView,
    StyleSheet,
    Text,
    TextInput,
    TouchableOpacity,
    View,
} from 'react-native';
import { FilterValues } from '../types';

interface FilterSheetProps {
  readonly isOpen: boolean;
  readonly onClose: () => void;
  readonly onApplyFilters: (filters: FilterValues) => void;
}

export function FilterSheet({ isOpen, onClose, onApplyFilters }: FilterSheetProps) {
  const [filters, setFilters] = useState<FilterValues>({
    availability: [],
    location: '',
    radius: 10,
    priceRange: [0, 1000],
  });

  const availabilityOptions = ['Weekdays', 'Weekends', 'Evenings', 'Mornings'];

  const toggleAvailability = (option: string) => {
    setFilters(prev => ({
      ...prev,
      availability: prev.availability.includes(option)
        ? prev.availability.filter(a => a !== option)
        : [...prev.availability, option],
    }));
  };

  const handleApply = () => {
    onApplyFilters(filters);
    onClose();
  };

  const handleReset = () => {
    setFilters({
      availability: [],
      location: '',
      radius: 10,
      priceRange: [0, 1000],
    });
  };

  return (
    <Modal
      visible={isOpen}
      animationType="slide"
      transparent={true}
      onRequestClose={onClose}
    >
      <View style={styles.overlay}>
        <View style={styles.content}>
          {/* Header */}
          <View style={styles.header}>
            <Text style={styles.title}>Filters</Text>
            <TouchableOpacity onPress={onClose} style={styles.closeButton}>
              <Ionicons name="close" size={24} color="#000" />
            </TouchableOpacity>
          </View>

          <ScrollView style={styles.scrollView}>
            {/* Availability */}
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Availability</Text>
              <View style={styles.optionsGrid}>
                {availabilityOptions.map((option) => {
                  const isSelected = filters.availability.includes(option);
                  return (
                    <TouchableOpacity
                      key={option}
                      onPress={() => toggleAvailability(option)}
                      style={[
                        styles.optionChip,
                        isSelected && styles.optionChipSelected,
                      ]}
                      activeOpacity={0.7}
                    >
                      <Text
                        style={[
                          styles.optionText,
                          isSelected && styles.optionTextSelected,
                        ]}
                      >
                        {option}
                      </Text>
                    </TouchableOpacity>
                  );
                })}
              </View>
            </View>

            {/* Location */}
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Location</Text>
              <TextInput
                style={styles.input}
                placeholder="Enter location..."
                value={filters.location}
                onChangeText={(text) => setFilters(prev => ({ ...prev, location: text }))}
                placeholderTextColor="#9CA3AF"
              />
            </View>

            {/* Radius */}
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Radius: {filters.radius} km</Text>
              <View style={styles.radiusContainer}>
                <Text style={styles.radiusLabel}>5 km</Text>
                <View style={styles.sliderPlaceholder}>
                  <Text style={styles.sliderText}>Slider (5-50 km)</Text>
                </View>
                <Text style={styles.radiusLabel}>50 km</Text>
              </View>
            </View>

            {/* Price Range */}
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>
                Price Range: {filters.priceRange[0]} - {filters.priceRange[1]} 💰
              </Text>
              <View style={styles.priceInputsContainer}>
                <View style={styles.priceInputWrapper}>
                  <Text style={styles.priceLabel}>Min</Text>
                  <TextInput
                    style={styles.priceInput}
                    value={filters.priceRange[0].toString()}
                    onChangeText={(text) => {
                      const value = Number.parseInt(text) || 0;
                      setFilters(prev => ({
                        ...prev,
                        priceRange: [value, prev.priceRange[1]],
                      }));
                    }}
                    keyboardType="numeric"
                    placeholderTextColor="#9CA3AF"
                  />
                </View>
                <View style={styles.priceInputWrapper}>
                  <Text style={styles.priceLabel}>Max</Text>
                  <TextInput
                    style={styles.priceInput}
                    value={filters.priceRange[1].toString()}
                    onChangeText={(text) => {
                      const value = Number.parseInt(text) || 1000;
                      setFilters(prev => ({
                        ...prev,
                        priceRange: [prev.priceRange[0], value],
                      }));
                    }}
                    keyboardType="numeric"
                    placeholderTextColor="#9CA3AF"
                  />
                </View>
              </View>
            </View>
          </ScrollView>

          {/* Footer */}
          <View style={styles.footer}>
            <TouchableOpacity
              onPress={handleReset}
              style={styles.resetButton}
              activeOpacity={0.7}
            >
              <Text style={styles.resetButtonText}>Reset</Text>
            </TouchableOpacity>
            <TouchableOpacity
              onPress={handleApply}
              style={styles.applyButton}
              activeOpacity={0.7}
            >
              <Text style={styles.applyButtonText}>Apply Filters</Text>
            </TouchableOpacity>
          </View>
        </View>
      </View>
    </Modal>
  );
}

const styles = StyleSheet.create({
  overlay: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    justifyContent: 'flex-end',
  },
  content: {
    backgroundColor: '#fff',
    borderTopLeftRadius: 24,
    borderTopRightRadius: 24,
    maxHeight: '90%',
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingVertical: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#F3F4F6',
  },
  title: {
    fontSize: 20,
    fontWeight: '600',
  },
  closeButton: {
    padding: 4,
  },
  scrollView: {
    paddingHorizontal: 20,
  },
  section: {
    paddingVertical: 20,
    borderBottomWidth: 1,
    borderBottomColor: '#F3F4F6',
  },
  sectionTitle: {
    fontSize: 16,
    fontWeight: '600',
    marginBottom: 12,
  },
  optionsGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    gap: 8,
  },
  optionChip: {
    paddingHorizontal: 16,
    paddingVertical: 10,
    borderRadius: 20,
    backgroundColor: '#F3F4F6',
    borderWidth: 1,
    borderColor: '#E5E7EB',
  },
  optionChipSelected: {
    backgroundColor: '#3B82F6',
    borderColor: '#3B82F6',
  },
  optionText: {
    fontSize: 14,
    color: '#6B7280',
    fontWeight: '500',
  },
  optionTextSelected: {
    color: '#fff',
  },
  input: {
    backgroundColor: '#F3F4F6',
    borderRadius: 12,
    paddingHorizontal: 16,
    paddingVertical: 12,
    fontSize: 16,
  },
  radiusContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 12,
  },
  radiusLabel: {
    fontSize: 14,
    color: '#6B7280',
  },
  sliderPlaceholder: {
    flex: 1,
    height: 40,
    backgroundColor: '#F3F4F6',
    borderRadius: 8,
    justifyContent: 'center',
    alignItems: 'center',
  },
  sliderText: {
    fontSize: 12,
    color: '#9CA3AF',
  },
  priceInputsContainer: {
    flexDirection: 'row',
    gap: 12,
  },
  priceInputWrapper: {
    flex: 1,
  },
  priceLabel: {
    fontSize: 14,
    color: '#6B7280',
    marginBottom: 8,
  },
  priceInput: {
    backgroundColor: '#F3F4F6',
    borderRadius: 12,
    paddingHorizontal: 16,
    paddingVertical: 12,
    fontSize: 16,
  },
  footer: {
    flexDirection: 'row',
    gap: 12,
    padding: 20,
    borderTopWidth: 1,
    borderTopColor: '#F3F4F6',
  },
  resetButton: {
    flex: 1,
    paddingVertical: 14,
    borderRadius: 12,
    backgroundColor: '#F3F4F6',
    alignItems: 'center',
  },
  resetButtonText: {
    fontSize: 16,
    fontWeight: '600',
    color: '#6B7280',
  },
  applyButton: {
    flex: 2,
    paddingVertical: 14,
    borderRadius: 12,
    backgroundColor: '#3B82F6',
    alignItems: 'center',
  },
  applyButtonText: {
    fontSize: 16,
    fontWeight: '600',
    color: '#fff',
  },
});

import { Ionicons } from '@expo/vector-icons';
import React from 'react';
import { Image, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { categories } from '../data/mockData';
import { Person } from '../types';

interface PersonCardProps {
  readonly person: Person;
  readonly onClick: () => void;
}

export function PersonCard({ person, onClick }: PersonCardProps) {
  const category = categories.find(c => c.id === person.category);

  return (
    <TouchableOpacity 
      style={styles.card}
      onPress={onClick}
      activeOpacity={0.8}
    >
      <Image 
        source={{ uri: person.image }} 
        style={styles.image}
        resizeMode="cover"
      />
      
      <View style={styles.content}>
        <View style={styles.header}>
          <View style={styles.nameContainer}>
            <Text style={styles.name}>{person.name}</Text>
            <Text style={styles.age}>, {person.age}</Text>
          </View>
          <View style={styles.ratingContainer}>
            <Ionicons name="star" size={16} color="#FCD34D" />
            <Text style={styles.rating}>{person.rating}</Text>
            <Text style={styles.reviewCount}>({person.reviews})</Text>
          </View>
        </View>

        {category && (
          <View 
            style={[
              styles.categoryBadge,
              { backgroundColor: category.bgColor }
            ]}
          >
            <Text style={styles.categoryIcon}>{category.icon}</Text>
            <Text 
              style={[
                styles.categoryText,
                { color: category.color }
              ]}
            >
              {category.name}
            </Text>
          </View>
        )}

        <Text style={styles.bio} numberOfLines={2}>
          {person.bio}
        </Text>

        <View style={styles.footer}>
          <View style={styles.locationContainer}>
            <Ionicons name="location-outline" size={16} color="#9CA3AF" />
            <Text style={styles.location}>{person.location}</Text>
          </View>
          <View style={styles.priceContainer}>
            <Text style={styles.priceAmount}>{person.price}</Text>
            <Text style={styles.priceUnit}>💰/hr</Text>
          </View>
        </View>

        <View style={styles.details}>
          <View style={styles.detailItem}>
            <Ionicons name="time-outline" size={16} color="#9CA3AF" />
            <Text style={styles.detailText}>{person.responseTime}</Text>
          </View>
          <View style={styles.detailItem}>
            <Ionicons name="calendar-outline" size={16} color="#9CA3AF" />
            <Text style={styles.detailText}>{person.availability.join(', ')}</Text>
          </View>
        </View>
      </View>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  card: {
    backgroundColor: '#fff',
    borderRadius: 16,
    overflow: 'hidden',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 8,
    elevation: 3,
  },
  image: {
    width: '100%',
    height: 200,
  },
  content: {
    padding: 16,
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 8,
  },
  nameContainer: {
    flexDirection: 'row',
    alignItems: 'baseline',
  },
  name: {
    fontSize: 20,
    fontWeight: '600',
  },
  age: {
    fontSize: 18,
    color: '#6B7280',
  },
  ratingContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4,
  },
  rating: {
    fontSize: 16,
    fontWeight: '600',
  },
  reviewCount: {
    fontSize: 14,
    color: '#9CA3AF',
  },
  categoryBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    alignSelf: 'flex-start',
    paddingHorizontal: 12,
    paddingVertical: 6,
    borderRadius: 12,
    marginBottom: 12,
    gap: 4,
  },
  categoryIcon: {
    fontSize: 14,
  },
  categoryText: {
    fontSize: 13,
    fontWeight: '600',
  },
  bio: {
    fontSize: 15,
    color: '#4B5563',
    lineHeight: 22,
    marginBottom: 12,
  },
  footer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 12,
    paddingTop: 12,
    borderTopWidth: 1,
    borderTopColor: '#F3F4F6',
  },
  locationContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4,
  },
  location: {
    fontSize: 14,
    color: '#6B7280',
  },
  priceContainer: {
    flexDirection: 'row',
    alignItems: 'baseline',
    gap: 4,
  },
  priceAmount: {
    fontSize: 20,
    fontWeight: '700',
    color: '#059669',
  },
  priceUnit: {
    fontSize: 14,
    color: '#6B7280',
  },
  details: {
    flexDirection: 'row',
    gap: 16,
  },
  detailItem: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4,
  },
  detailText: {
    fontSize: 13,
    color: '#6B7280',
  },
});

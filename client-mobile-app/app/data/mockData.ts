import { Booking, Category, Conversation, Listing, Meeting, Notification, Person } from '../types';

export const mockCurrentUser = {
  id: 'current-user',
  name: 'Alex Morgan',
  email: 'alex.morgan@example.com',
  image: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwb3J0cmFpdCUyMHByb2Zlc3Npb25hbHxlbnwxfHx8fDE3NTkxNTkyODJ8MA&ixlib=rb-4.1.0&q=80&w=1080',
  bio: 'Love exploring new places and meeting new people. Always up for an adventure! 🌍',
  location: 'Los Angeles, CA',
  memberSince: 'January 2024',
  walletBalance: 12500, // Virtual coins
  totalBookings: 12,
  completedBookings: 8,
  upcomingBookings: 2,
  totalReviews: 5,
  averageRating: 4.8,
  isCompanion: true,
  companionProfile: {
    categories: ['friendship', 'activity'],
    price: 350, // Coins per hour
    bio: 'Professional companion available for social events, outdoor activities, and friendly hangouts. Let\'s make great memories together!',
    images: [
      'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwb3J0cmFpdCUyMHByb2Zlc3Npb25hbHxlbnwxfHx8fDE3NTkxNTkyODJ8MA&ixlib=rb-4.1.0&q=80&w=1080',
      'https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwyfHxwb3J0cmFpdCUyMHByb2Zlc3Npb25hbHxlbnwxfHx8fDE3NTkxNTkyODJ8MA&ixlib=rb-4.1.0&q=80&w=1080',
    ],
    availability: ['Weekdays', 'Evenings'],
    responseTime: '< 1 hour',
    companionRating: 4.9,
    companionReviews: 47,
  }
};

export const mockBookings: Booking[] = [
  {
    id: 'b1',
    companionId: '3',
    companionName: 'Emma Rodriguez',
    companionImage: 'https://images.unsplash.com/photo-1723189037342-c94baeda4e42?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjYXN1YWwlMjBwb3J0cmFpdCUyMGhhcHB5fGVufDF8fHx8MTc1OTIzNzQ5Nnww&ixlib=rb-4.1.0&q=80&w=1080',
    category: 'friendship',
    date: '2025-10-15',
    time: '14:00',
    duration: '3 hours',
    location: 'Downtown Coffee Shop',
    status: 'upcoming',
    price: 900, // Coins
  },
  {
    id: 'b2',
    companionId: '4',
    companionName: 'David Park',
    companionImage: 'https://images.unsplash.com/photo-1706025090794-7ade2c1b6208?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx5b3VuZyUyMHByb2Zlc3Npb25hbCUyMGhlYWRzaG90fGVufDF8fHx8MTc1OTE1OTI4Mnww&ixlib=rb-4.1.0&q=80&w=1080',
    category: 'activity',
    date: '2025-10-20',
    time: '07:00',
    duration: '2 hours',
    location: 'Central Gym',
    status: 'upcoming',
    price: 800, // Coins
  },
  {
    id: 'b3',
    companionId: '1',
    companionName: 'Sarah Johnson',
    companionImage: 'https://images.unsplash.com/photo-1488510065224-1cb40f9c9f48?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmcmllbmRseSUyMHNtaWxpbmclMjBwZXJzb258ZW58MXx8fHwxNzU5MjM3NDk2fDA&ixlib=rb-4.1.0&q=80&w=1080',
    category: 'romantic',
    date: '2025-09-28',
    time: '19:00',
    duration: '4 hours',
    location: 'Italian Restaurant',
    status: 'completed',
    price: 1800, // Coins
    rating: 5,
  },
  {
    id: 'b4',
    companionId: '7',
    companionName: 'Maya Patel',
    companionImage: 'https://images.unsplash.com/photo-1723189037342-c94baeda4e42?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjYXN1YWwlMjBwb3J0cmFpdCUyMGhhcHB5fGVufDF8fHx8MTc1OTIzNzQ5Nnww&ixlib=rb-4.1.0&q=80&w=1080',
    category: 'friendship',
    date: '2025-09-20',
    time: '11:00',
    duration: '3 hours',
    location: 'Art Museum',
    status: 'completed',
    price: 840, // Coins
    rating: 5,
  },
];

export const mockMeetings: Meeting[] = [
  {
    id: 'm1',
    clientName: 'Jessica Williams',
    clientImage: 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwb3J0cmFpdCUyMHdvbWFufGVufDF8fHx8MTc1OTIzNzQ5Nnww&ixlib=rb-4.1.0&q=80&w=1080',
    category: 'friendship',
    date: '2025-10-12',
    time: '15:00',
    duration: '2 hours',
    location: 'Starbucks Downtown',
    status: 'upcoming',
    earnings: 700, // Coins
  },
  {
    id: 'm2',
    clientName: 'Michael Chen',
    clientImage: 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwb3J0cmFpdCUyMG1hbnxlbnwxfHx8fDE3NTkyMzc0OTZ8MA&ixlib=rb-4.1.0&q=80&w=1080',
    category: 'activity',
    date: '2025-10-18',
    time: '08:00',
    duration: '3 hours',
    location: 'Hiking Trail',
    status: 'upcoming',
    earnings: 1050, // Coins
  },
  {
    id: 'm3',
    clientName: 'Amanda Rodriguez',
    clientImage: 'https://images.unsplash.com/photo-1544005313-94ddf0286df2?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwyfHxwb3J0cmFpdCUyMHdvbWFufGVufDF8fHx8MTc1OTIzNzQ5Nnww&ixlib=rb-4.1.0&q=80&w=1080',
    category: 'friendship',
    date: '2025-09-25',
    time: '13:00',
    duration: '4 hours',
    location: 'Shopping Mall',
    status: 'completed',
    earnings: 1400, // Coins
    rating: 5,
  },
];

export const categories: Category[] = [
  {
    id: 'romantic',
    name: 'Romantic',
    color: '#EF4444',
    bgColor: '#FEE2E2',
    icon: '💕',
  },
  {
    id: 'friendship',
    name: 'Friendship',
    color: '#F59E0B',
    bgColor: '#FEF3C7',
    icon: '🤝',
  },
  {
    id: 'family',
    name: 'Family',
    color: '#10B981',
    bgColor: '#D1FAE5',
    icon: '👨‍👩‍👧',
  },
  {
    id: 'professional',
    name: 'Professional',
    color: '#3B82F6',
    bgColor: '#DBEAFE',
    icon: '💼',
  },
  {
    id: 'activity',
    name: 'Activity Buddy',
    color: '#8B5CF6',
    bgColor: '#EDE9FE',
    icon: '🏃',
  },
];

export const mockPersons: Person[] = [
  {
    id: '1',
    name: 'Sarah Johnson',
    age: 28,
    category: 'romantic',
    price: 450, // Coins per hour
    rating: 4.9,
    image: 'https://images.unsplash.com/photo-1488510065224-1cb40f9c9f48?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmcmllbmRseSUyMHNtaWxpbmclMjBwZXJzb258ZW58MXx8fHwxNzU5MjM3NDk2fDA&ixlib=rb-4.1.0&q=80&w=1080',
    bio: 'Looking to accompany you to weddings, dinner dates, or social events. Great conversationalist with a positive attitude!',
    location: 'New York, NY',
    availability: ['Weekends', 'Evenings'],
    reviews: 127,
    responseTime: '< 1 hour',
  },
  {
    id: '2',
    name: 'Marcus Chen',
    age: 32,
    category: 'professional',
    price: 650, // Coins per hour
    rating: 5,
    image: 'https://images.unsplash.com/photo-1570170609489-43197f518df0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwcm9mZXNzaW9uYWwlMjBwb3J0cmFpdCUyMHBlcnNvbnxlbnwxfHx8fDE3NTkxODMxMzN8MA&ixlib=rb-4.1.0&q=80&w=1080',
    bio: 'Business networking expert. I can accompany you to conferences, meetings, or corporate events. MBA graduate with 10+ years experience.',
    location: 'San Francisco, CA',
    availability: ['Weekdays', 'Flexible'],
    reviews: 89,
    responseTime: '< 2 hours',
  },
  {
    id: '3',
    name: 'Emma Rodriguez',
    age: 25,
    category: 'friendship',
    price: 300, // Coins per hour
    rating: 4.8,
    image: 'https://images.unsplash.com/photo-1723189037342-c94baeda4e42?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjYXN1YWwlMjBwb3J0cmFpdCUyMGhhcHB5fGVufDF8fHx8MTc1OTIzNzQ5Nnww&ixlib=rb-4.1.0&q=80&w=1080',
    bio: 'Love coffee chats, museum visits, and exploring new restaurants. Perfect companion for casual hangouts and city adventures!',
    location: 'Austin, TX',
    availability: ['Afternoons', 'Weekends'],
    reviews: 156,
    responseTime: '< 30 min',
  },
  {
    id: '4',
    name: 'David Park',
    age: 35,
    category: 'activity',
    price: 400, // Coins per hour
    rating: 4.7,
    image: 'https://images.unsplash.com/photo-1706025090794-7ade2c1b6208?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx5b3VuZyUyMHByb2Zlc3Npb25hbCUyMGhlYWRzaG90fGVufDF8fHx8MTc1OTE1OTI4Mnww&ixlib=rb-4.1.0&q=80&w=1080',
    bio: 'Certified personal trainer and outdoor enthusiast. Available for gym sessions, hiking, running, or any fitness activities!',
    location: 'Denver, CO',
    availability: ['Mornings', 'Evenings'],
    reviews: 203,
    responseTime: '< 1 hour',
  },
  {
    id: '5',
    name: 'Lisa Anderson',
    age: 42,
    category: 'family',
    price: 350, // Coins per hour
    rating: 5,
    image: 'https://images.unsplash.com/photo-1488510065224-1cb40f9c9f48?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmcmllbmRseSUyMHNtaWxpbmclMjBwZXJzb258ZW58MXx8fHwxNzU5MjM3NDk2fDA&ixlib=rb-4.1.0&q=80&w=1080',
    bio: 'Warm and caring personality. Perfect for family gatherings, playing the role of aunt, or accompanying elderly family members to events.',
    location: 'Portland, OR',
    availability: ['Weekends', 'Holidays'],
    reviews: 178,
    responseTime: '< 45 min',
  },
  {
    id: '6',
    name: 'Alex Thompson',
    age: 29,
    category: 'romantic',
    price: 500, // Coins per hour
    rating: 4.9,
    image: 'https://images.unsplash.com/photo-1570170609489-43197f518df0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwcm9mZXNzaW9uYWwlMjBwb3J0cmFpdCUyMHBlcnNvbnxlbnwxfHx8fDE3NTkxODMxMzN8MA&ixlib=rb-4.1.0&q=80&w=1080',
    bio: 'Charming and respectful. Available for dates, special occasions, or as a plus-one to events. Experienced and professional.',
    location: 'Miami, FL',
    availability: ['Evenings', 'Weekends'],
    reviews: 94,
    responseTime: '< 2 hours',
  },
  {
    id: '7',
    name: 'Maya Patel',
    age: 26,
    category: 'friendship',
    price: 280, // Coins per hour
    rating: 4.8,
    image: 'https://images.unsplash.com/photo-1723189037342-c94baeda4e42?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjYXN1YWwlMjBwb3J0cmFpdCUyMGhhcHB5fGVufDF8fHx8MTc1OTIzNzQ5Nnww&ixlib=rb-4.1.0&q=80&w=1080',
    bio: 'Art lover and foodie. Great at making new friends feel comfortable. Love shopping trips, brunch, and attending concerts!',
    location: 'Seattle, WA',
    availability: ['Weekends', 'Flexible'],
    reviews: 142,
    responseTime: '< 1 hour',
  },
  {
    id: '8',
    name: 'Ryan Mitchell',
    age: 31,
    category: 'activity',
    price: 380, // Coins per hour
    rating: 4.9,
    image: 'https://images.unsplash.com/photo-1706025090794-7ade2c1b6208?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx5b3VuZyUyMHByb2Zlc3Npb25hbCUyMGhlYWRzaG90fGVufDF8fHx8MTc1OTE1OTI4Mnww&ixlib=rb-4.1.0&q=80&w=1080',
    bio: 'Rock climbing instructor and adventure seeker. Available for sports events, outdoor activities, or trying new experiences!',
    location: 'Boulder, CO',
    availability: ['Mornings', 'Weekends'],
    reviews: 167,
    responseTime: '< 30 min',
  },
];

export const mockReviews = [
  {
    id: 'r1',
    userName: 'Sarah Johnson',
    userImage: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400',
    rating: 5,
    date: 'September 28, 2025',
    comment: 'Amazing experience! Very friendly and made me feel comfortable throughout. Highly recommend for anyone looking for a genuine companion.',
    category: 'Friendship'
  },
  {
    id: 'r2',
    userName: 'Michael Chen',
    userImage: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400',
    rating: 5,
    date: 'September 22, 2025',
    comment: 'Professional and punctual. Great conversation and really knew the area well. Will definitely book again!',
    category: 'Activity'
  },
  {
    id: 'r3',
    userName: 'Emma Wilson',
    userImage: 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=400',
    rating: 4,
    date: 'September 15, 2025',
    comment: 'Had a wonderful time. Very attentive and great personality. Small timing issue but overall excellent service.',
    category: 'Romantic'
  },
  {
    id: 'r4',
    userName: 'James Rodriguez',
    userImage: 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=400',
    rating: 5,
    date: 'September 8, 2025',
    comment: 'Exceeded all expectations! Made the event so much more enjoyable. Highly professional and engaging.',
    category: 'Professional'
  },
  {
    id: 'r5',
    userName: 'Lisa Anderson',
    userImage: 'https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=400',
    rating: 5,
    date: 'September 1, 2025',
    comment: 'Fantastic companion! Very easy to talk to and made me feel at ease immediately. Would book again without hesitation.',
    category: 'Friendship'
  },
  {
    id: 'r6',
    userName: 'David Kim',
    userImage: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=400',
    rating: 4,
    date: 'August 25, 2025',
    comment: 'Great experience overall. Very knowledgeable and fun to be around. Small communication delay but otherwise perfect.',
    category: 'Activity'
  }
];

export const mockListings: Listing[] = [
  {
    id: 'l1',
    title: 'Fun outdoor adventure companion',
    description: 'Professional companion available for hiking, biking, and outdoor activities. I love exploring new trails and making fitness fun! Perfect for workout buddies or adventure seekers.',
    category: 'activity',
    price: 450,
    status: 'active',
    availability: ['Weekdays', 'Mornings', 'Evenings'],
    createdDate: '2025-09-15',
    images: [
      'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwb3J0cmFpdCUyMHByb2Zlc3Npb25hbHxlbnwxfHx8fDE3NTkxNTkyODJ8MA&ixlib=rb-4.1.0&q=80&w=1080',
      'https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwyfHxwb3J0cmFpdCUyMHByb2Zlc3Npb25hbHxlbnwxfHx8fDE3NTkxNTkyODJ8MA&ixlib=rb-4.1.0&q=80&w=1080',
    ],
    location: 'Los Angeles, CA',
    responseTime: '< 1 hour',
  },
  {
    id: 'l2',
    title: 'Professional dinner companion',
    description: 'Available for dinner dates, social events, and special occasions. Great conversationalist with excellent social skills. Let\'s make your evening memorable!',
    category: 'friendship',
    price: 350,
    status: 'active',
    availability: ['Evenings', 'Weekends'],
    createdDate: '2025-08-22',
    images: [
      'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwb3J0cmFpdCUyMHByb2Zlc3Npb25hbHxlbnwxfHx8fDE3NTkxNTkyODJ8MA&ixlib=rb-4.1.0&q=80&w=1080',
    ],
    location: 'Los Angeles, CA',
    responseTime: '< 1 hour',
  },
];

export const mockConversations: Conversation[] = [
  {
    id: 'c1',
    companionId: '1',
    companionName: 'Sarah Johnson',
    companionImage: 'https://images.unsplash.com/photo-1488510065224-1cb40f9c9f48?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmcmllbmRseSUyMHNtaWxpbmclMjBwZXJzb258ZW58MXx8fHwxNzU5MjM3NDk2fDA&ixlib=rb-4.1.0&q=80&w=1080',
    category: 'romantic',
    lastMessage: 'Sounds perfect! See you then! 😊',
    timestamp: '2025-10-01 14:30',
    unread: false,
    messages: [
      {
        id: 'm1',
        text: 'Hi Sarah! I\'d love to book you for this weekend. Are you available Saturday evening?',
        sentBy: 'user',
        timestamp: '2025-10-01 10:15',
        type: 'text',
      },
      {
        id: 'm2',
        text: 'Hi! I\'d be happy to help! Let me send you an offer for Saturday.',
        sentBy: 'companion',
        timestamp: '2025-10-01 10:20',
        type: 'text',
      },
      {
        id: 'm3',
        text: '',
        sentBy: 'companion',
        timestamp: '2025-10-01 10:21',
        type: 'offer',
        offer: {
          id: 'o1',
          date: '2025-10-05',
          time: '19:00',
          duration: '4 hours',
          location: 'Downtown Italian Restaurant',
          price: 1800,
          status: 'accepted',
          message: 'Perfect timing for a nice dinner date!',
          sentBy: 'companion',
        }
      },
      {
        id: 'm4',
        text: 'Perfect! That works great for me.',
        sentBy: 'user',
        timestamp: '2025-10-01 14:25',
        type: 'text',
      },
      {
        id: 'm5',
        text: 'Sounds perfect! See you then! 😊',
        sentBy: 'companion',
        timestamp: '2025-10-01 14:30',
        type: 'text',
      },
    ]
  },
  {
    id: 'c2',
    companionId: '3',
    companionName: 'Emma Rodriguez',
    companionImage: 'https://images.unsplash.com/photo-1723189037342-c94baeda4e42?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjYXN1YWwlMjBwb3J0cmFpdCUyMGhhcHB5fGVufDF8fHx8MTc1OTIzNzQ5Nnww&ixlib=rb-4.1.0&q=80&w=1080',
    category: 'friendship',
    lastMessage: 'I can do 3pm if that works better?',
    timestamp: '2025-09-30 18:45',
    unread: true,
    messages: [
      {
        id: 'm1',
        text: 'Hey Emma! Would love to hang out next week. Coffee?',
        sentBy: 'user',
        timestamp: '2025-09-30 15:00',
        type: 'text',
      },
      {
        id: 'm2',
        text: 'I\'d love that! ☕',
        sentBy: 'companion',
        timestamp: '2025-09-30 15:15',
        type: 'text',
      },
      {
        id: 'm3',
        text: '',
        sentBy: 'user',
        timestamp: '2025-09-30 15:20',
        type: 'offer',
        offer: {
          id: 'o2',
          date: '2025-10-15',
          time: '14:00',
          duration: '3 hours',
          location: 'Downtown Coffee Shop',
          price: 900,
          status: 'counter',
          message: 'How about Tuesday afternoon?',
          sentBy: 'user',
        }
      },
      {
        id: 'm4',
        text: 'I can do 3pm if that works better?',
        sentBy: 'companion',
        timestamp: '2025-09-30 18:45',
        type: 'text',
      },
    ]
  },
  {
    id: 'c3',
    companionId: '4',
    companionName: 'David Park',
    companionImage: 'https://images.unsplash.com/photo-1706025090794-7ade2c1b6208?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx5b3VuZyUyMHByb2Zlc3Npb25hbCUyMGhlYWRzaG90fGVufDF8fHx8MTc1OTE1OTI4Mnww&ixlib=rb-4.1.0&q=80&w=1080',
    category: 'activity',
    lastMessage: 'Great! Looking forward to our session! 💪',
    timestamp: '2025-09-29 09:30',
    unread: false,
    messages: [
      {
        id: 'm1',
        text: 'Hi David! Need a gym buddy. Are you available next Friday morning?',
        sentBy: 'user',
        timestamp: '2025-09-29 08:00',
        type: 'text',
      },
      {
        id: 'm2',
        text: '',
        sentBy: 'companion',
        timestamp: '2025-09-29 08:15',
        type: 'offer',
        offer: {
          id: 'o3',
          date: '2025-10-20',
          time: '07:00',
          duration: '2 hours',
          location: 'Central Gym',
          price: 800,
          status: 'accepted',
          message: 'Perfect! I have a great workout planned.',
          sentBy: 'companion',
        }
      },
      {
        id: 'm3',
        text: 'Great! Looking forward to our session! 💪',
        sentBy: 'companion',
        timestamp: '2025-09-29 09:30',
        type: 'text',
      },
    ]
  },
];

export const mockNotifications: Notification[] = [
  {
    id: 'n1',
    type: 'booking',
    title: 'New Booking Request',
    message: 'Jessica Williams wants to book you for October 12',
    timestamp: '2025-10-01 10:30',
    read: false,
    image: 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwb3J0cmFpdCUyMHdvbWFufGVufDF8fHx8MTc1OTIzNzQ5Nnww&ixlib=rb-4.1.0&q=80&w=1080',
  },
  {
    id: 'n2',
    type: 'message',
    title: 'New Message',
    message: 'Emma Rodriguez sent you a message',
    timestamp: '2025-09-30 18:45',
    read: false,
    image: 'https://images.unsplash.com/photo-1723189037342-c94baeda4e42?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjYXN1YWwlMjBwb3J0cmFpdCUyMGhhcHB5fGVufDF8fHx8MTc1OTIzNzQ5Nnww&ixlib=rb-4.1.0&q=80&w=1080',
  },
  {
    id: 'n3',
    type: 'review',
    title: 'New Review',
    message: 'Sarah Johnson left you a 5-star review',
    timestamp: '2025-09-28 15:20',
    read: true,
    image: 'https://images.unsplash.com/photo-1488510065224-1cb40f9c9f48?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmcmllbmRseSUyMHNtaWxpbmclMjBwZXJzb258ZW58MXx8fHwxNzU5MjM3NDk2fDA&ixlib=rb-4.1.0&q=80&w=1080',
  },
  {
    id: 'n4',
    type: 'payment',
    title: 'Payment Received',
    message: 'You received 1,400 coins from your completed booking',
    timestamp: '2025-09-25 16:00',
    read: true,
  },
  {
    id: 'n5',
    type: 'system',
    title: 'Profile Verified',
    message: 'Your companion profile has been verified!',
    timestamp: '2025-09-20 12:00',
    read: true,
  },
];
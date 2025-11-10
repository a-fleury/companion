export type CategoryType = 'romantic' | 'friendship' | 'family' | 'professional' | 'activity';

export interface Category {
  id: CategoryType;
  name: string;
  color: string;
  bgColor: string;
  icon: string;
}

export interface Person {
  id: string;
  name: string;
  age: number;
  category: CategoryType;
  price: number;
  rating: number;
  image: string;
  bio: string;
  location: string;
  availability: string[];
  reviews: number;
  responseTime: string;
}

export interface Booking {
  id: string;
  companionId: string;
  companionName: string;
  companionImage: string;
  category: CategoryType;
  date: string;
  time: string;
  duration: string;
  location: string;
  status: 'upcoming' | 'completed' | 'cancelled';
  price: number;
  rating?: number;
}

export interface Meeting {
  id: string;
  clientName: string;
  clientImage: string;
  category: CategoryType;
  date: string;
  time: string;
  duration: string;
  location: string;
  status: 'upcoming' | 'completed' | 'cancelled';
  earnings: number;
  rating?: number;
}

export interface Listing {
  id: string;
  title: string;
  description: string;
  category: CategoryType;
  price: number;
  images: string[];
  availability: string[];
  location: string;
  isActive?: boolean;
  status?: string;
  createdAt?: string;
  createdDate?: string;
  responseTime?: string;
}

export interface Message {
  id: string;
  content?: string;
  text?: string;
  senderId?: string;
  sentBy?: string;
  type?: string;
  timestamp: string;
  isRead?: boolean;
  offer?: {
    id?: string;
    price: number;
    duration: string;
    date: string;
    time: string;
    location: string;
    status?: string;
    message?: string;
    sentBy?: string;
  };
}

export interface Conversation {
  id: string;
  participantId?: string;
  participantName?: string;
  participantImage?: string;
  companionId?: string;
  companionName?: string;
  companionImage?: string;
  category?: CategoryType;
  lastMessage: string;
  lastMessageTime?: string;
  timestamp?: string;
  unreadCount?: number;
  unread?: boolean;
  messages: Message[];
}

export interface Notification {
  id: string;
  type: 'booking' | 'message' | 'review' | 'system' | 'payment';
  title: string;
  message: string;
  timestamp: string;
  isRead?: boolean;
  read?: boolean;
  image?: string;
  actionUrl?: string;
}

export interface FilterValues {
  availability: string[];
  location: string;
  radius: number;
  priceRange: [number, number];
}

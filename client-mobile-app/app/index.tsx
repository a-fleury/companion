import React, { useState } from 'react';
import { ConversationsPage } from './components/ConversationPage';
import { ExplorePage } from './components/ExplorePage';
import type { Conversation, Person } from './types';

type CurrentPage = 'explore' | 'conversations';

export default function Index() {
  const [currentPage, setCurrentPage] = useState<CurrentPage>('explore');

  const handleNavigate = (page: 'explore' | 'groups' | 'create' | 'favorites' | 'profile') => {
    console.log('Navigate to:', page);
  };

  const handleOpenShop = () => {
    console.log('Open shop');
  };

  const handlePersonClick = (person: Person) => {
    console.log('Person clicked:', person.name);
  };

  const handleOpenNotifications = () => {
    console.log('Open notifications');
  };

  const handleOpenMessages = () => {
    setCurrentPage('conversations');
  };

  const handleBackToExplore = () => {
    setCurrentPage('explore');
  };

  const handleSelectConversation = (conversation: Conversation) => {
    console.log('Conversation selected:', conversation.companionName);
    // TODO: Navigate to chat page
  };

  if (currentPage === 'conversations') {
    return (
      <ConversationsPage
        onBack={handleBackToExplore}
        onSelectConversation={handleSelectConversation}
      />
    );
  }

  return (
    <ExplorePage
      onNavigate={handleNavigate}
      onOpenShop={handleOpenShop}
      onPersonClick={handlePersonClick}
      onOpenNotifications={handleOpenNotifications}
      onOpenMessages={handleOpenMessages}
    />
  );
}

    Node* copyRandomList(Node* head) {
        
        if(head == NULL) return NULL;
        
        Node* track = head;
        while(track)
        {
            Node* temp = new Node(track->val);
            temp->next = track->next;
            track->next = temp;
            track = temp->next;
        }
        
        track = head;
        while(track)
        {
            track->next->random = track->random ? track->random->next : NULL;
            track = track->next->next;
        }
        
        track = head;
        Node* track2 = head->next;
        Node* clone = head->next;
        while(track)
        {
            track->next = track->next->next;
            track2->next = track->next ? track->next->next : NULL;
            track = track->next;
            track2 = track2->next;
        }
        
        return clone;
    }
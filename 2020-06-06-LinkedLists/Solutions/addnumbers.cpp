    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        
        ListNode preHead(-1), *head = &preHead;
        int carry = 0;
        
        while(l1 || l2 || carry)
        {
            int val = (l1 ? l1->val : 0) + (l2 ? l2->val : 0) + carry;
            carry = val / 10;
            
            head->next = new ListNode(val % 10);
            head = head->next;
            l1 = l1 ? l1->next : l1;
            l2 = l2 ? l2->next : l2;
        }

        return preHead.next;
    }
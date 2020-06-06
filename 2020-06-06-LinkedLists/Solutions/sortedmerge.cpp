class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        
        ListNode temp(0);
        ListNode* root = &temp;
        
        while(l1 && l2)
        {
            if(l1->val >= l2->val)
            {
              root->next = l2;
              l2 =  l2->next; 
            }
            else
            {
              root->next = l1;
              l1 =  l1->next;
            }
            root = root->next;
        }
        
        root->next = (l1 == NULL) ? l2 : l1;
        
        return temp.next;
        
    }
};
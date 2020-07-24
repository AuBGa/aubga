package com.aubga.alog.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKList {
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            pq.add(list);
        }

        while (!pq.isEmpty()) {
            ListNode nextNode = pq.poll();
            curr.next = nextNode;
            curr = curr.next;
            if (nextNode.next != null) {
                pq.add(nextNode.next);//为什么还要add一个东西
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {

      //  ListNode[] list = new ListNode[]{[1,4,5],[1,3,4],[2,6]};

     //   MergeKList mkl = new MergeKList();
      //  ListNode node =  mkl.mergeKLists();
       // System.out.println(node);
    }
}

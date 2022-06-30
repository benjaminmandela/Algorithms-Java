package com.chihitox.datastructures.util.PriorityQueue;

import java.util.*;

public class PriorityQueue<E extends Comparable<E>> {
  private int size = 0;

  private List<E> pq = null;

//  private Map<E, TreeSet<Integer>> map = new HashMap<>();

  public PriorityQueue() { this(1); }

  public PriorityQueue(int size) {
    pq = new ArrayList<>(size);
  }

  public PriorityQueue(Collection<E> elems) {
    this(elems.size());
   // for (E elem : elems) { add(elem); }
  }
  
  public void offer(E elem) {
    if (elem == null) throw new IllegalArgumentException();

    pq.add(elem);
  }

  private void sink(int parentIndex) {
    int childIndex;
  //  while (childIndex = parentIndex * 2 + 1 < size)
  }

  public int size() {
    return size;
  }

  private void swap() {

  }

  // stub
  private boolean less(int i, int j) {
    return true;
  }
}
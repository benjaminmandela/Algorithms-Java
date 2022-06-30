package com.chihitox.datastructures.util.PriorityQueue;

import java.util.*;
public class MinPQ<E extends Comparable<E>> {
  private E[] heap;
  private int size;

  public MinPQ() {
    this(11);
  }

  public MinPQ(int initialCapacity) {
    heap = (E[]) new Comparable[initialCapacity];
  }

  public boolean offer(E elem) {
    heap[size] = elem;
    siftUp(size++);
    return true;
  }

  public E poll() {
    E elem = heap[0];
    swap(0, --size);
    siftDown(0);
    heap[size] = null;
    return elem;
  }

  private void siftUp(int childIndex) {
    int parentIndex;
    while(childIndex > 0 && compare(childIndex, (parentIndex = (childIndex - 1) / 2)) < 0) {
      swap(childIndex, parentIndex);
      childIndex = parentIndex;
    }
  }

  private void siftDown(int parentIndex) {
    int childIndex;
    while ((childIndex = parentIndex * 2 + 1) < size) {
      if (childIndex + 1 < size && compare(childIndex, childIndex + 1) < 0) {
        childIndex++;
      }
      if (compare(parentIndex, childIndex) <= 0) {
        break;
      }
      swap(parentIndex, childIndex);
      parentIndex = childIndex;
    }
  }

  private void swap(int i, int j) {
    // Collections.swap(list, i, j);
    E temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
  }

  private int compare(int i, int j) {
    return heap[i].compareTo(heap[j]);
  }
}

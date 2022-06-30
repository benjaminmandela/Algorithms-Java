package com.chihitox.datastructures.util.PriorityQueue;

import java.util.NoSuchElementException;

public class MinIndexedPriorityQueue<E extends Comparable<E>> {
  private int maxSize;   // Maximum number of elements on this priority queue
  private int size;      // Current number of elements on this priority queue
  private E[] elements;  // elements[i] = prioriy of i
  private int[] pq;      // Binary heap. pq[i] is the index of the key in heap position i
  private int[] qp;      // qp[i] is the heap position of the key at index i. Inverse of pq - qp[pq[i]] = pq[qp[i]] = i
  
  public MinIndexedPriorityQueue(int maxSize) {
    if (maxSize < 1) {
      throw new IllegalArgumentException("Maximum size less than 1: " + maxSize);
    }
    this.maxSize = maxSize;
    size = 0;
    elements = (E[]) new Comparable[maxSize];
    pq = new int[maxSize];
    qp = new int[maxSize];
    for (int i = 0; i < maxSize; i++) {
      qp[i] = -1;
    }
  }

  public void offer(int i, E elem) {
    indexInBoundOrThrow(i);
    if (contains(i)) throw new IllegalArgumentException("Index already in the queue: " + i);
    elements[i] = elem;
    pq[size] = i;
    qp[i] = size;
    siftUp(size);
    size++;
  }

  public E peekMin() {
    isNotEmptyOrThrow();
    return elements[pq[0]];
  }

  public E pollMin() {
    E minElem = elements[delMin()];
    return minElem;
  }

  public int delMin() {
    isNotEmptyOrThrow();
    int min = pq[0];
    swap(0, --size);
    siftDown(0);
    qp[min] = -1;
    elements[min] = null;
    pq[size] = -1;
    return min;
  }

  public void delete(int i) {
    indexInBoundOrThrow(i);
    elementExistsOrThrow(i);
    int index = qp[i];
    swap(index, --size);
    siftUp(index);
    siftDown(index);
    qp[i] = -1;
    elements[i] = null;
    pq[size] = -1;
  }

  public void updateElement(int i, E elem) {
    indexInBoundOrThrow(i);
    if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue: " + 1);
    elements[i] = elem;
    siftUp(qp[i]);
    siftDown(qp[i]);
  }

  public void decreaseElem(int i, E elem) {
    indexInBoundOrThrow(i);
    elementExistsOrThrow(i);
    elements[i] = elem;
    siftUp(qp[i]);
  }

  public void increaseElem(int i, E elem) {
    indexInBoundOrThrow(i);
    elementExistsOrThrow(i);
    elements[i] = elem;
    siftDown(qp[i]);
  }

  private void swap(int i, int j) {
    int temp = pq[i];
    pq[i] = pq[j];
    pq[j] = temp;
    qp[pq[i]] = i;
    qp[pq[j]] = j;
  }

  private void siftUp(int childIndex) {
    int parentIndex;
    while (childIndex > 0 && compare(childIndex, (parentIndex = (childIndex - 1) / 2)) < 0) {
      swap(childIndex, parentIndex);
      childIndex = parentIndex;
    }
  }

  private void siftDown(int parentIndex) {
    int childIndex;
    while ((childIndex = parentIndex * 2 + 1) < size) {
      if (childIndex + 1 < size && compare(childIndex, childIndex + 1) > 0) {
        childIndex++;
      }
      if (compare(parentIndex, childIndex) <= 0) {
        break;
      }
      swap(parentIndex, childIndex);
      parentIndex = childIndex;
    }
  }

  private int compare(int i, int j) {
    return elements[pq[i]].compareTo(elements[pq[j]]);
  }

  public boolean contains(int i) {
    return qp[i] != -1;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private void indexInBoundOrThrow(int i) {
    if (i < 0) throw new IllegalArgumentException("Invalid index. Must not be less than 0: " + i);
    if (i >= maxSize) throw new IllegalArgumentException("index exceeds queue capacity:" + i);
  }

  private void elementExistsOrThrow(int i) {
    if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue: " + i);
  }

  private void isNotEmptyOrThrow() {
    if ((isEmpty())) throw new NoSuchElementException("priority queue is empty"); 
  }

}

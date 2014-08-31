#ifndef _HEADER_
#define _HEADER_
#include "header.h"
#endif

void swap(int *x, int *y)
{
    if (x != y)
    {
        *x ^= *y;
        *y ^= *x;
        *x ^= *y;
    }
}

void heapify(int* a, int i, int last)
{
    int j = 2 * i + 1;
    if(j <= last)
    {
        heapify(a, j, last);
        heapify(a, j+1, last);
        move_down(a,i,last);
    }

}

void print_array(int* a, int size)
{
    int i;
    for(i = 0; i < size; ++i)
    {
        printf("[%d] %d; ", i, *(a+i));
    }
    printf("\n");
}

void heapsort(int* a, int n)
{
    int i, j;
    for(j = (n-2)/2; j >= 0; --j)
    {
        move_down(a, j, n);
    }
    for(i = n-1; i > 0; --i)
    {
        swap((a+i),a);
        move_down(a,0,i);
    }
}

void move_down(int* a, int i, int n)
{
    if(i > n - 1)
    {
        fprintf(stderr, "errore nella move down");
        return ;
    }
    int e = *(a+i);
    int j;
    while( (j = 2*i+1) < n)
    {
        if( j+1 < n && *(a+j+1) > *(a+j))   ++j;
        if( e >= *(a+j))   break;
        *(a+i) = *(a+j);
        i = j;
    }
    *(a+i) = e;
}
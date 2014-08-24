#include<stdio.h>
#include<stdlib.h>

void heapsort(int *, int);
void heapify(int*, int, int);
void move_down(int*, int, int);
void scambia(int*, int*);

void scambia(int *x, int *y)
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

void stampa(int* a, int size)
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
        scambia((a+i),a);
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


void main() {
    int* a = (int*)malloc(sizeof(int) * 5);
    a[0] = 51;
    a[1] = 2;
    a[2] = 3;
    a[3] = 10;
    a[4] = 2;
    a[5] = 5;
    a[6] = 90;
    a[7] = 4;
    a[8] = 9;
    a[9] = -5;


    stampa(a, 10);
    heapsort(a,10);
    stampa(a,10);
  
}

#ifndef _HEADER_
#define _HEADER_
#include "header.h"
#endif

#define FILE_NAME "risultato_analisi.csv"
#define QUANTI_TEST 50
#define MOLTIPLICATORE 100

int* create_array(int size)
{
	int* a = (int*) malloc(sizeof(int) * size);
	int i = 0;
	for(; i < size; ++i)
	{
		*(a+i) = random_in_range(INT_MIN,INT_MAX);
	}
	return a;
}

copy_array(int* from, int* to, int size)
{
	int i;
	for(i = 0; i < size; i++)
	{
		to[i] = from[i];
	}
}

int write_row(int size, double isort, double ssort, double msort, double quicksort, FILE* file)
{
	fprintf(file, "%d, %.2f, %.2f, %.2f, %.2f \n", size, isort, ssort, msort, quicksort);
	return fflush(file);
	
}

void main(int argc, char* argv[])
{
	int* a;
	int* b;
	int i = 0;
	int size = 1;
	double ti, ts, tm, tq;
	struct timeval t1, t2;
	double elapsedTime;
	
	FILE* ouput_file = fopen(FILE_NAME, "a");
	if(fprintf(ouput_file,"arraySize, tInsSort, tSelSort, tMergeSort, tQuickSort \n") < 0 )
	{
		fprintf(stderr, "Errore durante la scrittura del file");
	}
	
	for(; i < QUANTI_TEST; ++i)
	{
		// prepare the arrays 
		size = size + i * MOLTIPLICATORE;
		b = create_array(size);
		a = (int*) malloc (sizeof(int) * size);

		// ISORT
		copy_array(b,a,size);
		gettimeofday(&t1, NULL);
		isort(a,size);
		gettimeofday(&t2, NULL);
		// da secondi a ms
		ti = (t2.tv_sec - t1.tv_sec) * 1000.0;
		// da usecondi a ms
		ti += (t2.tv_usec - t1.tv_usec) / 1000.0;
		printf("ISORT [%d] %f  \n", i, ti);

		// SSORT
		copy_array(b,a,size);
		gettimeofday(&t1, NULL);
		ssort(a,size);
		gettimeofday(&t2, NULL);
		// da secondi a ms
		ts = (t2.tv_sec - t1.tv_sec) * 1000.0;
		// da usecondi a ms
		ts += (t2.tv_usec - t1.tv_usec) / 1000.0;
		printf("SSORT [%d] %f  \n", i, ts);

		// MSORT
		copy_array(b,a,size);
		gettimeofday(&t1, NULL);
		msort(a,size);
		gettimeofday(&t2, NULL);
		// da secondi a ms
		tm = (t2.tv_sec - t1.tv_sec) * 1000.0;
		// da usecondi a ms
		tm += (t2.tv_usec - t1.tv_usec) / 1000.0;
		printf("MSORT [%d] %f  \n", i, tm);

		// QSORT
		copy_array(b,a,size);
		gettimeofday(&t1, NULL);
		quicksort(a,size);
		gettimeofday(&t2, NULL);
		// da secondi a ms
		tq = (t2.tv_sec - t1.tv_sec) * 1000.0;
		// da usecondi a ms
		tq += (t2.tv_usec - t1.tv_usec) / 1000.0;
		printf("QSORT [%d] %f  \n\n", i, tq);

		free(a);
		free(b);
		
		if( write_row(size, ti, ts, tm, tq, ouput_file) < 0 )
		{
			fprintf(stderr, "Errore durante la scrittura del file");
		}
	}
	
	fclose(ouput_file);
}


void quickSort( int a[], int l, int r)
{
   int j;

   if( l < r ) 
   {
   	// divide and conquer
        j = partition( a, l, r);
       quickSort( a, l, j-1);
       quickSort( a, j+1, r);
   }
	
}



int partition( int a[], int l, int r) {
   int pivot, i, j, t;
   pivot = a[l];
   i = l; j = r+1;
		
   while( 1)
   {
   	do ++i; while( a[i] <= pivot && i <= r );
   	do --j; while( a[j] > pivot );
   	if( i >= j ) break;
   	t = a[i]; a[i] = a[j]; a[j] = t;
   }
   t = a[l]; a[l] = a[j]; a[j] = t;
   return j;
}

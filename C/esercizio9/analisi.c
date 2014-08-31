#ifndef _HEADER_
#define _HEADER_
#include "header.h"
#endif

#define FILE_NAME "risultato_analisi.csv"
#define INIZIALE 1
#define QUANTI_TEST 100
#define MOLTIPLICATORE 1000

int* create_array(int size)
{
	int* a = (int*) malloc(sizeof(int) * size);
	int i = 0;
	for(; i < size; ++i)
	{
		*(a+i) = rand();
	}
	return a;
}

void stampa_array(int* a, int size)
{
	int i;
	printf("Array di dimensione %d:\n", size);
	for(i = 0; i < size; ++i)
	{
		printf("%d ", a[i]);
	}
}

copy_array(int* from, int* to, int size)
{
	int i;
	for(i = 0; i < size; i++)
	{
		to[i] = from[i];
	}
}

int write_row(int size, double isort, double ssort, double msort, double quicksort, double heapsort, FILE* file)
{
	fprintf(file, "%d, %.2f, %.2f, %.2f, %.2f, %.2f \n", size, isort, ssort, msort, quicksort, heapsort);
	return fflush(file);
	
}

void main(int argc, char* argv[])
{
	srand(time(0));
	int* a;
	int* b;
	int i = 1;
	int size = INIZIALE;
	double ti, ts, tm, tq, th;
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
		//stampa_array(b, size);
/*
		// ISORT
		copy_array(b,a,size);
		gettimeofday(&t1, NULL);
		isort(a,size);
		gettimeofday(&t2, NULL);
		// da secondi a ms
		ti = (t2.tv_sec - t1.tv_sec) * 1000.0;
		// da usecondi a ms
		ti += (t2.tv_usec - t1.tv_usec) / 1000.0;
		if(ordinato(a,size) < 0)
		{
			printf("Errore! Non Ordinato!\n");
			return;
		}
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
		if(ordinato(a,size) < 0)
		{
			printf("Errore! Non Ordinato!\n");
			return;
		}
		printf("SSORT [%d] %f  \n", i, ts);
*/
        ts = 0;
        ti = 0;

		// MSORT
		copy_array(b,a,size);
		gettimeofday(&t1, NULL);
		msort(a,size);
		gettimeofday(&t2, NULL);
		// da secondi a ms
		tm = (t2.tv_sec - t1.tv_sec) * 1000.0;
		// da usecondi a ms
		tm += (t2.tv_usec - t1.tv_usec) / 1000.0;
		if(ordinato(a,size) < 0)
		{
			printf("Errore! Non Ordinato!\n");
			return;
		}
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
		if(ordinato(a,size) < 0)
		{
			printf("Errore! Non Ordinato!\n");
			return;
		}
		printf("QSORT [%d] %f  \n", i, tq);
		
		
		// HSORT
		copy_array(b,a,size);
		gettimeofday(&t1, NULL);
		heapsort(a,size);
		gettimeofday(&t2, NULL);
		// da secondi a ms
		th = (t2.tv_sec - t1.tv_sec) * 1000.0;
		// da usecondi a ms
		th += (t2.tv_usec - t1.tv_usec) / 1000.0;
		if(ordinato(a,size) < 0)
		{
			printf("Errore! Non Ordinato!\n");
			return;
		}
		printf("HSORT [%d] %f  \n\n", i, th);

		free(a);
		free(b);
		
		if( write_row(size, ti, ts, tm, tq, th, ouput_file) < 0 )
		{
			fprintf(stderr, "Errore durante la scrittura del file");
		}
	}
	
	fclose(ouput_file);
}

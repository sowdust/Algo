#ifndef _HEADER_
#define _HEADER_
#include "header.h"
#endif

void quicksort(int *a, int size)
{
	qqsort(a, 0, size-1);
}




void qqsort(int* a, int fst, int lst)
{
	if (fst > lst) {
            return;
        }
        // INIZIALIZZAZIONE
        int i = fst;
	int j = lst;
        // scelta del pivot casuale
        int pivot = fst;
        int x = a[pivot];
        do {
            // troviamo il primo elemento che deve o può stare a dx
            while (a[i] < x) {
                ++i;
            }
            // troviamo il primo elemento che deve o può stare a sx
            while (a[j] > x) {
                --j;
            }
            // a questo punto sicuramente a[i] ≥ x e a[j] ≤ x
            // ma gli indici possono già essersi incrociati
            if (i <= j) {
                scambia(a, i, j);
                i++;
                j--;
            }
        } while (i <= j);

        qqsort(a, fst, j);
        qqsort(a, i, lst);

    }




void msort(int* a, int size)
{
	int* aux = (int*) malloc(sizeof(int) * size);
	mmsort(a, 0, size-1, aux);
	free(aux);
	
}

void mmsort(int* a, int first, int last, int* aux)
{
	if (first < last)
	{
		int m = (first + last) / 2;
		mmsort(a, first, m, aux);
		mmsort(a, m + 1, last, aux);
		merge(a, first, m, last, aux);
	}
}

// versione "in place" del merge (con sempre lo stesso array ausiliario)
void merge(int *a, int first, int m, int last, int* aux)
{
	int i = first;
	int j = m + 1;
	int k = first;
	
	if (*(a+m) <= *(a + m + 1))
	{
            return;
        }

	while (i <= m && j <= last)
	{
		if (a[i] <= a[j])	aux[k++] = a[i++];
		else			aux[k++] = a[j++];
	}

	
	int h = m;
	int l = last;
	int r = first;
	
        while (h >= i)	a[l--] = a[h--];
	
	for (; r < k; r++)	a[r] = aux[r];
}

void isort(int *a, int size)
{
	int x, i, j;
	for(i = 1; i < size; ++i)
	{
		x = *(a+i);
		j = i;
		while( j > 0 && x < *(a + j - 1))
		{
			*(a+j) = *(a+j-1);
			--j;		
		}
		*(a+j) = x;
	}
}
		
void ssort(int* a, int size)
{

	int i, iMin;	
	
	for(i = 0; i < size; ++i)
	{
		iMin = i;
		int j = iMin;
	        for (++j; j < size; ++j)
		{
			if (a[j] < a[iMin])
			{
				iMin = j;
			}
		}
		scambia(a, i, iMin);
	}	
}

void stampa(int* a, int size)
{
	int i = 0;
	for(; i < size; ++i)
	{
		printf("[%d] %d \t", i, *(a+i));
	}
	printf("\n");
}

void scambia(int* a, int i, int j)
{
	int temp = *(a+i);
	*(a+i) = *(a+j);
	*(a+j) = temp;
}

/* funzione trovata in rete per il calcolo di un numero */
/* pseudo casuale all'interno dell'intervallo semi aperto */
/* [min,max) */
/* autore presunto: Ryan Reich */
int random_in_range (unsigned int min, unsigned int max)
{
	int base_random = rand();
	if (RAND_MAX == base_random)
	{
		return random_in_range(min, max);
	}
	int range = max - min, 	remainder = RAND_MAX % range, bucket = RAND_MAX / range;
	if (base_random < RAND_MAX - remainder)
	{
		return min + base_random/bucket;
	} else	return random_in_range (min, max);
}





// mmm qualcosa non va:
void qqsortt(int* a, int inf, int sup)
{
	if( inf >= sup)	return;
	
	int randompivot = inf + random_in_range(0, sup-inf);
	scambia(a, sup, randompivot);
	
	int x = *(a + sup);
	int i = inf;
	int j = sup - 1;
	while (i <= j) {
                while (i <= j && a[i] <= x)	++i;
                while (i <= j && a[j] >= x)	--j;
                if (i < j)			scambia(a, i, j);
	}
	scambia(a, i, sup);
	qqsortt(a, inf, i - 1);
	qqsortt(a, i + 1, sup);
}

int ordinato(int* a, int size)
{
	int i = 1;
	for(; i < size; ++i)
	{
		if( a[i] < a[i-i] )
		{
			printf(" %d minore di % d", a[i], a[i-1]);
			return -1;
		}
	}
	return 0;
}







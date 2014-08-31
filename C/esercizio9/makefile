heapsort:	heapsort.o header.h
	gcc -o heapsort.out heapsort.o
sorting:	sorting.o header.h
	gcc -o sorting.out heapsort.o
analisi:	analisi.o heapsort.o sorting.o header.h
	gcc -o analisi.out analisi.o heapsort.o sorting.o
heapsort.o:	heapsort.c
	gcc -c heapsort.c
sorting.o:	sorting.c
	gcc -c sorting.c
analisi.o:	analisi.c
	gcc -c analisi.c	
clean:
	rm *.o *.out
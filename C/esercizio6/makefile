sorting:	sorting.o header.h
	gcc -o sorting.out sorting.o
analisi:	analisi.o sorting.o header.h
	gcc -o analisi.out analisi.o sorting.o
sorting.o:	sorting.c
	gcc -c sorting.c
analisi.o:	analisi.c
	gcc -c analisi.c	
clean:
	rm *.o *.out
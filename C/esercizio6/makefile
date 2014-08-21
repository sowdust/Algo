sorting:	sorting.o header.h
	gcc -o sorting sorting.o
analisi:	analisi.o sorting.o header.h
	gcc -o analisi analisi.o sorting.o
sorting.o:	sorting.c
	gcc -c sorting.c
analisi.o:	analisi.c
	gcc -c analisi.c	
clean:
	rm *.o analisi sorting
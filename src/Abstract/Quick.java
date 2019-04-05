package Abstract;

import util.Lista;

/**
 * Criado por C.A.R. Hoare, o algoritmo QuickSort("Ordenação rápida") é baseado
 * no fato de que as pormutações devem ser perfeitamente empregadas p/ pares de
 * elementos que guardem entre si distâncias grandes, com a finalidfade se
 * conseguiruma eficiência maior. É possível ordená-las com apenas n/2
 * permutações tomando-se primeiramente os elementos das extremidades á direita
 * e á esquerda das extremidades á direita e á esquerda convergindo gradualmente
 * p/ o centro, pelos dois lados.
 *
 * QUICK COM PIVÔ - O vetor é varrido da da esquerda para a direita ate que seja
 * encontrado um elemento maior que o pivo, sendo entao varrido da direita para
 * esquersa ate que seja encontradio em elemento menor que o pivo. Nesta ocasião
 * os elementos são permutados, e este processo de varredura e permutação
 * continuação continua até que os 2 deslocamentos se encontrem, quando deve ser
 * repetido o processo p/ as partes restantes;
 *
 * QUICK SEM PIVÔ: p/ i = 0 e j = TL-1, começa-se aumentando o i; quando o
 * elemento da posição i for maior que o elemento da posição j, permutam-se os
 * elementos. e passa-se a diminuir o j até que o elemento de j seja menor que o
 * de i, assim sucessivamente até que i e j se encontrem separados as partes e
 * continuamos até que todas as partes e continuamos até que todas as partes
 * tenham um só elemento
 *
 * @author Aluno
 */
public abstract class Quick extends Algoritmo
{

    public Quick()
    {
    }

    public Quick(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Quick(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

}

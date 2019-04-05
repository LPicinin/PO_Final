package Abstract;

import util.Lista;

/**
 *Método de Ordenação - Intercalação Simples
 * MergeSort ou Fusão Direta
 * Wirth págs 76-82
 * Azeredo págs 86 - 96
 * A classificação por intercalação consiste em dividir o vetor em 
 * 2 ou mais segmentos, formando 
 * novos segmentos ordenados, os quais serão intercalados entre si.
 * @author Luís - Chico
 */
public abstract class Merge extends Algoritmo
{
    public Merge()
    {
    }

    public Merge(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Merge(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }
}

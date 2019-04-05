package Abstract;

import util.Lista;

public abstract class Inserção extends Algoritmo
{
    public Inserção()
    {
    }

    public Inserção(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Inserção(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }
}

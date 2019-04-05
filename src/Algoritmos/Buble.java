package Algoritmos;

import Abstract.Algoritmo;
import Abstract.Registro;
import util.Lista;
import util.RegistroArq;

/**
 * O método de ordenação por permutação direta(Bolha) é baseado na comparação e
 * permutação de pares de elementos adjacentes até que todos eles estejam
 * ordenados.
 *
 * @author Aluno
 */
public class Buble extends Algoritmo
{

    public Buble()
    {
    }

    public Buble(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Buble(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        int n = lista.size();
        int temp;
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                if (lista.get(j).getInfo() > lista.get(j + 1).getInfo())
                {
                    temp = lista.get(j).getInfo();
                    lista.get(j).setInfo(lista.get(j + 1).getInfo());
                    lista.get(j + 1).setInfo(temp);
                }
            }
        }
    }

    @Override
    protected void sortedVetor()
    {
        int n = vet.length;
        int temp;
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                if (vet[j] > vet[j + 1])
                {
                    // troca arr[j+1] and arr[i] 
                    temp = vet[j];
                    vet[j] = vet[j + 1];
                    vet[j + 1] = temp;
                }
            }
        }
    }

    @Override
    protected void sortedArquivo()
    {
        //int tl2 = filesize();
        int tl2 = arquivo.size();

        RegistroArq regi = new RegistroArq();
        RegistroArq regii = new RegistroArq();
        while (tl2 > 1)
        {
            for (int i = 0; i < tl2 - 1; i++)
            {
                registro.sumMovProg(2);
                arquivo.seekArq(i);
                regi.leDoArq(arquivo.getFile());
                regii.leDoArq(arquivo.getFile());
                
                registro.incCompProg();
                if (regi.getCodigo() > regii.getCodigo())
                {
                    registro.sumMovProg(2);//leu 2 vezes
                    arquivo.seekArq(i);
                    regii.gravaNoArq(arquivo.getFile());
                    regi.gravaNoArq(arquivo.getFile());
                }
            }
            tl2--;
        }
    }
}

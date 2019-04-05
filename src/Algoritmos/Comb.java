package Algoritmos;

import Abstract.Algoritmo;
import Abstract.Registro;
import util.Lista;
import util.No;
import util.RegistroArq;

/**
 * O Comb Sort é principalmente uma melhoria em relação ao Bubble Sort. O tipo
 * de bolha sempre compara valores adjacentes. Então, todas as inversões são
 * removidas uma por uma. Comb Sort melhora em Bubble Sort usando gap de tamanho
 * maior que 1. O gap começa com um valor grande e encolhe por um fator de 1,3
 * em cada iteração até atingir o valor 1. Assim, Comb Sort remove mais de uma
 * inversão conta com um troque e tenha um desempenho melhor que o Bubble Sort.
 *
 * O fator de encolhimento foi empiricamente verificado como 1,3 (testando o
 * Combsort em mais de 200.000 listas aleatórias) Fonte: Wiki
 *
 * @author luis
 */
public class Comb extends Algoritmo
{

    public Comb(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Comb(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        boolean trocou = false;
        int size = lista.size();
        int gap = size;
        No aux1;
        No aux2;
        int a;
        while (gap != 1 || trocou)
        {
            gap = (gap * 10) / 13;          //calcula gap da interação
            gap = (gap < 1) ? 1 : gap;    //calcula gap da interação

            trocou = false;
            for (int i = 0; i < size - gap; i++)
            {
                aux1 = lista.get(i);
                aux2 = lista.get(i + gap);
                if (aux1.getInfo() > aux2.getInfo())
                {
                    a = aux1.getInfo();
                    aux1.setInfo(aux2.getInfo());
                    aux2.setInfo(a);
                    trocou = true;
                }
            }
        }
    }

    @Override
    protected void sortedVetor()
    {
        int n = TL;
        int gap = n;
        int temp;
        boolean trocou = true;
        while (gap != 1 || trocou == true)
        {
            // calcula o gap 
            gap = (gap * 10) / 13;
            gap = (gap < 1) ? 1 : gap;
            trocou = false;

            for (int i = 0; i < n - gap; i++)
            {
                if (vet[i] > vet[i + gap])
                {
                    temp = vet[i];
                    vet[i] = vet[i + gap];
                    vet[i + gap] = temp;
                    trocou = true;
                }
            }
        }
    }

    @Override
    protected void sortedArquivo()
    {
        int n = arquivo.size();
        int gap = n;
        int temp;
        boolean trocou = true;
        RegistroArq r = new RegistroArq();
        RegistroArq r2 = new RegistroArq();

        while (gap != 1 || trocou == true)
        {
            // calcula o gap 
            gap = (gap * 10) / 13;
            if(gap < 1)
                gap = 1;
            trocou = false;

            for (int i = 0; i < n - gap; i++)
            {
                registro.incMovProg();
                arquivo.seekArq(i);
                r.leDoArq(arquivo.getFile());
                
                registro.incMovProg();
                arquivo.seekArq(i + gap);
                r2.leDoArq(arquivo.getFile());

                registro.incCompProg();
                if (r.getCodigo() > r2.getCodigo())
                {
                    registro.incMovProg();
                    arquivo.seekArq(i);
                    r2.gravaNoArq(arquivo.getFile());
                    
                    registro.incMovProg();
                    arquivo.seekArq(i + gap);
                    r.gravaNoArq(arquivo.getFile());
                    
                    trocou = true;
                }
            }
        }
    }

}

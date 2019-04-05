package Algoritmos;

import Abstract.Algoritmo;
import Abstract.Registro;
import util.Lista;
import util.No;
import util.RegistroArq;

/**
 * O método Shell foi proposto por Shell, D.L. como sendo um refinamento do
 * método de ordenação por inserção direta. Primeiramente, todos os elementos
 * que estiverem a intervalo de quatro posições entre si na sequência corrente
 * são agrupados e ordenados separadamente. Este processo é denominado de
 * ordenação de distância 4. Após este primeiro passo, os elementos são
 * reagrupados em grupos com elementos cujo intervalo é de duas posições, sendo
 * então ordenados novamente. Este processo é denominado de ordenação de
 * distância 2. Finalmente, em um terceiro passo, todos os elementos são
 * ordenados através de uma ordenação simples, ou ordenação de distância 1. O
 * método possui a vantagem que a cada passo faz classificações parciais da
 * estrutura, o que favorece o desempenho dos passos seguintes.
 *
 * @author Luish
 */
public class Shell extends Algoritmo
{

    public Shell() {
    }

    public Shell(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Shell(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        int dist = 4;
        int temp;
        int i, j, k;
        No naux1, naux2, naux3, naux4;
        while (dist > 0)
        {
            for (i = 0; i < dist; i++)
            {
                for(j = i; j + dist < lista.size(); j++)
                {
                    naux1 = lista.get(j);
                    naux2 = lista.avanca(naux1, dist);

                    while (naux2 != null)
                    {
                        if (naux1.getInfo() > naux2.getInfo())
                        {
                            temp = naux1.getInfo();
                            naux1.setInfo(naux2.getInfo());
                            naux2.setInfo(temp);

                            naux3 = naux2;
                            naux4 = lista.recua(naux3, dist);
                            while (naux4 != null && naux3 != null)//faz a volta até não achar elementos fora do lugar
                            {
                                if(naux3.getInfo() < naux4.getInfo())
                                {
                                    temp = naux4.getInfo();
                                    naux4.setInfo(naux3.getInfo());
                                    naux3.setInfo(temp);
                                }
                                naux3 = lista.recua(naux3, dist);
                                naux4 = lista.recua(naux3, dist);
                            }
                        }

                        naux1 = lista.avanca(naux1, dist);
                        naux2 = lista.avanca(naux1, dist);
                    }
                }
            }
            dist /= 2;
        }
    }

    @Override
    protected void sortedVetor()
    {
        int dist = 4;
        int temp;
        int i, j, k;//melhor que declarar dentro do for
        while (dist > 0)
        {
            for (i = 0; i < dist; i++)
            {
                for (j = i; j + dist < TL; j += dist)
                {
                    if (vet[j] > vet[j + dist])
                    {
                        temp = vet[j];
                        vet[j] = vet[j + dist];
                        vet[j + dist] = temp;
                        for (k = j; k - dist >= 0/*ou i*/ && vet[k] < vet[k - dist]; k -= dist)//checar se existe k-dist no arquivo, if antes e dentro do laço(no fim)
                        {
                            temp = vet[k];
                            vet[k] = vet[k - dist];
                            vet[k - dist] = temp;
                        }
                    }
                }
            }
            dist /= 2;
        }
    }

    @Override
    protected void sortedArquivo()
    {
        RegistroArq vet1 = new RegistroArq();
        RegistroArq vet2 = new RegistroArq();
        int tl = arquivo.size();
        int i, j, k, dist;
        for (dist = 4; dist > 0; dist /= 2)
        {
            for (i = 0; i < dist; i++)
            {
                for (j = i; j + dist < tl; j += dist)
                {
                    registro.incMovProg();
                    arquivo.seekArq(j);
                    vet1.leDoArq(arquivo.getFile());
                    
                    registro.incMovProg();
                    arquivo.seekArq(j + dist);
                    vet2.leDoArq(arquivo.getFile());
                    
                    registro.incCompProg();
                    if (vet1.getCodigo() > vet2.getCodigo())
                    {
                        swap(j, j + dist);//já incrementa 2 no contador de permutação
                        k = j;
                        registro.incMovProg();
                        arquivo.seekArq(k);
                        vet1.leDoArq(arquivo.getFile());
                        
                        registro.incMovProg();
                        arquivo.seekArq(k - dist);
                        vet2.leDoArq(arquivo.getFile());
                        
                        registro.incCompProg();
                        while (k - dist >= i && vet1.getCodigo() < vet2.getCodigo())
                        {
                            swap(k, k - dist);
                            k -= dist;
                            
                            registro.incMovProg();
                            arquivo.seekArq(k);
                            vet1.leDoArq(arquivo.getFile());
                            
                            registro.incMovProg();
                            arquivo.seekArq(k - dist);
                            vet2.leDoArq(arquivo.getFile());
                        }
                    }
                }
            }
        }
    }

    public void swap(int i, int j)
    {
        RegistroArq temp = new RegistroArq();
        RegistroArq rj = new RegistroArq();
        arquivo.seekArq(i);
        temp.leDoArq(arquivo.getFile());
        arquivo.seekArq(j);
        rj.leDoArq(arquivo.getFile());
        arquivo.seekArq(i);
        rj.gravaNoArq(arquivo.getFile());
        arquivo.seekArq(j);
        temp.gravaNoArq(arquivo.getFile());
        
        registro.sumMovProg(2);
    }

}

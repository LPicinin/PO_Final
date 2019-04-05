package Algoritmos;

import Abstract.Algoritmo;
import Abstract.Registro;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Lista;
import util.No;
import util.RegistroArq;

/**
 * Separa os elementos em Baldes ordena cada balde com outro algoritmo e após a
 * ordenação os baldes são concatenados para formar o vetor ordenado
 *
 * @author Luish
 */
public class Bucket extends Algoritmo
{

    public Bucket() 
    {
    }
    
    public Bucket(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Bucket(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        int Nmax = lista.max() / 10;//qtd baldes
        int aux;
        No naux;
        Lista[] balde = new Lista[Nmax];//tamanho dos balde variam de acordo com os elementos da lista
        for (int i = 0; i < balde.length; i++)
        {
            balde[i] = new Lista();
        }
        naux = lista.getInicio();
        while (naux != null)//distribui os elementos nos baldes
        {
            aux = naux.getInfo();
            balde[aux / Nmax].insereInicio(aux);
            naux = lista.avanca(naux, 1);
        }
        for (int i = 0; i < balde.length; i++)//ordena baldes
        {
            Heap.sortedLista(balde[i]);
        }
        lista.inicializa();
        No auxn;
        for (int j = 0; j < balde.length; j++)//concatena baldes
        {
            auxn = balde[j].getInicio();
            while (auxn != null)
            {
                lista.insereFinal(auxn.getInfo());
                auxn = auxn.getProx();
            }
        }
    }

    @Override
    protected void sortedVetor()
    {
        int n = Arrays.stream(vet).max().getAsInt() / 10;
        Lista[] baldes = new Lista[n];//tamanho dos balde variam de acordo com os elementos da lista
        for (int i = 0; i < n; i++)//instancia blades
        {
            baldes[i] = new Lista();
        }
        for (int i = 0; i < TL; i++)//distribui elementos nos baldes
        {
            baldes[vet[i] / n].insereFinal(vet[i]);
        }
        for (int i = 0; i < baldes.length; i++)//ordena baldes
        {
            Heap.sortedLista(baldes[i]);
        }
        int[] v = new int[TL];
        int pos = 0;
        for (int i = 0; i < baldes.length; i++)
        {
            int size = baldes[i].size();
            for (int j = 0; j < size; j++)
            {
                v[pos++] = baldes[i].get(j).getInfo();
            }

        }
    }

    private void exibeBaldes(Lista[] b)
    {
        StringBuilder s = new StringBuilder("\n\n\n");
        int size;
        for (int i = 0; i < b.length; i++)
        {
            size = b[i].size();
            if (size > 0)
            {
                s.append(b[i].toString() + "\n");
            }
        }
        System.out.println(s.toString());
    }

    @Override
    protected void sortedArquivo()
    {
        int n = arquivo.max() / 10;
        int size = arquivo.size();
        RegistroArq r = new RegistroArq();
        
        Lista[] baldes = new Lista[n];//tamanho dos balde variam de acordo com os elementos da lista
        for (int i = 0; i < n; i++)//instancia baldes
        {
            baldes[i] = new Lista();
        }
        for (int i = 0; i < size; i++)//distribui elementos nos baldes
        {
            registro.incMovProg();
            arquivo.seekArq(i);
            r.leDoArq(arquivo.getFile());
            baldes[r.getCodigo() / n].insereFinal(r.getCodigo());
        }
        for (int i = 0; i < baldes.length; i++)//ordena baldes
        {
            Heap(baldes[i]);
        }
        No auxn;
        for (int i = 0, k = 0; i < baldes.length; i++)
        {
            auxn = baldes[i].getInicio();
            while(auxn != null)
            {
                registro.incMovProg();
                r.setCodigo(auxn.getInfo());
                arquivo.seekArq(k++);
                r.gravaNoArq(arquivo.getFile());
                auxn = auxn.getProx();
            }
        }
    }
    
    public void Heap(Lista l)
    {
        int tl2 = l.size();
        int pai, fe, fd;
        No nfe, nfd, npai, nMaiorF;
        while (tl2 > 1)
        {
            pai = tl2 / 2 - 1;
            while (pai >= 0)
            {
                registro.incMovProg();
                npai = l.get(pai);
                fe = 2 * pai + 1;
                fd = fe + 1;
                if (fd < tl2)
                {
                    registro.incMovProg();
                    nfd = l.get(fd);
                    
                    registro.incMovProg();
                    nfe = l.get(fe);
                    
                    registro.incCompProg();
                    nMaiorF = (nfe.getInfo() > nfd.getInfo()) ? nfe : nfd;
                } else
                {
                    nMaiorF = l.get(fe);
                }
                registro.incCompProg();
                if (nMaiorF.getInfo() > npai.getInfo())
                {
                    int aux = nMaiorF.getInfo();
                    nMaiorF.setInfo(npai.getInfo());
                    registro.incMovProg();
                    npai.setInfo(aux);
                }
                pai--;
            }
            registro.incMovProg();
            int aux = l.get(0).getInfo();
            l.get(0).setInfo(l.get(tl2 - 1).getInfo());
            
            registro.incMovProg();
            l.get(tl2 - 1).setInfo(aux);
            tl2--;
        }
    }
}

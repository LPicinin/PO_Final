package Algoritmos;

import Abstract.Merge;
import Abstract.Registro;
import java.io.IOException;
import util.Arquivo;
import util.Lista;
import util.No;
import util.RegistroArq;

/**
 * Método de Ordenação - Intercalação Simples MergeSort ou Fusão Direta Wirth
 * págs 76-82 Azeredo págs 86 - 96 A classificação por intercalação consiste em
 * dividir o vetor em 2 ou mais segmentos, formando novos segmentos ordenados,
 * os quais serão intercalados entre si.
 *
 * Primeira Implementação Vet[23, 17, 8, 15, 9, 12, 19, 7]
 *
 *          / vet1[] particao- Fusão[] \ vet2[]
 *
 *          / vet1[] particao- Fusão[] \ vet2[]
 *
 *          / vet1[] particao- Fusão[] \ vet2[]
 *
 * @author Luís - Chico
 */
public class MergeP extends Merge
{
    public MergeP()
    {
    }

    public MergeP(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public MergeP(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }
    
    private void copiaLista(int comeca, int termina, Lista l)
    {
        for(int i = comeca; i < termina;i++)
            l.insereInicio(lista.get(i).getInfo());
    }
    
    @Override
    protected void sortedLista()
    {
        int seq = 1;
        Lista particao1 = new Lista(),particao2 = new Lista();
        while(seq < lista.size())
        {
            particionaLista(particao1, particao2, lista.size());
            fusao(particao1, particao2, seq);
            seq *=2;
        }
    }
    
    private void particionaLista(Lista particao1,Lista particao2, int s)
    {
        particao1.inicializa();
        particao2.inicializa();
        No aux1 = lista.getInicio();
        No aux2 = lista.get(s/2);
        while(aux1 != null && aux2 != null)
        {
            particao1.insereFinal(aux1.getInfo());
            aux1 = aux1.getProx();
            
            particao2.insereFinal(aux2.getInfo());
            aux2 = aux2.getProx();
        }
        //caso falte algum elemento de uma das duas partições
        while(aux1 != null)
        {
            particao1.insereFinal(aux1.getInfo());
            aux1 = aux1.getProx();
        }
        while(aux2 != null)
        {
            particao2.insereFinal(aux2.getInfo());
            aux2 = aux2.getProx();
        }
    }
    
    private void fusao(Lista particao1,Lista particao2, int seq)
    {
        int aux_seq = seq;
        int i = 0, j = 0, c = 0;
        while (c < lista.size())
        {
            while (i < seq && j < seq)
                if (particao1.get(i).getInfo() < particao2.get(j).getInfo())
                    lista.get(c++).setInfo(particao1.get(i++).getInfo());
                else
                    lista.get(c++).setInfo(particao2.get(j++).getInfo());
            while (i < seq)
                lista.get(c++).setInfo(particao1.get(i++).getInfo());
            while (j < seq)
                lista.get(c++).setInfo(particao2.get(j++).getInfo());
            seq += aux_seq;
        }
    }

    @Override
    protected void sortedVetor()
    {
        int seq = 1;
        int vet1[] = new int[TL / 2];
        int vet2[] = new int[TL / 2];
        while (seq < TL)
        {
            particao(vet1, vet2);
            fusao(vet1, vet2, seq);
            seq *= 2;
        }
    }

    private void fusao(int[] vet1, int[] vet2, int seq)
    {
        int meio = TL / 2;
        int aux_seq = seq;
        int i = 0, j = 0, c = 0;
        while (c < TL)
        {
            while (i < seq && j < seq)
                if (vet1[i] < vet2[j])
                    vet[c++] = vet1[i++];
                else
                    vet[c++] = vet2[j++];
            while (i < seq)
                vet[c++] = vet1[i++];
            while (j < seq)
                vet[c++] = vet2[j++];
            seq += aux_seq;
        }
    }

    private void particao(int[] vet1, int[] vet2)
    {
        int c = TL / 2;
        for (int i = 0; i < c; i++)
        {
            vet1[i] = vet[i];
            vet2[i] = vet[i + c];
        }
    }
    
      @Override
    protected void sortedArquivo()
    {
        mergeSortArquivo();
    }
    ////////////////////////////Ma
    public void mergeSortArquivo()
    {
        Arquivo arq1 = new Arquivo("src\\Arquivos\\arq1.dat");
        Arquivo arq2 = new Arquivo("src\\Arquivos\\arq2.dat");
        int seq = 1, s = arquivo.size();
        while(seq < s)
        {
            particao(arq1, arq2, s);
            fusao(arq1, arq2, seq, s);
            seq = seq*2;
        }
    }
    
    public void particao(Arquivo arq1, Arquivo arq2, int TL)
    {
        int m = TL/2, i = 0, k = 0;
        RegistroArq a = new RegistroArq();
        
        for (int j = 0; j < m; j++)
        {
            registro.incMovProg();
            arquivo.seekArq(j);
            a.leDoArq(arquivo.getFile());
            a.gravaNoArq(arq1.getFile());
            
            registro.incMovProg();
            arquivo.seekArq(m+j);
            a.leDoArq(arquivo.getFile());
            a.gravaNoArq(arq2.getFile());
        }
    }
    
    public void fusao(Arquivo arq1, Arquivo arq2, int seq, int tl)
    {
        int i = 0, j = 0, k = 0, tseq = seq;
        RegistroArq a1, a2;
        a1 = new RegistroArq();
        a2 = new RegistroArq();
        
        while(k < tl)
        {
            while(i < tseq && k < tseq)
            {
                registro.incMovProg();
                arq1.seekArq(i);
                a1.leDoArq(arq1.getFile());
                
                registro.incMovProg();
                arq2.seekArq(j);
                a2.leDoArq(arq2.getFile());
                
                registro.incCompProg();
                if(a1.getCodigo() < a2.getCodigo())
                {
                    registro.incMovProg();
                    arquivo.seekArq(k);
                    a1.gravaNoArq(arquivo.getFile());
                    i++;
                }
                else
                {
                    registro.incMovProg();
                    arquivo.seekArq(k);
                    a2.gravaNoArq(arquivo.getFile());
                    j++;
                }
                k++;
                
                
                while(i < tseq)
                {
                    registro.incMovProg();
                    arquivo.seekArq(k);
                    a1.gravaNoArq(arquivo.getFile());
                    i++; k++;
                }
                
                while(j < tseq)
                {
                    registro.incMovProg();
                    arquivo.seekArq(k);
                    a2.gravaNoArq(arquivo.getFile());
                    j++; k++;
                }
            }
            tseq = tseq + seq;
        }
    }
}

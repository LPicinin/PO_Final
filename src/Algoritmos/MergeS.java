package Algoritmos;

import Abstract.Merge;
import Abstract.Registro;
import java.io.IOException;
import java.io.RandomAccessFile;
import util.Arquivo;
import util.Lista;
import util.No;
import util.RegistroArq;

/**
 *Segunda Implementação
 * 
 * aux[]
 * 
 * vet[23, 17, 8, 15, 9, 12]
 *      \esq     |          \dir
 *               meio
 * 
 * @author Aluno
 */
public class MergeS extends Merge
{
    public MergeS()
    {
    }

    public MergeS(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public MergeS(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }
    private void copiaLista(int comeca, int termina, Lista l)
    {
        int pos = comeca;
        int fim = termina;
        No aux = lista.get(pos);
        while (aux != null && pos < fim)
        {
            l.insereFinal(aux.getInfo());
            aux = aux.getProx();
            pos++;
        }
    }
    
    @Override
    protected void sortedLista()
    {
        Lista aux = new Lista(); 
        copiaLista(0, lista.size(), aux);
        merge(aux, 0, lista.size()-1);
    }

    private void merge(Lista aux, int esq, int dir)
    {
        if(esq < dir)
        {
            int meio =  (esq + dir)/2;
            merge(aux, esq, meio);
            merge(aux, meio+1, dir);
            fusao(aux, esq, meio, meio+1, dir);
        }
    }
    
    private void fusao(Lista aux, int ini1, int fim1, int ini2, int fim2)
    {
        int c = 0,i = ini1,j = ini2;
        while (i <= fim1 && j <= fim2)
        {
            if(lista.get(i).getInfo() < lista.get(j).getInfo())
                aux.get(c++).setInfo(lista.get(i++).getInfo());
            else
                aux.get(c++).setInfo(lista.get(j++).getInfo());
        }
        while(i <= fim1)
            aux.get(c++).setInfo(lista.get(i++).getInfo());
        while(j <= fim2)
            aux.get(c++).setInfo(lista.get(j++).getInfo());
        for(i = 0; i < c; i++)
            lista.get(ini1 + i).setInfo(aux.get(i).getInfo());
    } 
    
    @Override
    protected void sortedVetor()
    {
        int aux[] = new int[TL];
        merge(aux, 0, TL-1);
    }

    private void merge(int[] aux, int esq, int dir)
    {
        if(esq < dir)
        {
            int meio =  (esq + dir)/2;
            merge(aux, esq, meio);
            merge(aux, meio+1, dir);
            fusao(aux, esq, meio, meio+1, dir);
        }
    }

    private void fusao(int[] aux, int ini1, int fim1, int ini2, int fim2)
    {
        int c = 0,i = ini1,j = ini2;
        while (i <= fim1 && j <= fim2)
        {
            if(vet[i] < vet[j])
                aux[c++] = vet[i++];//vet[c++] = vet[ini1++];
            else
                aux[c++] = vet[j++];//vet[c++] = vet[ini2++];
        }
        while(i <= fim1)
            aux[c++] = vet[i++];
        while(j <= fim2)
            aux[c++] = vet[j++];
        for(i = 0; i < c; i++)
            vet[ini1 + i] = aux[i];
    }
////////////////////////arquivo
    @Override
    protected void sortedArquivo()
    {
        Arquivo aux = new Arquivo("src\\Arquivos\\AuxMerge.dat");
        merge_rec(aux, 0, arquivo.size() - 1);
    }
    private void merge_rec(Arquivo aux, int esq, int dir)
    {
        int meio;
        if(esq < dir)
        {
            meio = (esq + dir)/2;
            merge_rec(aux, esq, meio);
            merge_rec(aux, meio + 1, dir);
            fusao(aux, esq, meio, meio + 1, dir);
        }
    }
    private void fusao(Arquivo aux, int ini1, int fim1, int ini2, int fim2)
    {
        RegistroArq veti = new RegistroArq();
        RegistroArq vetj = new RegistroArq();
        
        int k = 0, i = ini1, j = ini2;
        while(i <= fim1 && j <= fim2)
        {
            registro.incMovProg();
            arquivo.seekArq(i);
            veti.leDoArq(arquivo.getFile());
            
            registro.incMovProg();
            arquivo.seekArq(j);
            vetj.leDoArq(arquivo.getFile());
            
            registro.incCompProg();
            if(veti.getCodigo() < vetj.getCodigo())
            {
                registro.incMovProg();
                aux.seekArq(k);
                veti.gravaNoArq(aux.getFile());
                i++;
            }
            else
            {
                registro.incMovProg();
                aux.seekArq(k);
                vetj.gravaNoArq(aux.getFile());
                j++;
            }
            k++;  
        } 

        while(i <= fim1)
        {
            registro.incMovProg();
            arquivo.seekArq(i);
            veti.leDoArq(arquivo.getFile());
            
            registro.incMovProg();
            aux.seekArq(k);
            veti.gravaNoArq(aux.getFile());
            i++;
            k++;
        }
            

        while(j <= fim2)
        {
            arquivo.seekArq(j);
            vetj.leDoArq(arquivo.getFile());
            aux.seekArq(k);
            vetj.gravaNoArq(aux.getFile());
            j++;
            k++;
            //aux[k++] = vet[j++];
        }
       
        for (int l = 0; l < k; l++)
        {
            aux.seekArq(l);
            veti.leDoArq(aux.getFile());
            arquivo.seekArq(l + ini1);
            veti.gravaNoArq(arquivo.getFile());
            //vet[l + ini1] = aux[l];
        }
            
    }
    
}

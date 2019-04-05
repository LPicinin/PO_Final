package Algoritmos;

import Abstract.Algoritmo;
import Abstract.Registro;
import util.Lista;
import util.No;
import util.RegistroArq;
import util.Util;

/**
 * Construir arvore heap(filhos são menores que o pai). Trocar o (1ª, 2ª, ...,
 * TL-1) maior elemnto que entrará na posição 0 com o (TL-1, TL-2, ..., 1)
 * elemnto, respectivamente. pai->posição n->filhos(2n+1 e 2n+2). O método heap
 * é dividido em suas partes: primeiro monta-se uma arvore binária chamada heap,
 * p/ em seguida, classificar através de seleção(na árvore).
 *
 * É considerado um vetor(C0, C1, .., Cn-1), como sendo a representação de uma
 * arvore binária, usando a seguinte interpretação dos indices das chaves:
 *
 * Co é a raiz da árvore; C2i+1 = Subárvore da esquerda de Ci \ para i = 0, n
 * div 2 C2i+1 = subárvore da direita de Ci /
 *
 * Representação da árvore no vetor C0, ...6
 *
 * O algoritmo consiste em trocar as chaves dentro do vetor, de tal forma que
 * estas passam a formar uma hierarquia, na qual todas as raizes das subárvores
 * sejam maiores ou iguais a qualquer uma das suas sucessoras (Ci >= C2i+1 e Ci
 * >= C2i+2). Quando todas as raízes das subárvores satisfazerem essas
 * condições, a árvore forma um heap. O teste se inicia pela ultima subárvore,
 * cuja raiz está na posição n div 2-1 do vetor de chaves, prosseguindo-se, a
 * partir daí, p/ as subárvores que antecedem esta, até testar a raiz da árvore.
 *
 * @author picinin
 */
public class Heap extends Algoritmo
{

    public Heap()
    {
    }

    public Heap(Lista lista, int[] vet, int TL, Registro registro)
    {
        super(lista, vet, TL, registro);
    }

    public Heap(Lista lista, int[] vet, int TL)
    {
        super(lista, vet, TL);
    }

    @Override
    protected void sortedLista()
    {
        int tl2 = lista.size();
        int pai, fe, fd;
        No nfe, nfd, npai, nMaiorF;
        while (tl2 > 1)
        {
            pai = tl2 / 2 - 1;
            while (pai >= 0)
            {
                npai = lista.get(pai);
                fe = 2 * pai + 1;
                fd = fe + 1;
                if (fd < tl2)
                {
                    nfd = lista.get(fd);
                    nfe = lista.get(fe);
                    nMaiorF = (nfe.getInfo() > nfd.getInfo()) ? nfe : nfd;
                } else
                {
                    nMaiorF = lista.get(fe);
                }
                if (nMaiorF.getInfo() > npai.getInfo())
                {
                    int aux = nMaiorF.getInfo();
                    nMaiorF.setInfo(npai.getInfo());
                    npai.setInfo(aux);
                }
                pai--;
            }
            int aux = lista.get(0).getInfo();
            lista.get(0).setInfo(lista.get(tl2 - 1).getInfo());
            lista.get(tl2 - 1).setInfo(aux);
            tl2--;
        }
    }

    public static void sortedLista(Lista l)
    {
        int tl2 = l.size();
        int pai, fe, fd;
        No nfe, nfd, npai, nMaiorF;
        while (tl2 > 1)
        {
            pai = tl2 / 2 - 1;
            while (pai >= 0)
            {
                npai = l.get(pai);
                fe = 2 * pai + 1;
                fd = fe + 1;
                if (fd < tl2)
                {
                    nfd = l.get(fd);
                    nfe = l.get(fe);
                    nMaiorF = (nfe.getInfo() > nfd.getInfo()) ? nfe : nfd;
                } else
                {
                    nMaiorF = l.get(fe);
                }
                if (nMaiorF.getInfo() > npai.getInfo())
                {
                    int aux = nMaiorF.getInfo();
                    nMaiorF.setInfo(npai.getInfo());
                    npai.setInfo(aux);
                }
                pai--;
            }
            int aux = l.get(0).getInfo();
            l.get(0).setInfo(l.get(tl2 - 1).getInfo());
            l.get(tl2 - 1).setInfo(aux);
            tl2--;
        }
    }

    @Override
    protected void sortedVetor()
    {
        int tl2 = TL;
        int pai, fe, fd, PmaioF;
        while (tl2 > 1)
        {
            pai = tl2 / 2 - 1;
            while (pai >= 0)
            {
                fe = 2 * pai + 1;
                fd = fe + 1;
                PmaioF = (fd < tl2) ? ((vet[fe] > vet[fd]) ? fe : fd) : fe;
                if (vet[PmaioF] > vet[pai])
                {
                    int aux = vet[PmaioF];
                    vet[PmaioF] = vet[pai];
                    vet[pai] = aux;
                }
                pai--;
            }
            int aux = vet[0];
            vet[0] = vet[tl2 - 1];
            vet[tl2 - 1] = aux;
            tl2--;
        }
    }

    @Override
    protected void sortedArquivo()
    {
        int tl2 = arquivo.size();
        int pai, fe, fd, PmaioF;
        RegistroArq rpai = new RegistroArq(), 
                rfe = new RegistroArq(), 
                rfd = new RegistroArq(), rMfilho, 
                rini = new RegistroArq(), 
                rfimtl2 = new RegistroArq();
        while (tl2 > 1)
        {
            pai = tl2 / 2 - 1;
            while (pai >= 0)
            {
                fe = 2 * pai + 1;
                fd = fe + 1;
                registro.incMovProg();
                arquivo.seekArq(pai);
                rpai.leDoArq(arquivo.getFile());
                
                registro.incMovProg();
                arquivo.seekArq(fe);
                rfe.leDoArq(arquivo.getFile());
                
                registro.incCompProg();
                if(fd < tl2)//tem mais de um filho
                {
                    registro.incMovProg();
                    arquivo.seekArq(fd);
                    rfd.leDoArq(arquivo.getFile());
                    
                    registro.incCompProg();
                    if(rfe.getCodigo() > rfd.getCodigo())
                    {
                        rMfilho = rfe;
                        PmaioF = fe;
                    }
                    else
                    {
                        rMfilho = rfd;
                        PmaioF = fd;
                    }
                }
                else
                {
                    rMfilho = rfe;
                    PmaioF = fe;
                }
                
                if (rMfilho.getCodigo() > rpai.getCodigo())
                {
                    registro.sumMovProg(2);
                    Util.swapArquivo(rMfilho, rpai, arquivo, PmaioF, pai);
                    /*int aux = vet[PmaioF];
                    vet[PmaioF] = vet[pai];
                    vet[pai] = aux;*/
                }
                pai--;
            }
            registro.incMovProg();
            arquivo.seekArq(0);
            rini.leDoArq(arquivo.getFile());
            
            registro.incMovProg();
            arquivo.seekArq(tl2-1);
            rfimtl2.leDoArq(arquivo.getFile());
            
            registro.sumMovProg(2);
            Util.swapArquivo(rini, rfimtl2, arquivo, 0, tl2-1);
            /*int aux = vet[0];
            vet[0] = vet[tl2 - 1];
            vet[tl2 - 1] = aux;*/
            tl2--;
        }
    }

}

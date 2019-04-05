/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po;

import Abstract.Algoritmo;
import Algoritmos.*;
import com.jfoenix.controls.JFXCheckBox;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import util.Arquivo;
import util.Lista;
import util.RegistroArq;
import util.RegistroOrdenado;
import util.Saida;
import util.Util;

/**
 *
 * @author luis
 */
public class TelaPrincipalController implements Initializable
{

    @FXML
    private TableView<Saida> tabelaOrdenada;
    @FXML
    private TableView<Saida> tabelaReversa;
    @FXML
    private TableView<Saida> tabelaRandom;
    @FXML
    private TableColumn<Saida, Integer> nomeOrd;
    @FXML
    private TableColumn<Saida, Integer> compEqualOrd;
    @FXML
    private TableColumn<Saida, Integer> movProgOrd;
    @FXML
    private TableColumn<Saida, Integer> movEquaOrd;
    @FXML
    private TableColumn<Saida, Long> tempoOrd;
    @FXML
    private TableColumn<Saida, Integer> nomeReverso;
    @FXML
    private TableColumn<Saida, Integer> compProgRever;
    @FXML
    private TableColumn<Saida, Integer> compEquaRever;
    @FXML
    private TableColumn<Saida, Integer> movProgRever;
    @FXML
    private TableColumn<Saida, Integer> movEquaRever;
    @FXML
    private TableColumn<Saida, Long> tempoRever;
    @FXML
    private TableColumn<Saida, Integer> nomeRand;
    @FXML
    private TableColumn<Saida, Integer> compProgRand;
    @FXML
    private TableColumn<Saida, Integer> compEquaRand;
    @FXML
    private TableColumn<Saida, Integer> movProgRand;
    @FXML
    private TableColumn<Saida, Integer> movEquaRand;
    @FXML
    private TableColumn<Saida, Long> tempoRand;
    @FXML
    private TableColumn<Saida, Integer> compProgOrd;
    @FXML
    private JFXCheckBox cbPregerados;
    @FXML
    private BorderPane tela;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        iniciaComponentes();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                tela.setDisable(true);
                scriptOredacoes();
                tela.setDisable(false);
            }
        }).start();
        RegistroArq ra = new RegistroArq();
        Arquivo ar = new Arquivo("src\\Arquivos\\fileReverso.dat");

        ar.seekArq(1);
        ra.leDoArq(ar.getFile());
        System.out.println(ar.size() + " " + ra.getCodigo());

        Lista l = Util.geraListaRandomico(16);
        int[] v = Util.geraVetorRandomico(16);

        Algoritmo a = new QuickComPivo(l, v, 10, new RegistroOrdenado(0, 0, 0, 0, 0));

        a.setArquivo(new Arquivo("src\\Arquivos\\fileReverso.dat"));

        a.OrdenaArquivo();
        System.out.println("Acabou");
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        c.setContents(new StringSelection(ar.toString()), null);

        /*ObservableList<Saida> o = FXCollections.observableArrayList();
        o.add(new Saida("QuickComPivo", a.getRegistro()));
        o.add(new Saida("QuickComPivo", a.getRegistro()));
        o.add(new Saida("QuickComPivo", a.getRegistro()));
        tabelaOrdenada.setItems(o);*/
    }
    
    private void scriptOredacoes()
    {
        Algoritmo a;
        Arquivo aOrd = new Arquivo("src\\Arquivos\\fileOrdenado.dat");
        Arquivo aReverso = new Arquivo("src\\Arquivos\\fileReverso.dat");
        Arquivo aRandom = new Arquivo("src\\Arquivos\\fileRandom.dat");
        
        //bolha
        iniciaArquivos();
        a = new Buble(null, null, 10, new RegistroOrdenado(0, 0, 0, 0, 0));
        a.setArquivo(aOrd);
        tabelaOrdenada.getItems().add(new Saida("Buble", a.getRegistro()));
        a.setArquivo(aRandom);
        tabelaRandom.getItems().add(new Saida("Buble", a.getRegistro()));
        a.setArquivo(aReverso);
        tabelaReversa.getItems().add(new Saida("Buble", a.getRegistro()));
        
        //insercao direta
        iniciaArquivos();
        a = new InserçãoDireta(null, null, 0, null);
        a.setArquivo(aOrd);
        tabelaOrdenada.getItems().add(new Saida("Buble", a.getRegistro()));
        a.setArquivo(aRandom);
        tabelaRandom.getItems().add(new Saida("Buble", a.getRegistro()));
        a.setArquivo(aReverso);
        tabelaReversa.getItems().add(new Saida("Buble", a.getRegistro()));
        
    }
    
    private void iniciaArquivos()
    {
        Util.geraArquivoOrdenado();
        Util.geraArquivoRandomico();
        Util.geraArquivoReverso();
    }
    private void iniciaComponentes()
    {
        nomeOrd.setCellValueFactory(new PropertyValueFactory<>("nome"));
        compProgOrd.setCellValueFactory(new PropertyValueFactory<>("compProg"));
        compEqualOrd.setCellValueFactory(new PropertyValueFactory<>("compEqua"));
        movProgOrd.setCellValueFactory(new PropertyValueFactory<>("movProg"));
        movEquaOrd.setCellValueFactory(new PropertyValueFactory<>("movEqua"));
        tempoOrd.setCellValueFactory(new PropertyValueFactory<>("tempo"));

        nomeRand.setCellValueFactory(new PropertyValueFactory<>("nome"));
        compProgRand.setCellValueFactory(new PropertyValueFactory<>("compProg"));
        compEquaRand.setCellValueFactory(new PropertyValueFactory<>("compEqua"));
        movProgRand.setCellValueFactory(new PropertyValueFactory<>("movProg"));
        movEquaRand.setCellValueFactory(new PropertyValueFactory<>("movEqua"));
        tempoRand.setCellValueFactory(new PropertyValueFactory<>("tempo"));

        nomeReverso.setCellValueFactory(new PropertyValueFactory<>("nome"));
        compProgRever.setCellValueFactory(new PropertyValueFactory<>("compProg"));
        compEquaRever.setCellValueFactory(new PropertyValueFactory<>("compEqua"));
        movProgRever.setCellValueFactory(new PropertyValueFactory<>("movProg"));
        movEquaRever.setCellValueFactory(new PropertyValueFactory<>("movEqua"));
        tempoRever.setCellValueFactory(new PropertyValueFactory<>("tempo"));
        
        /*ObservableList<Saida> o = FXCollections.observableArrayList();
        o.add(new Saida("Merge", new RegistroOrdenado(0, 0, 0, 0, 0)));
        o.add(new Saida("Merge", new RegistroOrdenado(0, 5, 30, 100, 0)));
        o.add(new Saida("Merge", new RegistroOrdenado(90, 70, 6, 1, 21)));
        tabelaOrdenada.setItems(o);*/

    }
}

package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * @author Gabriel Arsênio
 * @since 26/05/2015
 * @version 1.0
 */
public class FuncaoCopia {

    public FuncaoCopia() {
    }

    public boolean iniciarCopia(File lista, String pasta, JProgressBar barraProgresso, JLabel labelStatus) {
        int qtdElementos;
        String caminho;
        File arquivo;
        File destino;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc = null;
        try {
            docBuilder = dbf.newDocumentBuilder();
            doc = docBuilder.parse(lista);
        } catch (ParserConfigurationException ex) {
            JOptionPane.showMessageDialog(null, "Erro db: " + ex.getMessage());
        } catch (SAXException ex) {
            JOptionPane.showMessageDialog(null, "Erro SAX: " + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro IO: " + ex.getMessage());
        }

        Element tagSmil = doc.getDocumentElement();
        Element tagBody = (Element) tagSmil.getElementsByTagName("body").item(0);
        Element tagSeq = (Element) tagBody.getElementsByTagName("seq").item(0);
        Element tagMedia;

        qtdElementos = tagSeq.getElementsByTagName("media").getLength();

        if (qtdElementos == 0) {
            return false;
        }

        barraProgresso.setMaximum(qtdElementos);

        for (int i = 0; i < qtdElementos; i++) {
            try {
                tagMedia = (Element) tagSeq.getElementsByTagName("media").item(i);
                caminho = tagMedia.getAttribute("src");

                arquivo = new File(caminho);
                destino = new File(pasta + "\\" + arquivo.getName());

                copiarArquivo(arquivo, destino);

                barraProgresso.setValue(i + 1);
                labelStatus.setText(arquivo.getAbsolutePath());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Erro, ponto nulo: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao copiar: " + ex.getMessage());
            }
        }
        barraProgresso.setString("Concluído");
        labelStatus.setText("");
        return true;
    }

    public void copiarArquivo(File arquivo, File destino) throws IOException {
        if (destino.exists()) {
            destino.delete();
        }

        FileChannel arquivoAlvo = null;
        FileChannel arquivoDestino = null;

        try {
            arquivoAlvo = new FileInputStream(arquivo).getChannel();
            arquivoDestino = new FileOutputStream(destino).getChannel();
            arquivoAlvo.transferTo(0, arquivoAlvo.size(), arquivoDestino);
        } finally {
            if (arquivoAlvo != null && arquivoAlvo.isOpen()) {
                arquivoAlvo.close();
            }
            if (arquivoDestino != null && arquivoDestino.isOpen()) {
                arquivoDestino.close();
            }
        }
    }
}

package org.itstep.pps2701.blokhin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.security.InvalidParameterException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by Vit on 16.05.2017.
 */
public class MainFrame extends JFrame {
    Article article;
    ArticleBuilder articleBuilder;
    ArticleConverter articleConverter;

    private JMenu menuFile;
    private JMenuItem mnuOpen;
    private JMenuItem mnuParse;
    private JMenuItem mnuSave;
    private JScrollPane jScrollPane;
    private JTextArea textArea;
    private JPanel controlPanel;
    private JButton btnOpen, btnParse, btnSave;


    public MainFrame(String title) throws HeadlessException {
        super(title);

        buildGUI();
    } // MainWindow

    private void buildGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(600,500);
        setLocationRelativeTo(null);
        setResizable(false);

        controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        setJMenuBar(createMenuBar());


        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        jScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnOpen = new JButton("Открыть");
        btnParse = new JButton("Преобразовать");
        btnSave = new JButton("Сохранить");
        btnParse.setEnabled(false);
        btnSave.setEnabled(false);

        btnOpen.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        btnParse.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertText();
            }
        });

        btnSave.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveXml();
            }
        });

        controlPanel.add(btnOpen);
        controlPanel.add(btnParse);
        controlPanel.add(btnSave);

        getContentPane().add(jScrollPane);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    } // buildGUI

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuFile = new JMenu("Файл");

        mnuOpen = new JMenuItem("Открыть...");
        mnuParse = new JMenuItem("Преобразовать");
        mnuSave = new JMenuItem("Сохранить...");
        mnuParse.setEnabled(false);
        mnuSave.setEnabled(false);

        mnuOpen.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        mnuSave.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveXml();
            }
        });


        JMenuItem mnuExit = new JMenuItem("Выход");
        mnuExit.addActionListener(e -> System.exit(0));

        menuFile.add(mnuOpen);
        menuFile.add(mnuParse);
        menuFile.add(mnuSave);
        menuFile.addSeparator();
        menuFile.add(mnuExit);
        menuBar.add(menuFile);

        return menuBar;
    }

    private void openFile(){
        try{
            File file = new File("data/article.txt");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Текстовые файлы", "txt", "TXT"));
            int ret = fileChooser.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();

                articleBuilder = new ArticleBuilder();

                articleConverter = new ArticleConverter();
                articleConverter.setArticleBuilder(articleBuilder);

                articleConverter.loadFile(file.getPath());

                textArea.setText(articleConverter.getText());

                getContentPane().remove(jScrollPane);

                jScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                getContentPane().add(jScrollPane);
                btnParse.setEnabled(true);
                mnuParse.setEnabled(true);

                getContentPane().revalidate();
                getContentPane().repaint();

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    } // openFile

    private void convertText(){
        try{
            articleConverter.buildArticle();
            article = articleConverter.getArticle();

            textArea.setText(article.toString());

            getContentPane().remove(jScrollPane);

            jScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            getContentPane().add(jScrollPane);
            btnSave.setEnabled(true);
            btnParse.setEnabled(false);
            mnuParse.setEnabled(false);
            mnuSave.setEnabled(true);

            getContentPane().revalidate();
            getContentPane().repaint();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    } // convertText

    private void saveXml() {
        try{
            File file = new File("data/article.xml");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML файлы", "xml", "XML"));
            int ret = fileChooser.showSaveDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {

                if (file.getName() == null)
                    throw new NullPointerException("Имя файла не может быть null");
                if (file.getName().isEmpty())
                    throw new InvalidParameterException("Имя файла не может быть пустым");
                if(file.getName().contains("\\/?*:\"<>|"))
                    throw new InvalidParameterException("Имя файла не может содержать символы \\ / ? * : \" < > |");
                file = fileChooser.getSelectedFile();

                articleConverter.saveXml(file.getPath());

                JOptionPane.showMessageDialog(this, "XML успешно сохранён", "Сохранение файла", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    } // saveXml

} // class MainFrame

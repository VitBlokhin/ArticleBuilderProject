package org.itstep.pps2701.blokhin;

import javax.swing.*;

public class Main {

    /* Порождающие паттерны - паттерн Строитель (Builder)
        Имеется текст статьи в формате TXT. Статья состоит из:
        ► заголовка
        ► фамилий авторов
        ► хэш-кода текста статьи
        ► собственно текста статьи
        Написать приложение, моделирующее конвертирование статьи в формате TXT в формат XML
        (создание тегов для составных частей статьи по правилам XML). Проверять корректность хэш-кода
     */

    public static void main(String[] args) {
	// write your code here
        SwingUtilities.invokeLater(()-> {
            new MainFrame("Конвертер статей");
        });
    } // main
}

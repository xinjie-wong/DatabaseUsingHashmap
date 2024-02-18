/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wia1002_group3;
/**
 *
 * @author Xin Jie
 */

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.swing.JTextArea;

public class TextAreaPrintStream extends PrintStream {
    private JTextArea textArea;

    public TextAreaPrintStream(OutputStream out, JTextArea textArea) throws UnsupportedEncodingException {
        super(out, true, "UTF-8");
        this.textArea = textArea;
    }

    @Override
    public PrintStream printf(String format, Object... args) {
        String formattedText = String.format(format, args);
        textArea.append(formattedText);
        return this;
    }

    @Override
    public void println(String x) {
        textArea.append(x + "\n");
    }
}

package main.java.logging;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

class TextAreaOutputStream extends OutputStream {
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private final JTextArea textArea;

    protected TextAreaOutputStream(JTextArea textArea) {
        super();
        this.textArea = textArea;
    }

    @Override
    public void flush() throws IOException {
        textArea.append(buffer.toString());//StandardCharsets.UTF_8)); //I Chang: took this out for now to test other stuff as my compiler was not recognizing it as a proper argument
        buffer.reset();
    }

    @Override
    public void write(int b) throws IOException {
        buffer.write(b);
    }
}

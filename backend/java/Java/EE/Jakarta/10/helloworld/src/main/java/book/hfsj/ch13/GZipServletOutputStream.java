package book.hfsj.ch13;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZipServletOutputStream extends ServletOutputStream {
    private GZIPOutputStream gzipOutputStream;

    public GZipServletOutputStream(ServletOutputStream outputStream) throws IOException {
        this.gzipOutputStream = new GZIPOutputStream(outputStream);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int i) throws IOException {
        gzipOutputStream.write(i);
    }
    public void finish() throws IOException {
        gzipOutputStream.finish();
    }
    @Override
    public void close() throws IOException {
        gzipOutputStream.close();
    }
}

package book.hfsj.ch13;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;

public class CompressionResponseWrapper extends HttpServletResponseWrapper {
    private GZipServletOutputStream gzipServletOutputStream;
    public CompressionResponseWrapper(HttpServletResponse response) {
        super(response);
    }
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        ServletResponse response = getResponse();
        return (gzipServletOutputStream = new GZipServletOutputStream(response.getOutputStream()));
    }
    public void commit() throws IOException{
        System.out.println("going to finish gzip::::::::::::::::::::");
        gzipServletOutputStream.finish();
    }
}

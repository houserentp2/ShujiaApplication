package example.com.shujiaapplication.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPAccess extends HttpURLConnection {
    protected HTTPAccess(URL u) {
        super(u);
    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    @Override
    public void connect() throws IOException {

    }
}

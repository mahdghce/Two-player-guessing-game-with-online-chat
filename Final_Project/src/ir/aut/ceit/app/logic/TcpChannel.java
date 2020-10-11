package ir.aut.ceit.app.logic;

import java.io.*;
import java.net.Socket;

public class TcpChannel {
    private Socket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;

//    public TcpChannel(SocketAddress socketAddress, int timeout) {
//    }

//    public TcpChannel(Socket socket, int timeout) {
//    }

//    public byte[] read(final int count) {
//    }

//    public void write(byte[] data) {
//    }

    //done
    public boolean isConnected() {
        return mSocket.isConnected();
    }

    //done
    public void closeChannel() throws IOException {
        mInputStream.close();
        mOutputStream.close();
        mSocket.close();
    }
}

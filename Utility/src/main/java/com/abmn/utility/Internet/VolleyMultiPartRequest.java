package com.abmn.utility.Internet;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class VolleyMultiPartRequest extends Request<String> {

    private final Response.Listener<String> mListener;
    // POST param
    private final Map<String, String> mParams;
    // upload file
    private final Map<String, String> mFileUploads;

    public static final int TIMEOUT_MS = 30000;
    // delimiter
    private final String mBoundary = "Volley-" + System.currentTimeMillis();
    private final String lineEnd = "\r\n";
    private final String twoHyphens = "--";
    // output stream
    final ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();

    /**
     * Creates a new request with the given method.
     *
     * @param url           URL to fetch the string at
     * @param listener      Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public VolleyMultiPartRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {

        super(Method.POST, url, errorListener);
        // set retry policy
        setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MS, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mListener = listener;
        mParams = getDefaultParams();
        mFileUploads = getFileParams();

    }

    /**
     * Delimiter at the beginning of the parameter
     *
     * @throws IOException
     */
    @SuppressWarnings("JavaDoc")
    private void writeFirstBoundary() throws IOException {
        mOutputStream.write((twoHyphens + mBoundary + lineEnd).getBytes());
    }

    @Override
    public String getBodyContentType() {
        return "multipart/form-data;boundary=" + mBoundary;
    }


    @Override
    public byte[] getBody() {
        try {
            // Output stream
            DataOutputStream dos = new DataOutputStream(mOutputStream);


            // populate text payload
            Map<String, String> params = mParams;
            if (params != null && params.size() > 0) {
                textParse(dos, params, getParamsEncoding());
            }


            // upload files
            for (String key : mFileUploads.keySet()) {
                writeFirstBoundary();

                File file = new File(Objects.requireNonNull(mFileUploads.get(key)));
                final String type = "Content-Type: application/octet-stream" + lineEnd;
                dos.writeBytes(type);
                String fileName = file.getName();
                dos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"" + lineEnd);
                dos.writeBytes("Content-Transfer-Encoding: binary\r\n\r\n");

                FileInputStream fin = new FileInputStream(file);
                final byte[] tmp = new byte[4096];
                int len;
                while ((len = fin.read(tmp)) != -1) {
                    mOutputStream.write(tmp, 0, len);
                }
                fin.close();
                dos.writeBytes(lineEnd);
            }

            // populate data byte payload
            Map<String, ArrayList<DataPart>> data = getByteData();
            if (data != null && data.size() > 0) {
                dataParse(dos, data);
            }
            // send multipart form data necessary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + mBoundary + twoHyphens + lineEnd);

            return mOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parse string map into data output stream by key and value.
     *
     * @param dataOutputStream data output stream handle string parsing
     * @param params           string inputs collection
     * @param encoding         encode the inputs, default UTF-8
     * @throws IOException
     */
    private void textParse(DataOutputStream dataOutputStream, Map<String, String> params, String encoding) throws IOException {
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                buildTextPart(dataOutputStream, entry.getKey(), entry.getValue());
            }
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + encoding, uee);
        }
    }

    /**
     * Write string data into header and data output stream.
     *
     * @param dataOutputStream data output stream handle string parsing
     * @param parameterName    name of input
     * @param parameterValue   value of input
     * @throws IOException
     */
    private void buildTextPart(DataOutputStream dataOutputStream, String parameterName, String parameterValue) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + mBoundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + parameterName + "\"" + lineEnd);
        // dataOutputStream.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);

        dataOutputStream.write(parameterValue.getBytes("utf-8"));
        dataOutputStream.writeBytes(lineEnd);
    }

    public Map<String, String> getDefaultParams() {
        return null;
    }

    public Map<String, String> getFileParams() {

        return null;
    }

    private void dataParse(DataOutputStream dataOutputStream, Map<String, ArrayList<DataPart>> data) throws IOException {
        for (Map.Entry<String, ArrayList<DataPart>> entry : data.entrySet()) {
            buildDataPart(dataOutputStream, entry.getValue(), entry.getKey());
        }
    }

    protected Map<String, ArrayList<DataPart>> getByteData() {
        return null;
    }

    private void buildDataPart(DataOutputStream dos, ArrayList<DataPart> dataFile, String inputName) throws IOException {
        for (int i = 0; i < dataFile.size(); i++) {
            writeFirstBoundary();
            DataPart dp = dataFile.get(i);

            dos.writeBytes("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + dp.getFileName() + "\"" + lineEnd);
            if (dp.getType() != null && !dp.getType().trim().isEmpty()) {
                dos.writeBytes("Content-Type: " + dp.getType() + lineEnd);
            }

            dos.writeBytes(lineEnd);

            ByteArrayInputStream fileInputStream = new ByteArrayInputStream(dp.getContent());


            int bytesAvailable = fileInputStream.available();
            int maxBufferSize = 1024 * 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];

            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            dos.writeBytes(lineEnd);
        }
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

}
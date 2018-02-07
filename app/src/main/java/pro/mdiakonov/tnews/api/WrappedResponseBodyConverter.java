package pro.mdiakonov.tnews.api;

import java.io.IOException;

import okhttp3.ResponseBody;
import pro.mdiakonov.tnews.api.pojo.WrappedResponse;
import retrofit2.Converter;

public class WrappedResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private Converter<ResponseBody, WrappedResponse<T>> converter;

    public WrappedResponseBodyConverter(Converter<ResponseBody,
            WrappedResponse<T>> converter) {
        this.converter = converter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        WrappedResponse<T> response = converter.convert(value);
        if (response.resultCode.equals("OK")) {
            return response.payload;
        }
        throw new WrappedResponseException(response.resultCode);
    }
}

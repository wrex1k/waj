package sk.ukf.demo.payload;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private T data;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetime;

    private ApiResponse(T data, String message, LocalDateTime datetime) {
        this.data = data;
        this.message = message;
        this.datetime = datetime;
    }

    public static <T> ApiResponse<T> success(T data) {return new ApiResponse<>(data, null, LocalDateTime.now());}
    public static <T> ApiResponse<T> success(T data, String message) {return new ApiResponse<>(data, message, LocalDateTime.now());}
    public static <T> ApiResponse<T> error(String message) {return new ApiResponse<>(null, message, LocalDateTime.now());}

    public String getMessage() { return message; }
    public T getData() { return data; }
    public LocalDateTime getDatetime() { return datetime; }
}

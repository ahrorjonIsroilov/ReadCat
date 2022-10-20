package ent.readcat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private Object data;
    private String message;
    private boolean success;

    @Builder
    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    @Builder
    public ApiResponse(Object data, boolean success) {
        this.data = data;
        if (data instanceof String){
            this.message = (String) data;
            this.data = null;
        }
        this.success = success;
    }

    public ApiResponse(Object data) {
        this.data = data;
        this.success = true;
    }
    public ApiResponse(String message) {
        this.message = message;
        this.success = true;
    }
}

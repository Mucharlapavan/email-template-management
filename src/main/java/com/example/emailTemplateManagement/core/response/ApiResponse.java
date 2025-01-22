package com.example.emailTemplateManagement.core.response;
import com.example.emailTemplateManagement.core.dto.EmailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;
    private PaginationResponse paginationResponse;
    private long count;

    public ApiResponse(boolean success, String message, Object data, PaginationResponse paginationResponse) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.paginationResponse = paginationResponse;
    }
}

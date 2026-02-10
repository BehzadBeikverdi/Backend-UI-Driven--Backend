package ir.pishgaman.backend_data_driven_demo.models;

import ir.pishgaman.backend_data_driven_demo.enums.ResponseTypeEnum;

public class ApiResponseModel<T> {

    private ResponseTypeEnum responseType;
    private String model;
    private T data;
    private UiSchemaModel schema;

    private ApiResponseModel() {}

    public static ApiResponseModel<?> uiSchema(UiSchemaModel schema) {
        ApiResponseModel<?> res = new ApiResponseModel<>();
        res.responseType = ResponseTypeEnum.UI_SCHEMA;
        res.schema = schema;
        return res;
    }

    public static <T> ApiResponseModel<T> data(String model, T data) {
        ApiResponseModel<T> res = new ApiResponseModel<>();
        res.responseType = ResponseTypeEnum.DATA;
        res.model = model;
        res.data = data;
        return res;
    }

}

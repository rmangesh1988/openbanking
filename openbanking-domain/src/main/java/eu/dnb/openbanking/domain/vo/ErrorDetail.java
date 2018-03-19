package eu.dnb.openbanking.domain.vo;

/**
 * Created by rmang on 15-03-2018.
 */
public class ErrorDetail {

    private String errorNumber;

    private String errorField;

    private String errorDescription;

    public ErrorDetail() {
    }

    public ErrorDetail(String errorNumber, String errorField, String errorDescription) {
        this.errorNumber = errorNumber;
        this.errorField = errorField;
        this.errorDescription = errorDescription;
    }

    public String getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(String errorNumber) {
        this.errorNumber = errorNumber;
    }

    public String getErrorField() {
        return errorField;
    }

    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}

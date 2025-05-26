package tn.esprit.userservice.DTO;


public class ResetPasswordRequest {
    private String phoneNumber;
    private String newPassword;
    private String enteredCode;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEnteredCode() {
        return enteredCode;
    }

    public void setEnteredCode(String enteredCode) {
        this.enteredCode = enteredCode;
    }
}
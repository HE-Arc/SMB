package ch.hearc.sandbox.dto;

public class PasswordDto {

    private String newPassword;

    private String newPasswordConfirm;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String oldPassword) {
        this.newPassword = oldPassword;
    }

    public String getnewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setnewPasswordConfirm(String newPassword) {
        this.newPasswordConfirm = newPassword;
    }

}
